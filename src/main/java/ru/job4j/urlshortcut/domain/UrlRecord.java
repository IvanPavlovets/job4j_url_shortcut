package ru.job4j.urlshortcut.domain;

import lombok.Builder;

/**
 * Заменяет класс шаблона DTO, конструкцией record.
 * обьект для хранения и передачи неизменяемых значений.
 * @param url
 * @param total
 */
@Builder(builderMethodName = "of")
public record UrlRecord(String url, Integer total) {
}
