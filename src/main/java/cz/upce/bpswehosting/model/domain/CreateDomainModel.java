package cz.upce.bpswehosting.model.domain;

import lombok.Data;

@Data
public class CreateDomainModel {
    private String name;
    private String basePath;
    private Long ownerId;
}