# Catálogo de errores

Este documento define categorías y códigos de error estándar que deben
utilizarse en todos los servicios.

## Categorías principales

- `CLIENT_ERROR`: errores causados por datos o peticiones inválidas.
- `BUSINESS_ERROR`: reglas de negocio no cumplidas.
- `INFRASTRUCTURE_ERROR`: fallas en recursos externos (DB, cola, APIs externas).
- `SECURITY_ERROR`: autenticación / autorización, accesos no permitidos.
- `UNEXPECTED_ERROR`: errores no controlados.

## Convención de códigos

Formato sugerido: `GRUPO_SUBGRUPO_DETALLE`, por ejemplo:

- `VALIDATION_REQUIRED_FIELD_MISSING`
- `BUSINESS_STOCK_NOT_AVAILABLE`
- `INFRASTRUCTURE_DATABASE_TIMEOUT`
- `SECURITY_AUTHENTICATION_FAILED`
- `UNEXPECTED_ERROR`

Usa estos códigos junto con el estándar de respuesta de error para mantener
consistencia entre servicios.
