package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.domain.Url;
import ru.job4j.urlshortcut.repository.UrlRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UrlService {
    private final UrlRepository urlRepository;

    public Optional<Url> save(Url url) {
        Optional<Url> rsl = Optional.empty();
        try {
            rsl = Optional.of(urlRepository.save(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public Optional<Url> findUrlByLogin(String code) {
        return this.urlRepository.findUrlByCode(code);
    }
}
