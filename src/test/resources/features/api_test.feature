Feature: Pruebas de API para Relatos de Papel

  Scenario: Obtener un libro por ID
    Given la API de libros está disponible
    When realizo una petición GET a "https://personal-anlcb4ao.outsystemscloud.com/Relatosdepapel/rest/v1/getLibro?id=3"
    Then el código de respuesta debe ser 200
    And la respuesta debe contener el campo "titulo"

  Scenario: Buscar libros por palabra clave
    Given la API de libros está disponible
    When realizo una petición GET a "https://personal-anlcb4ao.outsystemscloud.com/Relatosdepapel/rest/v1/libros?search=novela"
    Then el código de respuesta debe ser 200
    And la respuesta debe contener al menos un libro

  Scenario: Obtener la lista de pedidos
    Given la API de pedidos está disponible
    When realizo una petición GET a "https://personal-anlcb4ao.outsystemscloud.com/Relatosdepapel/rest/v1/pedidos"
    Then el código de respuesta debe ser 200
    And la respuesta debe contener el campo "pedidos"

  Scenario: Flujo completo de CRUD para libros
    Given la API de libros está disponible
    When realizo una búsqueda de libros con "testlibro2"
    Then la respuesta no debe contener ningún libro

    When creo un libro con título "testLibro2", autor "Autor de prueba" y año "2024"
    Then el código de respuesta debe ser 200

    When actualizo el libro con título "testLibro1" a "testLibroEditado"
    Then el código de respuesta debe ser 200

    When elimino el libro con título "testLibroEditado"
    Then el código de respuesta debe ser 200




