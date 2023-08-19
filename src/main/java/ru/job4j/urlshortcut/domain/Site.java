package ru.job4j.urlshortcut.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Модель описывает сайты регестрируемые в системе
 * Каждому сайту выдается пару пароль и логин.
 * registration указывает, выполнена ли регистрация.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Site {
    private int id;
    private String login;
    private String password;
    private boolean registration;

}
