package cz.upce.bpswehosting.controller;

import cz.upce.bpswehosting.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
@CrossOrigin("http://localhost:4200")
public class UserController {

    private final UserService userService;

    @GetMapping
    public void getProfile(
        Authentication authentication
    ) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

    }

}
