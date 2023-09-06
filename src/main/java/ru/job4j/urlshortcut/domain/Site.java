package ru.job4j.urlshortcut.domain;

import lombok.*;

import javax.persistence.*;

/**
 * Модель описывает сайты регестрируемые в системе
 * Каждому сайту выдается пару пароль и логин.
 * registration указывает, выполнена ли регистрация.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "of")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "sites")
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;
    private String login;
    private String password;
    private boolean registration;

}
