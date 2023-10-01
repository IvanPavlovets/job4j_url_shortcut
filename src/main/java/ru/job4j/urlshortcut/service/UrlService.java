package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.domain.Site;
import ru.job4j.urlshortcut.domain.Url;
import ru.job4j.urlshortcut.domain.UrlRecord;
import ru.job4j.urlshortcut.repository.UrlRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@AllArgsConstructor
public class UrlService {

    private static final Logger LOG = LoggerFactory.getLogger(UrlService.class.getSimpleName());
    private final UrlRepository urlRepository;

    public Optional<Url> save(Url url) {
        Optional<Url> rsl = Optional.empty();
        try {
            rsl = Optional.of(urlRepository.save(url));
        } catch (Exception e) {
            e.printStackTrace();
            LOG.info("The url = {} is already taken.", url.getUrl());
        }
        return rsl;
    }

    /**
     * Возвращает ссылку по уникальному коду.
     * @return Optional<Url>
     */
    public Optional<Url> findUrlByCode(String code) {
        this.urlRepository.updateUrlByTotal(code);
        return this.urlRepository.findUrlByCode(code);
    }

    /**
     * Возвращает список ссылок List<Url>,
     * показывающий статистику, вызваные ссылки, пользователя.
     *
     * @return List<Url>
     */
    public List<UrlRecord> getStatistic(Site site) {
        List<UrlRecord> urls = new CopyOnWriteArrayList<>();
        for (Url url : urlRepository.findAllBySite(site)) {
            urls.add(getUrlRecord(url));
        }
        return urls;
    }

    /**
     * Внутриний метод конвертации полей Url в UrlRecord.
     * @return UrlRecord
     */
    private static UrlRecord getUrlRecord(Url url) {
        return UrlRecord.of().url(url.getUrl()).total(url.getTotal()).build();
    }
}
