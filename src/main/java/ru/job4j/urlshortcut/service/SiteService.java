package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.domain.Site;
import ru.job4j.urlshortcut.repository.SiteRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SiteService {
    private final SiteRepository siteRepository;

    public Optional<Site> save(Site site) {
        Optional<Site> rsl = Optional.empty();
        try {
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

