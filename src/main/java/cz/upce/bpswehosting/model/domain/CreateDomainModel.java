package cz.upce.bpswehosting.model.domain;

import lombok.Data;

@Data
public class CreateDomainModel {
    private String domainName;
    private Long ownerId;
}
