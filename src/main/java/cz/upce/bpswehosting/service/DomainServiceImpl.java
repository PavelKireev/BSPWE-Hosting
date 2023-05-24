package cz.upce.bpswehosting.service;

import cz.upce.bpswehosting.db.entity.Domain;
import cz.upce.bpswehosting.db.entity.User;
import cz.upce.bpswehosting.db.repository.DomainRepository;
import cz.upce.bpswehosting.dto.DomainDto;
import cz.upce.bpswehosting.model.domain.CreateDomainModel;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DomainServiceImpl implements DomainService {

    private final UserService userService;
    private final DomainRepository domainRepository;

    @Override
    public List<DomainDto> create(CreateDomainModel model) {
        Domain domain = new Domain().setName(model.getDomainName())
                                    .setBasePath('/' + model.getDomainName())
                                    .setDomainOwner(userService.getOne(model.getOwnerId()));

        domainRepository.save(domain);
        return findAllByOwnerId(model.getOwnerId());
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
    public List<DomainDto> findAllByOwnerId(Long ownerId) {
        return domainRepository.findAllByDomainOwnerId(ownerId)
                               .stream()
                               .map(DomainDto::new)
                               .collect(Collectors.toList());
    }

    @Override
    public List<Domain> findAllByOwnerUsername(String username) {
        return domainRepository.findAllByDomainOwnerUsername(username);
    }

}
