package cz.upce.bpswehosting.service;

import cz.upce.bpswehosting.model.user.LoginModel;
import cz.upce.bpswehosting.model.user.RegistrationModel;
import org.springframework.security.core.Authentication;

public interface AuthService {

    String signIn(Authentication authentication, LoginModel model);
    void signUp(RegistrationModel model);

}
