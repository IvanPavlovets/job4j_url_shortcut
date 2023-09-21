package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.domain.Site;
import ru.job4j.urlshortcut.domain.Url;
import ru.job4j.urlshortcut.repository.UrlRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

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

    /**
     * Возвращает ссылку по уникальному коду.
     * @param code
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
     * @param site
     * @return List<Url>
     */
    public List<Url> getStatistic(Site site) {
        List<Url> urls = new CopyOnWriteArrayList<>();
        for (Url url : urlRepository.findAllBySite(site)) {
            urls.add(url);
        }
        return urls;
    }
}
