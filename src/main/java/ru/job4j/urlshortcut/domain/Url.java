package ru.job4j.urlshortcut.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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
@Builder(builderMethodName = "of")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "urls")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;
    @NotBlank(message = "Url must be non empty")
    private String url;
    @ManyToOne
    private Site site;
    private String code;
    private int total;
}
