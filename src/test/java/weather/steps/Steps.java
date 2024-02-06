package weather.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import logger.Logger;
import org.testng.Assert;
import weather.models.Root;
import static io.restassured.RestAssured.given;

public class Steps {
    private String town;
    public static String KEY;
    private String mistakenLocationCode;
    private String mistakenKeyCode;
    private String cloudState;


    @Given("пользователь получил ключ и сохранил его")
    public void setKey() {
        //имитируется получение токена и сохранение его на период сессии
        KEY = "a33a6505ace542a289481740240502";
    }


    @When("пользователь запрашивает погоду по городу из таблицы {string}")

    public void getWeatherInCity(String string) {
        Response response = given().contentType(ContentType.JSON)
                .when()
                .get("https://api.weatherapi.com/v1/current.json?q=" + string + "&lang=ru&key=" + KEY)
                .then()
                .extract().response();
        Root root = response.as(Root.class);
        town = root.getLocation().getName();
        cloudState = String.valueOf(root.getCurrent().getCloud());
    }


    @Then("проверка, что в ответе приходит запрашиваемый город {string} и 'облачность' - не пустое поле")
    public void isAnswerOk(String string) {
        Assert.assertEquals(town, string);
        Assert.assertFalse(cloudState.isEmpty());
    }


    @When("пользователь запрашивает погоду по городу c ошибочным параметром поиска {string}")
    public void sendRequestWithMistake(String string) {
        Response response = given().contentType(ContentType.JSON)
                .when()
                .get("https://api.weatherapi.com/v1/current.json?q=" + string + "&lang=ru&key=" + Steps.KEY)
                .then()
                .extract().response();
        weather.models.error.Root er = response.as(weather.models.error.Root.class);
        mistakenLocationCode = String.valueOf(er.getError().getCode());
        System.out.println(er.getError().getMessage());
    }


    @Then("проверка, что в ответе приходит необходимый код ошибки {string}")
    public void assertionOfMistake(String string) {
        Assert.assertEquals(mistakenLocationCode, string);
    }


    @When("пользователь запрашивает погоду по городу Moscow c ошибкой содержимого ключа {string}")
    public void sendRequestWithNoCorrectKey(String string) {
        Response response = given().contentType(ContentType.JSON)
                .when()
                .get("https://api.weatherapi.com/v1/current.json?q=Moscow&lang=ru&key=" + string)
                .then()
                .extract().response();
        weather.models.error.Root er = response.as(weather.models.error.Root.class);
        mistakenKeyCode = String.valueOf(er.getError().getCode());
        Logger.info("проверка города Москва с ключом " + "\"" + string + "\" - пример работы логгера");

    }

    @Then("проверка, что в ответе приходит необходимый код ошибки в ответ на некорректный ключ {string}")
    public void assertKeyMistakeAnswer(String string) {
        Assert.assertEquals(mistakenKeyCode, string);
    }
}
