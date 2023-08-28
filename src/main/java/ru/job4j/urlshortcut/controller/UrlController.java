package ru.job4j.urlshortcut.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.urlshortcut.service.UrlService;

@RestController
@RequiredArgsConstructor
public class UrlController {
    private static final Logger LOG = LoggerFactory.getLogger(UrlController.class.getSimpleName());
    private final UrlService service;

}
