package cz.upce.bpswehosting.service;

import cz.upce.bpswehosting.db.entity.Domain;
import cz.upce.bpswehosting.model.domain.CreateDomainModel;

public interface DomainService {

    void create(CreateDomainModel model);
    void addUser(Long userId, Long domainId);
    void removeUser(Long userId, Long domainId);
    Domain getOne(Long domainId);

}
