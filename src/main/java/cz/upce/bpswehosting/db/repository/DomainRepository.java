package cz.upce.bpswehosting.db.repository;

import cz.upce.bpswehosting.db.entity.Domain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomainRepository extends JpaRepository<Domain, Long> {
}
