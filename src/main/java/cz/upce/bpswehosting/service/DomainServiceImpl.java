package cz.upce.bpswehosting.service;

import cz.upce.bpswehosting.db.entity.Domain;
import cz.upce.bpswehosting.db.entity.User;
import cz.upce.bpswehosting.db.repository.DomainRepository;
import cz.upce.bpswehosting.model.domain.CreateDomainModel;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DomainServiceImpl implements DomainService {

    private final UserService userService;
    private final DomainRepository domainRepository;

    @Override
    public void create(CreateDomainModel model) {
        Domain domain = new Domain().setName(model.getName())
                                    .setBasePath('/' + model.getName())
                                    .setOwner(userService.getOne(model.getOwnerId()));

        domainRepository.save(domain);
    }

    @Override
    public Domain addUser(Long userId, Long domainId) {
        User user = userService.getOne(userId);
        Domain domain = domainRepository.findById(domainId).orElseThrow(EntityNotFoundException::new);
        domain.getMembers().add(user);
        return domainRepository.save(domain);
    }

    @Override
    public Domain removeUser(Long userId, Long domainId) {
        User user = userService.getOne(userId);
        Domain domain = domainRepository.findById(domainId).orElseThrow(EntityNotFoundException::new);
        domain.getMembers().remove(user);
        return domainRepository.save(domain);
    }

    @Override
    public Domain getOne(Long domainId) {
        return domainRepository.findById(domainId).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Domain> findAllByOwnerId(Long ownerId) {
        return domainRepository.findAllByOwnerId(ownerId);
    }

}
