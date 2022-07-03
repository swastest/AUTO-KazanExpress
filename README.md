# Проект по автоматизации тестирования интернет-магазина KazanExpress"
<p align="center">
<img title="Allure Test Page" src="images/screenshots/KznExpress.png">
</p>
 <a target="_blank" href="https://kazanexpress.ru/">KazanExpress - интернет-магазин с бесплатной доставкой за 1 день</a>

## :floppy_disk: Содержание:
- <a href="#computer-технологии-и-инструменты">Технологии и инструменты</a>
- <a href="#notebook_with_decorative_cover-реализованные-проверки">Реализованные проверки</a>
- <a href="#arrow_forward-запуск-из-терминала">Запуск из терминала</a>
- <a href="#open_book-allure-отчет">Allure отчет</a>
- <a href="#robot-отчет-в-telegram">Отчет в Telegram</a>
- <a href="#film_projector-видео-примеры-прохождения-тестов">Видео примеры прохождения тестов</a>

## :computer: Технологии и инструменты
<p align="center">
<img width="6%" title="IntelliJ IDEA" src="images/logo/Intelij_IDEA.svg">
<img width="6%" title="Java" src="images/logo/Java.svg">
<img width="6%" title="Selenide" src="images/logo/Selenide.svg">
<img width="6%" title="Selenoid" src="images/logo/Selenoid.svg">
<img width="6%" title="Allure Report" src="images/logo/Allure_Report.svg">
<img width="6%" title="Gradle" src="images/logo/Gradle.svg">
<img width="6%" title="JUnit5" src="images/logo/JUnit5.svg">
<img width="6%" title="GitHub" src="images/logo/GitHub.svg">
<img width="6%" title="Jenkins" src="images/logo/Jenkins.svg">
<img width="6%" title="Telegram" src="images/logo/Telegram.svg">
<img width="6%" title="Telegram" src="images/logo/Rest-Assured.png">
</p>

## :notebook_with_decorative_cover: Реализованные проверки
- UI Tests:
  - Авторизация зарегестрированного пользователя
  - Соответствие заголовка страницы с желаемым результатом
  - В журнале консоли нет ошибок
  - Все элементы каталога имеют атрибут 'href'

- API Tests:
  - Проверка на получение токенов при авторизации зарегестрированного пользователя
  - Зарегистрированный пользователь имеет доступ к своим контактным данным 


## :arrow_forward: Запуск из терминала

```
gradle clean test
```

## :open_book: Allure отчет
- ### Главный экран отчета
<p align="center">
<img title="Allure Overview Dashboard" src="images/screenshots/total.png">
</p>

- ### Страница с проведенными тестами, где справа можно увидеть подробно-расписанные шаги, а внизу каждого тест-кейса прикрепляются аттачи
Отчет Ui-теста:
<p align="center">
<img title="Allure Test Page" src="images/screenshots/ui_1.png">
</p>
Отчет Api-теста:
<p align="center">
<img title="Allure Test Page" src="images/screenshots/api_1.png">
</p>

## :robot: К каждому UI тесту прикрепляется аттч с видео
<p align="center">
<img title="Telegram notification message" src="images/screenshots/авт.gif">
</p>

## :robot: После прохождения тестов отправляется отчет в Telegram
<p align="center">
<img title="Telegram notification message" src="images/screenshots/photo_2022-07-03_16-40-29.jpg">
</p>


:heart: 
:blue_heart: 
