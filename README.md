![](https://img.shields.io/badge/Java-17-orange)
![](https://img.shields.io/badge/Maven-3-red)
![](https://img.shields.io/badge/Spring%20boot-%202.7.14-green)
![](https://img.shields.io/badge/PostgreSQL-%3E%3D%209-informational)
![](https://img.shields.io/badge/-JDBC-blue)
![](https://img.shields.io/badge/-checkstyle-lightgrey)

# job4j_url_shortcut
 - [О проекте](https://github.com/IvanPavlovets/job4j_url_shortcut/tree/master#%D0%BE-%D0%BF%D1%80%D0%BE%D0%B5%D0%BA%D1%82%D0%B5)
 - [Технологии](https://github.com/IvanPavlovets/job4j_url_shortcut/tree/master#%D1%82%D0%B5%D1%85%D0%BD%D0%BE%D0%BB%D0%BE%D0%B3%D0%B8%D0%B8) 
 - [Как использовать](https://github.com/IvanPavlovets/job4j_url_shortcut/tree/master#%D0%BA%D0%B0%D0%BA-%D0%B8%D1%81%D0%BF%D0%BE%D0%BB%D1%8C%D0%B7%D0%BE%D0%B2%D0%B0%D1%82%D1%8C)  

## О проекте
Чтобы обеспечить безопасность пользователей, все ссылки на сайте заменяются ссылками на наш сервис.<br>
Пользователь регистрирует свой сайт после чего получает логин (url сайта) и пароль (уникальный ключ).<br>

После авторизации пользователь получает токен, ключ доступа к регистрации (функционал сервиса),<br>
затем пользователь может осуществить регистрацию ссылок на своем сайте, зарегистрированном раннее.<br>

После регистрации ссылки (url) на сайте пользователь получает в ответ ключ в виде уникального кода<br>
ассоциированного с URL. Полуученый уникальный код можно использовать для получения<br>
ассоциированного адреса (url ссылки, регистрируемой ранее) и статус 302 (REDIRECT).<br>
Получения ассоциированного адреса (команда REDIRECT) можно выполнять без авторизации (без токена).<br>

Так же сервис регистрирует статистику, для каждого сайта, считается количество вызовов каждой ссылки (url).<br>
По определенному сайту можно получить количество обращений всех его ссылок.<br>


## Технологии
 * **Java 17**
 * **Spring Boot 2.7.14**
 * **Spring data jpa**
 * **Spring security**
 * Сборщик проектов - **Maven**
 * СУБД - **PostgreSQL**
 * Инструмент анализа стиля кода - **Checkstyle**
 * библиотека сокращения шаблонного кода в классах Java. - **Lombok**

## Как использовать
Сервис работает через REST API. 