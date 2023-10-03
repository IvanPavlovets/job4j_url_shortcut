package ru.job4j.urlshortcut.domain;

import javax.validation.constraints.NotBlank;

/**
 * Заменяет класс шаблона DTO, конструкцией record.
 * обьект для хранения и передачи неизменяемых значений.
 * @param site
 */
public record SiteRecord(@NotBlank(message = "Login must be non empty") String site) {
}
