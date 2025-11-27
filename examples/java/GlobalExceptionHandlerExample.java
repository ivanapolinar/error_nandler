package com.miempresa.api.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandlerExample {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(
            MethodArgumentNotValidException ex,
            WebRequest request
    ) {
        List<ValidationDetail> details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ValidationDetail(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());

        ErrorBody errorBody = new ErrorBody(
                "VALIDATION_ERROR",
                "Datos de entrada no válidos",
                details
        );

        Meta meta = new Meta(
                OffsetDateTime.now(ZoneOffset.UTC).toString(),
                "v1",
                UUID.randomUUID().toString()
        );

        ErrorResponse response = new ErrorResponse(errorBody, meta);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Aquí se pueden agregar más manejadores: ResourceNotFound, BusinessException, etc.

    public static class ValidationDetail {
        private String field;
        private String message;

        public ValidationDetail(String field, String message) {
            this.field = field;
            this.message = message;
        }

        public String getField() {
            return field;
        }

        public String getMessage() {
            return message;
        }
    }

    public static class ErrorBody {
        private String code;
        private String message;
        private List<ValidationDetail> details;

        public ErrorBody(String code, String message, List<ValidationDetail> details) {
            this.code = code;
            this.message = message;
            this.details = details;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public List<ValidationDetail> getDetails() {
            return details;
        }
    }

    public static class Meta {
        private String timestamp;
        private String version;
        private String correlationId;

        public Meta(String timestamp, String version, String correlationId) {
            this.timestamp = timestamp;
            this.version = version;
            this.correlationId = correlationId;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public String getVersion() {
            return version;
        }

        public String getCorrelationId() {
            return correlationId;
        }
    }

    public static class ErrorResponse {
        private ErrorBody error;
        private Meta meta;

        public ErrorResponse(ErrorBody error, Meta meta) {
            this.error = error;
            this.meta = meta;
        }

        public ErrorBody getError() {
            return error;
        }

        public Meta getMeta() {
            return meta;
        }
    }
}
