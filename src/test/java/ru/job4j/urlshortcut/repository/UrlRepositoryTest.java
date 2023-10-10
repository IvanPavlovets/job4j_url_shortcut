package ru.job4j.urlshortcut.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.job4j.urlshortcut.domain.Site;
import ru.job4j.urlshortcut.domain.Url;

import javax.persistence.EntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UrlRepositoryTest {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private UrlRepository urlRepository;

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(entityManager).isNotNull();
        assertThat(urlRepository).isNotNull();
    }

    @Test
    void whenFindUrlByCodeThenReturnEmpty() {
        Site site1 = Site.of().login("login_site1").password("passwors_site1").registration(true).build();
        entityManager.persist(site1);
        Url url = Url.of().url("url").site(site1).code("code").build();
        var actual = urlRepository.findUrlByCode(url.getCode());
        assertThat(actual).isEmpty();
    }

    @Test
    void whenFindUrlByCodeThenReturnUrl() {
        Site site1 = Site.of().login("login_site1").password("passwors_site1").registration(true).build();
        entityManager.persist(site1);
        Url url = Url.of().url("url").site(site1).code("code").build();
        entityManager.persist(url);
        var actual = urlRepository.findUrlByCode(url.getCode());
        assertThat(actual).isEqualTo(Optional.of(url));
    }

    @Test
    void whenSaveTwoUrlFindUrlByCodeThenReturnUrl1() {
        Site site1 = Site.of().login("login_site1").password("passwors_site1").registration(true).build();
        entityManager.persist(site1);
        Url url = Url.of().url("url").site(site1).code("code").build();
        Url url1 = Url.of().url("url1").site(site1).code("code1").build();
        entityManager.persist(url);
        entityManager.persist(url1);
        var actual = urlRepository.findUrlByCode(url1.getCode());
        assertThat(actual).isEqualTo(Optional.of(url1));
    }

    @Test
    void whenFindAllBySiteReturnEmpty() {
        Site site1 = Site.of().login("login_site1").password("passwors_site1").registration(true).build();
        entityManager.persist(site1);
        var actual = urlRepository.findAllBySite(site1);
        assertThat(actual.iterator().hasNext()).isFalse();
    }

    @Test
    void whenFindAllByReturnIteratorTwoUrl() {
        Site site1 = Site.of().login("login_site1").password("passwors_site1").registration(true).build();
        entityManager.persist(site1);
        Url url = Url.of().url("url").site(site1).code("code").build();
        Url url1 = Url.of().url("url1").site(site1).code("code1").build();
        entityManager.persist(url);
        entityManager.persist(url1);
        var actual = urlRepository.findAllBySite(site1);
        var iterator = actual.iterator();
        assertThat(iterator.next()).isEqualTo(url);
        assertThat(iterator.next()).isEqualTo(url1);
    }

    @Test
    void whenUpdateUrlByTotalWhenTotalAddOne() {
        Site site1 = Site.of().login("login_site1").password("passwors_site1").registration(true).build();
        entityManager.persist(site1);

        Url url = Url.of().url("url").site(site1).code("code").build();
        entityManager.persist(url);
        entityManager.flush();
        entityManager.clear();

        urlRepository.updateUrlByTotal(url.getCode());
        var actual = urlRepository.findUrlByCode(url.getCode());

        assertThat(actual.get().getTotal()).isEqualTo(1);
    }

    @Test
    void whenUpdateUrlByTotalWhenTotalAddTwo() {
        Site site1 = Site.of().login("login_site1").password("passwors_site1").registration(true).build();
        entityManager.persist(site1);

        Url url = Url.of().url("url").site(site1).code("code").build();
        entityManager.persist(url);
        entityManager.flush();
        entityManager.clear();

        urlRepository.updateUrlByTotal(url.getCode());
        urlRepository.updateUrlByTotal(url.getCode());
        var actual = urlRepository.findUrlByCode(url.getCode());

        assertThat(actual.get().getTotal()).isEqualTo(2);
    }

}
