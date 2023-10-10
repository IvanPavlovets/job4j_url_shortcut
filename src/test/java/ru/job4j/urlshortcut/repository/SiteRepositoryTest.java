package ru.job4j.urlshortcut.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.job4j.urlshortcut.domain.Site;

import javax.persistence.EntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class SiteRepositoryTest {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private SiteRepository siteRepository;


    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(entityManager).isNotNull();
        assertThat(siteRepository).isNotNull();
    }

    @Test
    public void whenFindSiteByLoginThenReturnEmpty() {
        Site site = Site.of().login("job4j").password("password").registration(true).build();
        var actual = siteRepository.findSiteByLogin(site.getLogin());
        assertThat(actual).isEmpty();
    }

    @Test
    public void whenFindSiteByLoginThenReturnLogin() {
        Site site = Site.of().login("job4j").password("password").registration(true).build();
        entityManager.persist(site);
        var actual = siteRepository.findSiteByLogin(site.getLogin());
        assertThat(actual).isEqualTo(Optional.of(site));
    }

    @Test
    public void whenFindSiteMoreLoginThenReturnEmpty() {
        Site site = Site.of().login("job4j").password("password").registration(true).build();
        entityManager.persist(site);
        var actual = siteRepository.findSiteByLogin("LOGOGO");
        assertThat(actual).isEmpty();
    }

    @Test
    public void whenFindSiteLoginThenReturnSite() {
        Site site = Site.of().login("job4j").password("password").registration(true).build();
        Site site1 = Site.of().login("job4j1").password("password1").registration(true).build();
        entityManager.persist(site);
        entityManager.persist(site1);
        var actual = siteRepository.findSiteByLogin(site1.getLogin());
        assertThat(actual).isEqualTo(Optional.of(site1));
    }

}
