Feature: Weather forecast

  Background: Пользователь регистрируется в системе и получает ключ на ресурсе https://www.weatherapi.com/
    Given пользователь получил ключ и сохранил его

  Scenario Outline: запрашивается погода по городу из таблицы и происходит проверка на корректный ответ
    When пользователь запрашивает погоду по городу из таблицы "<city>"
    Then проверка, что в ответе приходит запрашиваемый город "<city>" и 'облачность' - не пустое поле
    Examples:
      | city     |
      | Gomel    |
      | Paris    |
      | New York |
      | Tokyo    |

  Scenario Outline: негативный тест с набором неверных параметров в запросе
    When пользователь запрашивает погоду по городу c ошибочным параметром поиска "<mistake>"
    Then проверка, что в ответе приходит необходимый код ошибки "<code>"
    Examples:
      | mistake  | code |
      | *        | 1006 |
      |          | 1003 |

  Scenario Outline: негативный тест с набором неверных параметров в параметрах ключа
    When пользователь запрашивает погоду по городу Moscow c ошибкой содержимого ключа "<mistakenKey>"
    Then проверка, что в ответе приходит необходимый код ошибки в ответ на некорректный ключ "<code>"

    Examples:
      | mistakenKey| code |
      | *          | 2008 |
      |            | 1002 |

