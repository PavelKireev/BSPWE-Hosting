package cz.upce.bpswehosting.db.repository;

import cz.upce.bpswehosting.db.entity.Domain;
import org.springframework.data.repository.CrudRepository;

public interface DomainRepository extends CrudRepository<Domain, Long> {
}
