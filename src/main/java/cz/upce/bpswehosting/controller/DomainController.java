package cz.upce.bpswehosting.controller;

import cz.upce.bpswehosting.config.AuthUser;
import cz.upce.bpswehosting.db.entity.Domain;
import cz.upce.bpswehosting.model.domain.CreateDomainModel;
import cz.upce.bpswehosting.service.DomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("domain")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class DomainController {

    private final DomainService domainService;

    @GetMapping
    public List<Domain> list() {
        return Collections.emptyList();
    }

    @PostMapping("create")
    public void create(
        Authentication authentication,
        @RequestBody CreateDomainModel model
    ) {
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        model.setOwnerId(authUser.getId());
        domainService.create(model);
    }

    @GetMapping("add-user")
    public void upload(
        @RequestParam Long userId,
        @RequestParam Long domainId
    ) {
        domainService.addUser(userId, domainId);
    }

    @GetMapping("delete-user")
    public void deleteUser(
        @RequestParam Long userId,
        @RequestParam Long domainId
    ) {
        domainService.removeUser(userId, domainId);
    }
}
