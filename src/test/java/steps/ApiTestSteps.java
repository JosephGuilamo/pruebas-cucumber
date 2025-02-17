package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.junit.Assert.*;
import static io.restassured.RestAssured.*;
import java.util.List;
import java.util.Map;


public class ApiTestSteps {

    private Response response;
    private final String BASE_URL = "https://personal-anlcb4ao.outsystemscloud.com/Relatosdepapel/rest/v1";
    private List<Map<String, Object>> libros;
    private int createdBookId;

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

    @When("realizo una búsqueda de libros con {string}")
    public void realizo_una_busqueda_de_libros_con(String searchQuery) {
        response = given().when().get(BASE_URL + "/libros?search=" + searchQuery);


        libros = response.jsonPath().getList("$");

        // Debug para verificar la respuesta
        System.out.println("Respuesta del servidor: " + response.getBody().asString());
        System.out.println("Cantidad de libros encontrados: " + libros.size());
    }

    @Then("la respuesta no debe contener ningún libro")
    public void la_respuesta_no_debe_contener_ningun_libro() {
        assertNotNull("La respuesta no debería ser nula", libros);
        assertTrue("Se encontraron libros cuando no debería haber ninguno", libros.isEmpty());
    }

    // Step para crear un libro
    @When("creo un libro con título {string}, autor {string} y año {string}")
    public void creo_un_libro(String titulo, String autor, String año) {
        response = given()
                .contentType("application/json")
                .body("{\"titulo\":\"" + titulo + "\", \"autor\":\"" + autor + "\", \"año\":\"" + año + "\"}")
                .when()
                .post("https://personal-anlcb4ao.outsystemscloud.com/Relatosdepapel/rest/v1/crearLibro");

        createdBookId = Integer.parseInt(response.getBody().asString().trim());
    }

    @When("actualizo el libro con título {string} a {string}")
    public void actualizo_el_libro(String tituloViejo, String tituloNuevo) {
        response = given()
                .contentType("application/json")
                .body("{\"titulo\":\"" + tituloNuevo + "\"}")
                .when()
                .patch("https://personal-anlcb4ao.outsystemscloud.com/Relatosdepapel/rest/v1/actualizarLibro?id=" + createdBookId);
    }

    @When("elimino el libro con título {string}")
    public void elimino_el_libro(String titulo) {
        response = given()
                .when()
                .delete("/borrarLibro?id=" + createdBookId);
    }


}
