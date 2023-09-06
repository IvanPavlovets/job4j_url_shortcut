package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

/**
 * Как и в Spring MVC нужно создать сервис SiteDetailsService.
 * Этот сервис будет загружать в SecurityHolder детали
 * авторизованного пользователя.
 *
 */
@Service
@AllArgsConstructor
public class SiteDetailsService implements UserDetailsService {

    private final SiteService siteService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = siteService.findSiteByLogin(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        return new User(user.get().getLogin(), user.get().getPassword(), emptyList());
    }

}
