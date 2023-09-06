package ru.job4j.urlshortcut;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@SpringBootApplication
public class Job4jUrlShortcutApplication {
	private static final Logger LOG = LoggerFactory.getLogger(Job4jUrlShortcutApplication.class.getSimpleName());

	@Bean
	public SpringLiquibase liquibase(DataSource ds) {
		SpringLiquibase liquibase = new SpringLiquibase();
		liquibase.setChangeLog("classpath:liquibase-changeLog.xml");
		liquibase.setDataSource(ds);
		return liquibase;
	}

	/**
	 * Создание PasswordEncoder для шифрования паролей.
	 *
	 * @return PasswordEncoder
	 */
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	public static void main(String[] args) {
		SpringApplication.run(Job4jUrlShortcutApplication.class, args);
		LOG.info("Go to http://localhost:8080/");
	}

}
