package cz.upce.bpswehosting.dto;

import cz.upce.bpswehosting.db.entity.Domain;
import lombok.Data;

@Data
public class DomainDto {

    private Long id;
    private String name;
    private Long ownerId;

    public DomainDto(Domain domain) {
        this.id = domain.getId();
        this.name = domain.getName();
        this.ownerId = domain.getDomainOwner().getId();
    }
}
