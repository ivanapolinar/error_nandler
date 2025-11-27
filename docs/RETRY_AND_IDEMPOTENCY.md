# Reintentos e idempotencia

Este documento define lineamientos para reintentos y operaciones idempotentes.

## Reintentos

- Aplicar reintentos **solo** para errores transitorios (ej. timeouts, 5xx de dependencias).
- Usar backoff exponencial y número máximo de reintentos configurables.
- Evitar reintentar errores 4xx (normalmente son definitivos).

## Idempotencia

- Las operaciones que puedan re-ejecutarse (por reintentos, duplicidad en red, etc.)
  deben diseñarse como idempotentes cuando sea posible.
- Para operaciones de escritura críticas (por ejemplo, creación de órdenes), considerar:
  - Identificadores idempotentes (`Idempotency-Key`) enviados por el cliente.
  - Verificación previa de existencia antes de crear.
