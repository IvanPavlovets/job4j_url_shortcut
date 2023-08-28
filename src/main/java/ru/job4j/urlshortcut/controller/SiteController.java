package ru.job4j.urlshortcut.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.urlshortcut.service.SiteService;

@RestController
@RequiredArgsConstructor
public class SiteController {
    private static final Logger LOG = LoggerFactory.getLogger(UrlController.class.getSimpleName());
    private final SiteService service;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody) {
        return getResponseEntity();
    }
}
