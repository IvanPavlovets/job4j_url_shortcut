package ru.job4j.urlshortcut.controller;

import liquibase.repackaged.org.apache.commons.lang3.RandomStringUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.urlshortcut.domain.Site;
import ru.job4j.urlshortcut.service.SiteService;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class SiteController {
    private static final Logger LOG = LoggerFactory.getLogger(UrlController.class.getSimpleName());
    private final SiteService service;

    @PostMapping("/registration")
    public ResponseEntity<Map<String, String>> registration(@RequestBody Map<String, String> json) {
        LOG.info("Registration site={}", json.toString());
        String login = json.get("site");
        String password = RandomStringUtils.random(5, true, true);
        var findedSite = service.findSiteByLogin(login);
        var newSite = Site.of().login(login).password(password).registration(false).build();
        if (findedSite.isEmpty()) {
            newSite.setRegistration(true);
            this.service.save(newSite);
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new LinkedHashMap<>() {{
                    put("registration", String.valueOf(newSite.isRegistration()));
                    put("login", newSite.getLogin());
                    put("password", password);
                }});

    }
}
