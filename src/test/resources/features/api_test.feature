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
