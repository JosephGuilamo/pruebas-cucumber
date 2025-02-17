package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.junit.Assert.*;
import static io.restassured.RestAssured.*;

public class ApiTestSteps {

    private Response response;
    private final String BASE_URL = "https://personal-anlcb4ao.outsystemscloud.com/Relatosdepapel/rest/v1";

    @Given("la API de libros está disponible")
    public void la_api_de_libros_esta_disponible() {
        RestAssured.baseURI = BASE_URL;
    }

    @Given("la API de pedidos está disponible")
    public void la_api_de_pedidos_esta_disponible() {
        RestAssured.baseURI = BASE_URL;
    }


    @When("realizo una petición GET a {string}")
    public void realizo_una_peticion_get_a(String endpoint) {
        response = given().when().get(endpoint);
    }

    @Then("el código de respuesta debe ser {int}")
    public void el_codigo_de_respuesta_debe_ser(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }

    @Then("la respuesta debe contener el campo {string}")
    public void la_respuesta_debe_contener_el_campo(String field) {
        assertNotNull(response.jsonPath().get(field));
    }

    @Then("la respuesta debe contener al menos un libro")
    public void la_respuesta_debe_contener_al_menos_un_libro() {
        assertTrue(response.jsonPath().getList("$").size() > 0);
    }
}
