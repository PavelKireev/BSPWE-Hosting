package cz.upce.bpswehosting.model.user;

import lombok.Data;

@Data
public class RegistrationModel {
    private String username;
    private String firstName;
    private String lastName;
    private String password;
}
