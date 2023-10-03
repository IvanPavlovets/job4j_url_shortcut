package ru.job4j.urlshortcut.domain;

import lombok.Builder;

import javax.validation.constraints.NotBlank;

/**
 * Заменяет класс шаблона DTO, конструкцией record.
 * обьект для хранения и передачи неизменяемых значений.
 * @param url
 * @param total
 */
@Builder(builderMethodName = "of")
public record UrlRecord(@NotBlank(message = "Url must be non empty") String url, Integer total) {
}
