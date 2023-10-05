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
   - [Регистрация сайта](https://github.com/IvanPavlovets/job4j_url_shortcut/tree/master#%D1%80%D0%B5%D0%B3%D0%B8%D1%81%D1%82%D1%80%D0%B0%D1%86%D0%B8%D1%8F-%D1%81%D0%B0%D0%B9%D1%82%D0%B0)
   - [Авторизация](https://github.com/IvanPavlovets/job4j_url_shortcut/tree/master#%D0%B0%D0%B2%D1%82%D0%BE%D1%80%D0%B8%D0%B7%D0%B0%D1%86%D0%B8%D1%8F)
   - [Регистрация URL](https://github.com/IvanPavlovets/job4j_url_shortcut/tree/master#%D1%80%D0%B5%D0%B3%D0%B8%D1%81%D1%82%D1%80%D0%B0%D1%86%D0%B8%D1%8F-url)
   - [Переадресация](https://github.com/IvanPavlovets/job4j_url_shortcut/tree/master#%D0%BF%D0%B5%D1%80%D0%B5%D0%B0%D0%B4%D1%80%D0%B5%D1%81%D0%B0%D1%86%D0%B8%D1%8F)
   - [Статистика](https://github.com/IvanPavlovets/job4j_url_shortcut/tree/master#%D1%81%D1%82%D0%B0%D1%82%D0%B8%D1%81%D1%82%D0%B8%D0%BA%D0%B0)
## О проекте
Чтобы обеспечить безопасность пользователей, все ссылки на сайте заменяются ссылками на наш сервис.<br>
Пользователь регистрирует свой сайт после чего получает логин (url сайта) и пароль (уникальный ключ).<br>

После авторизации пользователь получает токен, ключ доступа к регистрации ссылок (функционал сервиса),<br>
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
### Регистрация сайта.
Сервисом могут пользоваться разные сайты. <br>
Каждому сайту выдается пару пароль и логин. <br>

Чтобы зарегистрировать сайт в систему нужно отправить запрос: <br> 
POST /registration <br>
C телом JSON объекта. <br>
>{ <br>
>  "site": "job4j.ru" <br>
>} <br>

Ответ от сервера:<br>
>{ <br>
>  "password": "УНИКАЛЬНЫЙ_КОД", <br>
>  "registration": "true/false", <br>
>  "login": "job4j.ru" <br>
>} <br>

Флаг registration указывает, что регистрация выполнена или нет, то есть сайт уже есть в системе. <br>

![](images/registration.jpg) <br>
Рисунок 1. запрос на регистрацию и ответ со статусом 200 (выполнен удачно) <br>
### Авторизация.
Авторизацию реализованна через JWT. <br>
Пользователь отправляет POST запрос с login (url сайта) и password (уникальный ключ) <br>
и получает токен, ключ доступа к регистрации ссылок (функционал сервиса) <br>

Этот ключ направляеться в запросе в блоке HEAD. <br>

![](images/login.jpg) <br>
Рисунок 2. запрос на авторизацию пользователя и ответ <br>

Без авторизации доступны запросы: <br>
POST /registration<br>
GET /redirect/УНИКАЛЬНЫЙ_КОД_ССЫЛКИ. <br>
### Регистрация URL.
После регистрации сайта, на него можно отправлять ссылки и в ответ <br>
получать ассоциированный адрес (код ключ ссылки). <br>

Запрос выглядит так: <br>
POST /convert <br>
C телом JSON объекта. <br>
>{ <br>
>  "url": "https://job4j.ru/profile/exercise/106/task-view/531" <br>
>} <br>

Ответ от сервера, c телом JSON объекта. <br>
>{ <br>
>  "code": "WJIjL" <br>
>} <br>
>
![](images/convert.jpg) <br> 
Рисунок 3. запрос на регистрацию ссылки и ответ со статусом 200 (выполнен удачно) <br>
### Переадресация.
Когда сайт отправляет ссылку с кодом в ответ вернеться ассоциированный адрес и статус 302. <br>
Запрос выглядит так: <br>
GET /redirect/УНИКАЛЬНЫЙ_КОД <br>
Ответ от сервера в заголовке. <br>
HTTP CODE - 302 REDIRECT URL <br>

![](images/redirect.jpg) <br>
Рисунок 4. запрос на Переадресацию и ответ со статусом 302 (перенаправление). <br>
### Статистика.
В сервисе считается количество вызовов каждого адреса. <br>
По сайту можно получить статистку, считается количество вызовов каждой ссылки. <br>
Статистика выходит только по адресам которые пренадлежат авторизованному пользователю. <br>
Запрос выглядит так: <br>
GET /statistic <br>
Ответ от сервера, c телом JSON объекта. <br>
>{ <br>
>  "url" : "https://job4j.ru/profile/exercise/106/task-view/531", total : 103} <br>
>} <br>

![](images/statistic.jpg) <br>
Рисунок 5. запрос/ответ статистика по ссылкам. <br>