package ru.job4j.urlshortcut.domain;

/**
 * Заменяет класс шаблона DTO, конструкцией record.
 * обьект для хранения и передачи неизменяемых значений.
 * @param site
 */
public record SiteRecord(String site) {
}
