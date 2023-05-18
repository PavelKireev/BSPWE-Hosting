package cz.upce.bpswehosting.controller;

import cz.upce.bpswehosting.db.entity.Domain;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("domain")
@RequiredArgsConstructor
public class DomainController {

    @GetMapping
    public List<Domain> list() {
        return Collections.emptyList();
    }

    @PostMapping("create")
    public void create() {

    }

    @GetMapping("add-user")
    public void upload(
        Authorization authorization,
        @RequestBody MultipartFile multipartFile
    ) {

    }

    @GetMapping("delete-user/{userId}")
    public void deleteUser(
        @PathVariable Long userId
    ) {
    }
}
