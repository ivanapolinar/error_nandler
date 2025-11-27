# Contrato de errores de cara al cliente

Este documento aclara qué puede esperar un cliente (frontend, integrador)
cuando ocurre un error.

- Para errores 4xx:
  - Mensajes claros y accionables.
  - Explicar qué campo o condición generó el error cuando aplique.
- Para errores 5xx:
  - Mensaje genérico para el cliente.
  - No exponer detalles internos (stacktrace, consultas SQL, rutas internas).
- Mantener la estructura estándar de respuesta de error (`error` + `meta`).

Cualquier cambio en la estructura o códigos de error debe considerarse
parte del contrato de la API y versionarse adecuadamente.
