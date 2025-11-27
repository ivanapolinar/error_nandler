# Logging y monitoreo de errores

Este documento define cómo registrar y monitorear errores.

## Logging

- Registrar siempre:
  - `timestamp`
  - `level` (ERROR, WARN)
  - `service` y `component`
  - `correlationId` o `traceId`
  - `error.code` y mensaje
- Evitar loggear datos sensibles (passwords, tokens, tarjetas).

## Monitoreo

- Integrar los errores con una herramienta de monitoreo (por ejemplo, APM / logs centralizados).
- Definir alertas para:
  - Incremento anormal de errores 5xx.
  - Fallas recurrentes en una dependencia específica.
  - Errores de seguridad (401/403).

Usa este estándar como referencia al implementar manejadores globales de excepciones.
