package cz.upce.bpswehosting.service;

import cz.upce.bpswehosting.db.entity.Domain;
import cz.upce.bpswehosting.db.repository.DomainRepository;
import cz.upce.bpswehosting.model.domain.CreateDomainModel;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DomainServiceImpl implements DomainService {

    private final UserService userService;
    private final DomainRepository domainRepository;

    @Override
    public void create(CreateDomainModel model) {
        Domain domain = new Domain().setName(model.getName())
                                    .setBasePath(model.getName())
                                    .setOwner(userService.getOne(model.getOwnerId()));

        domainRepository.save(domain);
    }

    @Override
    public void addUser(Long userId, Long domainId) {

    }

    @Override
    public void removeUser(Long userId, Long domainId) {

    }

    @Override
    public Domain getOne(Long domainId) {
        return domainRepository.findById(domainId).orElseThrow(EntityNotFoundException::new);
    }

}
