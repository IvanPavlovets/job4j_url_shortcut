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
import ru.job4j.urlshortcut.domain.UrlRecord;
import ru.job4j.urlshortcut.service.SiteService;
import ru.job4j.urlshortcut.service.UrlService;

import javax.validation.Valid;
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
     * 1) Сохраняет URL адрес, привязоный к сайту,
     * возвращает код URL адреса.
     * 2) Ответ на http запрос, в виде простого ответа через метод
     * ResponseEntity.ok() + body
     *
     * @param record тело входящего запроса
     * @return ResponseEntity<Map<String, String>>
     */
    @PostMapping("/convert")
    public ResponseEntity<Map<String, String>> convert(@Valid @RequestBody UrlRecord record,
                                                       Authentication authentication) {
        LOG.info("Registration url={}", record.url());
        if (record.url() == null) {
            throw new NullPointerException("Url mustn't be empty");
        }
        String code = RandomStringUtils.random(CODE_LENGTH, true, true);
        String login = authentication.getName();
        var foundSite = siteService.findSiteByLogin(login)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "For some reason the site was not found in the database."));
        Url urlSite = Url.of().url(record.url()).site(foundSite).code(code).build();
        if (this.urlService.save(urlSite).isEmpty()) {
            return new ResponseEntity<>(new HashMap<>() {{
                put("The url/code is already taken", record.url());
            }},
                    HttpStatus.CONFLICT
            );
        }
        return ResponseEntity.ok()
                .body(new HashMap<>() {{
                    put("code", code);
                }});
    }

    /**
     * 1) Возвращает статус 302 и ассоциированный адрес
     * по закодированой ссылке, которую получили в
     * /convert
     * 2) Ответ на http запрос, через строитель
     * ResponseEntity.status().header().contentType()
     * .contentLength().body();
     *
     * @param code уникальный код ссылки
     * @return ResponseEntity<String>
     */
    @GetMapping("/redirect/{code}")
    public ResponseEntity<String> redirect(@PathVariable String code) {
        if (code == null) {
            throw new NullPointerException("code mustn't be empty");
        }
        Url url = urlService.findUrlByCode(code)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Url is not found. Please, check unique code for url."));
        var body = new HashMap<>() {{
            put("URL", url.getUrl());
        }}.toString();
        return ResponseEntity.status(HttpStatus.FOUND)
                .header("HTTP CODE", "302 REDIRECT URL")
                .contentType(MediaType.TEXT_PLAIN)
                .contentLength(body.length())
                .body(body);
    }

    /**
     * В сервисе считается количество вызовов каждого адреса.
     * Метод возвращает статистику по вызваемым ссылкам.
     *
     * @return Iterable<Url>
     */
    @GetMapping("/statistic")
    public Iterable<UrlRecord> statistic(Authentication authentication) {
        String login = authentication.getName();
        var foundSite = siteService.findSiteByLogin(login)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Site is not found. Please, check url for site."));
        return urlService.getStatistic(foundSite);
    }


}
