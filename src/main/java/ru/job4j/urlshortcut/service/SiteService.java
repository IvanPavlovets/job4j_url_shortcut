package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.domain.Site;
import ru.job4j.urlshortcut.repository.SiteRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SiteService {
    private static final Logger LOG = LoggerFactory.getLogger(SiteService.class.getSimpleName());
    private final SiteRepository siteRepository;
    private final PasswordEncoder encoder;

    public Site save(Site site) {
        try {
            site.setPassword(encoder.encode(site.getPassword()));
            this.siteRepository.save(site);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.info("The site = {} has not be saved, the url is already taken.", site.getLogin());
            site.setRegistration(false);
        }
        return site;
    }

    public Optional<Site> findSiteByLogin(String login) {
        return this.siteRepository.findSiteByLogin(login);
    }

}

