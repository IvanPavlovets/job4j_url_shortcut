package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.domain.Site;
import ru.job4j.urlshortcut.repository.SiteRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SiteService {
    private final SiteRepository siteRepository;
    private final PasswordEncoder encoder;

    public Optional<Site> save(Site site) {
        Optional<Site> rsl = Optional.empty();
        try {
            site.setPassword(encoder.encode(site.getPassword()));
            rsl = Optional.of(siteRepository.save(site));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public Optional<Site> findSiteByLogin(String login) {
        return this.siteRepository.findSiteByLogin(login);
    }

}

