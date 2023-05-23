package cz.upce.bpswehosting.service;

import cz.upce.bpswehosting.db.entity.Domain;
import cz.upce.bpswehosting.model.domain.CreateDomainModel;

import java.util.List;

public interface DomainService {

    void create(CreateDomainModel model);
    Domain addUser(Long userId, Long domainId);
    Domain removeUser(Long userId, Long domainId);
    Domain getOne(Long domainId);

    List<Domain> findAllByOwnerId(Long ownerId);
}
