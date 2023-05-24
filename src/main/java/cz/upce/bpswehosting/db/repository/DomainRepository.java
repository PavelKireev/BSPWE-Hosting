package cz.upce.bpswehosting.db.repository;

import cz.upce.bpswehosting.db.entity.Domain;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DomainRepository extends CrudRepository<Domain, Long> {

    List<Domain> findAllByDomainOwnerId(Long ownerId);
    List<Domain> findAllByDomainOwnerUsername(String username);
}
