package ru.job4j.urlshortcut.controller;

import liquibase.repackaged.org.apache.commons.lang3.RandomStringUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.urlshortcut.domain.Site;
import ru.job4j.urlshortcut.service.SiteService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class SiteController {
    private static final Logger LOG = LoggerFactory.getLogger(UrlController.class.getSimpleName());
    private final SiteService siteService;
    private static final int CODE_LENGTH = 5;

    /**
     * Метод регистрации сайта.
     * Чтобы зарегистрировать сайт в систему
     * нужно отправить запрос POST по url:
     * /registration.
     * запроса в виде JSON с телом:
     * {
     *     "site" : "job4j"
     * }
     * Ответ получем в виде JSON + tokken (headers):
     * {
     *     "registration": "true",
     *     "login": "job4444j.ru",
     *     "password": "FN7KZ"
     * }
     *
     * @param json
     * @return ResponseEntity<Map<String, String>>
     */
    @PostMapping("/registration")
    public ResponseEntity<Map<String, String>> registration(@RequestBody Map<String, String> json) {
        LOG.info("Registration site={}", json.toString());
        String login = json.get("site");
        String password = RandomStringUtils.random(CODE_LENGTH, true, true);
        var foundSite = siteService.findSiteByLogin(login);
        var newSite = Site.of().login(login).password(password).registration(false).build();
        if (foundSite.isEmpty()) {
            newSite.setRegistration(true);
            this.siteService.save(newSite);
        }
        return ResponseEntity.ok()
                .body(new HashMap<>() {{
                    put("registration", String.valueOf(newSite.isRegistration()));
                    put("login", newSite.getLogin());
                    put("password", password);
                }});

    }
}
