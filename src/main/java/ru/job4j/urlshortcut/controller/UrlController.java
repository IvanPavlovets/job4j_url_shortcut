package ru.job4j.urlshortcut.controller;

import liquibase.repackaged.org.apache.commons.lang3.RandomStringUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.urlshortcut.domain.Url;
import ru.job4j.urlshortcut.service.SiteService;
import ru.job4j.urlshortcut.service.UrlService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UrlController {
    private static final Logger LOG = LoggerFactory.getLogger(UrlController.class.getSimpleName());
    private final UrlService urlService;
    private final SiteService siteService;
    private static final int CODE_LENGTH = 5;

    @PostMapping("/convert")
    public ResponseEntity<Map<String, String>> convert(@RequestBody Map<String, String> json,
                                                       Authentication authentication) {
        LOG.info("Registration url={}", json.toString());
        String url = json.get("url");
        String code = RandomStringUtils.random(CODE_LENGTH, true, true);
        String login = authentication.getName();
        var findedSite = siteService.findSiteByLogin(login);
        if (findedSite.isEmpty()) {
            return (ResponseEntity<Map<String, String>>) ResponseEntity.notFound();
        }
        Url urlSite = Url.of().url(url).site(findedSite.get()).code(code).build();
        this.urlService.save(urlSite);
        return ResponseEntity.ok()
                .body(new HashMap<>() {{
                    put("code", code);
                }});
    }

}
