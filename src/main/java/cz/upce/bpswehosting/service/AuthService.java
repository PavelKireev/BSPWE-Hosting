package cz.upce.bpswehosting.service;

import cz.upce.bpswehosting.model.LoginModel;
import cz.upce.bpswehosting.model.RegistrationModel;
import org.springframework.security.core.Authentication;

public interface AuthService {
    String signIn(Authentication authentication, LoginModel model);

    void signUp(RegistrationModel model);
}
