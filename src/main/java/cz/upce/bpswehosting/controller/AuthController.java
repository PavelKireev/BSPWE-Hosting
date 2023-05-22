package cz.upce.bpswehosting.controller;

import cz.upce.bpswehosting.model.user.LoginModel;
import cz.upce.bpswehosting.model.user.RegistrationModel;
import cz.upce.bpswehosting.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
@CrossOrigin("http://localhost:4200")
public class AuthController {

    private final AuthService authService;

    @PostMapping("sign-in")
    public String signIn(
        @RequestBody LoginModel model,
        Authentication authentication
    ) {
        return authService.signIn(authentication, model);
    }

    @PostMapping("sign-up")
    public void signUp(
        @RequestBody RegistrationModel model
    ) {
        authService.signUp(model);
    }

}
