package ru.job4j.urlshortcut.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@ControllerAdvice
@Data
public class GlobalExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger((GlobalExceptionHandler.class.getSimpleName()));
    private final ObjectMapper objectMapper;

    @ExceptionHandler(value = { NullPointerException.class })
    public void handleException(Exception e,
                                HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() { {
            put("message", "Some of fields empty");
            put("type", e.getMessage());
        }}));
        LOG.error(e.getMessage());
    }

}