package ru.job4j.urlshortcut.controller;

import liquibase.repackaged.org.apache.commons.lang3.RandomStringUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
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

    /**
     * Сохраняет URL адрес, привязоный к сайту,
     * возвращает код URL адреса.
     *
     * @param json тело входящего запроса
     * @param authentication
     * @return ResponseEntity<Map<String, String>>
     */
    @PostMapping("/convert")
    public ResponseEntity<Map<String, String>> convert(@RequestBody Map<String, String> json,
                                                       Authentication authentication) {
        LOG.info("Registration url={}", json.toString());
        String url = json.get("url");
        String code = RandomStringUtils.random(CODE_LENGTH, true, true);
        String login = authentication.getName();
        var foundSite = siteService.findSiteByLogin(login)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Url urlSite = Url.of().url(url).site(foundSite).code(code).build();
        this.urlService.save(urlSite);
        return ResponseEntity.ok()
                .body(new HashMap<>() {{
                    put("code", code);
                }});
    }

    /**
     * Возвращает статус 302 и ассоциированный адрес
     * по закодированой ссылке, которую получили в
     * /convert
     * @param code уникальный код ссылки
     * @return ResponseEntity<String>
     */
    @GetMapping("/redirect/{code}")
    public ResponseEntity<String> redirect(@PathVariable String code) {
        Url url = urlService.findUrlByCode(code)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.status(HttpStatus.FOUND)
                .header("HTTP CODE", "302 REDIRECT URL")
                .contentType(MediaType.TEXT_PLAIN)
                .body(new HashMap<>() {{
                        put("URL", url.getUrl());
                    }}.toString());
    }

    /**
     * В сервисе считается количество вызовов каждого адреса.
     * Метод возвращает статистику по вызваемым ссылкам.
     *
     * @param authentication
     * @return Iterable<Url>
     */
    @GetMapping("/statistic")
    public Iterable<Url> statistic(Authentication authentication) {
        String login = authentication.getName();
        var foundSite = siteService.findSiteByLogin(login)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return urlService.getStatistic(foundSite);
    }


}
