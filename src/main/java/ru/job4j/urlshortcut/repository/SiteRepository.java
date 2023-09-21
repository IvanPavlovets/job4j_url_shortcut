package ru.job4j.urlshortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.urlshortcut.domain.Site;

import java.util.Optional;

public interface SiteRepository extends CrudRepository<Site, Integer> {

    /**
     * Возвращает сайт по логину.
     * @param login
     * @return Optional<Site>
     */
    Optional<Site> findSiteByLogin(String login);

}
