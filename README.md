README

Заготовка фреймворка демонстрирующая возможность работы с банковскими продуктами (картами, вкладами),   
а так же API тестами (получение прогноза с сайта https://www.weatherapi.com/)
Приведены некотороые, базовые примеры работы тестов, а так же логирования, возможности запуска отчета Allure  

запуск примеров тестов по картам и депозитам возможен через:
1) [запуск тестового класса](src/test/java/BankProductTest.java)
2) [запуск посредством xml](src/test/resources/testsuites/testbank.xml)
3) через командную строку

запуск API тестов (RestAssured + Cucumber) возможен через:
1) [запуск feature класса](src/test/resources/features/forecast.feature)
2) [запуск посредством xml](src/test/resources/testsuites/cucumbtest.xml)
3) через командную строку  

логи сохраняются в раздел /logs.
Конфигурация логов (время хранения, объем, уровень итд ) производится [здесь(логгер)](src/test/java/logger/Logger.java)  
и [здесь(log4j2.xml)](src/test/resources/log4j2.xml)

Allure отчет создается обычным образом через запуск AllureServе






