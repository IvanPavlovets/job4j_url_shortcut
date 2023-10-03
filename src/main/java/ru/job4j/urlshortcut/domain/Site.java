package ru.job4j.urlshortcut.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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
    @NotBlank(message = "Login must be non empty")
    private String login;
    @NotBlank(message = "Password must be non empty")
    private String password;
    private boolean registration;

}
