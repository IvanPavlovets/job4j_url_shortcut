package ru.job4j.urlshortcut.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Модель описывает ссылки добавленые в систему для кодировки.
 * Поле того, как пользователь зарегистрировал свой сайт он может отправлять
 * на сайт ссылки и получать преобразованные ссылки.
 * Ответ от сервера - уникальный code ассоциированный с URL.
 * В сервисе считается количество вызовов каждого адреса.
 * пример: {url : "https://job4j.ru/profile/exercise/106/task-view/532", total : 103}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Url {
    private int id;
    private String url;
    private Site site;
    private String code;
    private int total;
}
