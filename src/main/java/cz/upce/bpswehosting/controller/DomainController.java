package cz.upce.bpswehosting.controller;

import cz.upce.bpswehosting.config.AuthUser;
import cz.upce.bpswehosting.db.entity.Domain;
import cz.upce.bpswehosting.db.entity.User;
import cz.upce.bpswehosting.model.domain.CreateDomainModel;
import cz.upce.bpswehosting.service.DomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("domain")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class DomainController {

    private final DomainService domainService;

    @GetMapping("list")
    public List<Domain> list(
        Authentication authentication
    ) {
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        return domainService.findAllByOwnerId(authUser.getId());
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
    public List<User> upload(
        @RequestParam Long userId,
        @RequestParam Long domainId
    ) {
        Domain domain = domainService.addUser(userId, domainId);
        return domain.getMembers();
    }

    @GetMapping("delete-user")
    public List<User> deleteUser(
        @RequestParam Long userId,
        @RequestParam Long domainId
    ) {
        Domain domain = domainService.removeUser(userId, domainId);
        return domain.getMembers();
    }
}
