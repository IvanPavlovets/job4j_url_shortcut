package ru.job4j.urlshortcut.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.urlshortcut.domain.Site;
import ru.job4j.urlshortcut.domain.Url;

import java.util.Optional;

public interface UrlRepository extends CrudRepository<Url, Integer> {

    /**
     * Поиск сайта по коду
     * @return Optional<Url>
     */
    Optional<Url> findUrlByCode(String code);

    Iterable<Url> findAllBySite(Site site);

    /**
     * Метод для увеличения счетчика статистики вызова каждого адреса.
     * Увеличение счетчика нужно сделать в базе данных, а не в Java.
     *
     * @param link String ссылка обьекта Url.
     */
    @Transactional
    @Modifying
    @Query("UPDATE Url u SET u.total = u.total + 1 WHERE u.url=:link")
    void updateUrlByTotal(@Param("link") String link);
}
