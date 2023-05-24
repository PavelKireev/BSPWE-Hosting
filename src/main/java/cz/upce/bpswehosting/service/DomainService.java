package cz.upce.bpswehosting.service;

import cz.upce.bpswehosting.db.entity.Domain;
import cz.upce.bpswehosting.dto.DomainDto;
import cz.upce.bpswehosting.model.domain.CreateDomainModel;

import java.util.List;

public interface DomainService {

    List<DomainDto> create(CreateDomainModel model);
    Domain addUser(Long userId, Long domainId);
    Domain removeUser(Long userId, Long domainId);
    Domain getOne(Long domainId);
    List<DomainDto> findAllByOwnerId(Long ownerId);
    List<Domain> findAllByOwnerUsername(String username);

}
