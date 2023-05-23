package cz.upce.bpswehosting.controller;

import cz.upce.bpswehosting.config.AuthUser;
import cz.upce.bpswehosting.model.user.UpdateProfileModel;
import cz.upce.bpswehosting.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
@CrossOrigin("http://localhost:4200")
public class UserController {

    private final UserService userService;

    @GetMapping
    public void get(
        Authentication authentication
    ) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

    }

    @PostMapping("update")
    public void update(
        Authentication authentication,
        UpdateProfileModel model
    ) {
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        userService.update(authUser.getId(), model);
    }

}
