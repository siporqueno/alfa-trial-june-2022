## [Формулировка задачи](Task.md)

## Для запуска приложения вам понадобится:
- [JDK 11](https://www.oracle.com/java/technologies/downloads/#java11) или выше
- [Gradle](https://docs.gradle.org/current/userguide/installation.html)
- [Git](https://git-scm.com/)
## Инструкция по запуску:
- Загрузите приложение с помощью команды git clone https://github.com/siporqueno/alfa-trial-june-2022.git
- Зарегистрируйтесь на https://docs.openexchangerates.org/ и получите app_id
- Зарегистрируйтесь на https://giphy.com/ и получите api_key
- Установите app_id и api_key в файле application.yml
- Перепроверьте url и конечные точки для historical и для latest курсов на https://docs.openexchangerates.org/ и при необходимости внесите изменения в application.yml
- Перепроверьте url на https://developers.giphy.com/docs/api/endpoint#random и при необходимости внесите изменения в application.yml
- Запустите приложение с помощью команды ./gradlew bootRun в командной строке
## Как пользоваться приложением:
Приложение выводит два характерных вида гифок. Каждый вид соответствует одному из двух следующих вариантов:
- Крайний курс выбранной вами валюты по отношению к доллару США на сегодня вырос или не изменился по сравнению с вчерашним курсом
- Крайний курс выбранной вами валюты по отношению к доллару США на сегодня упал по сравнению с вчерашним курсом

Выберите трехбуквенный код любой валюты кроме доллара США согласно ISO 4217 (например EUR - евро, INR - индийская рупия, RUB - рубль)  
Обозначим выбранный вами код как currency_code  
Перейдите по ссылке: http://localhost:8080/api/v1/currency-change/currency_code  
Например, для евро (EUR) ссылка будет такой: http://localhost:8080/api/v1/currency-change/EUR
## Инструкция по сборке и запуску в Docker:
Сначала собрать .jar с помощью gradle build  
docker build --build-arg JAR_FILE=build/libs/*.jar -t niko/alfa-trial-june-2022 .  
docker run -p 8080:8080 -e properties.rates.app_id=<app_id_value> -e properties.gifs.api_key=<api_key_value> <IMAGE ID>  