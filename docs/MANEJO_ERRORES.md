# Error Handling in API Tienda

Este documento describe la estrategia de manejo de errores para la aplicación API Tienda. Un manejo adecuado de errores es fundamental para brindar una buena experiencia de usuario y para depurar problemas de manera eficaz.

## Global Exception Handling

La aplicación utiliza un gestor de excepciones global para administrar las excepciones que genera. Esto se implementa en la clase `GlobalExceptionHandler`, ubicada en el paquete `exception`.

### Ejemplo de Global Exception Handler

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseDTO> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ResponseDTO response = new ResponseDTO("ERROR", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> handleGenericException(Exception ex) {
        ResponseDTO response = new ResponseDTO("ERROR", "An unexpected error occurred.");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
```

## Custom Exceptions

Deben crearse excepciones personalizadas para escenarios de error específicos. Por ejemplo, la excepción `ResourceNotFoundException` se utiliza cuando no se encuentra un recurso solicitado.

### Ejemplo de Custom Exception

```java
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
```

## Response Structure

Todas las respuestas de error deben seguir una estructura coherente. La clase `ResponseDTO` se utiliza para estandarizar el formato de respuesta.

### Example of ResponseDTO

```java
public class ResponseDTO {
    private String status;
    private String message;

    public ResponseDTO(String status, String message) {
        this.status = status;
        this.message = message;
    }

    // Getters and Setters
}
```

## HTTP Status Codes

Utilice los códigos de estado HTTP adecuados para los diferentes escenarios de error:

- **404 No encontrado**: Cuando no se encuentra un recurso.
- **500 Error interno del servidor**: Para errores inesperados.
- **400 Solicitud incorrecta**: Para errores de validación o datos de entrada incorrectos.
## Registro de errores
Es importante registrar los errores para facilitar la depuración. Utilice un marco de registro como SLF4J con Logback o Log4j para registrar los mensajes de error.

### Example of Logging

```java
private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

@ExceptionHandler(Exception.class)
public ResponseEntity<ResponseDTO> handleGenericException(Exception ex) {
    logger.error("An unexpected error occurred: {}", ex.getMessage());
    ResponseDTO response = new ResponseDTO("ERROR", "An unexpected error occurred.");
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
}
```

## Conclusion

Siguiendo estas directrices para el manejo de errores, la aplicación API Tienda proporcionará mensajes de error claros y coherentes a los clientes, lo que facilitará la comprensión y resolución de problemas.