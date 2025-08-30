package com.market.project.exception;

import com.market.project.model.ErrorModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Manejo de excepciones personalizadas que extienden BaseException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorModel> handleResourceNotFound(ResourceNotFoundException ex) {
        logger.warn("Resource not found: {}", ex.getMessage());
        
        ErrorModel errorResponse = new ErrorModel(0, ex.getType(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorModel> handleValidation(ValidationException ex) {
        logger.warn("Validation error: {}", ex.getMessage());
        
        ErrorModel errorResponse = new ErrorModel(0, ex.getType(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorModel> handleBusiness(BusinessException ex) {
        logger.warn("Business error: {}", ex.getMessage());
        
        ErrorModel errorResponse = new ErrorModel(0, ex.getType(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorModel> handleAuthentication(AuthenticationException ex) {
        logger.warn("Authentication error: {}", ex.getMessage());
        
        ErrorModel errorResponse = new ErrorModel(0, ex.getType(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<ErrorModel> handleAuthorization(AuthorizationException ex) {
        logger.warn("Authorization error: {}", ex.getMessage());
        
        ErrorModel errorResponse = new ErrorModel(0, ex.getType(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    // Manejo de errores de validación de Bean Validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorModel> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        logger.warn("Validation error in request body");
        
        StringBuilder errors = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            if (errors.length() > 0) errors.append(", ");
            errors.append(error.getField()).append(": ").append(error.getDefaultMessage());
        });
        
        ErrorModel errorResponse = new ErrorModel(0, "VALIDATION_ERROR", errors.toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Manejo de IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorModel> handleIllegalArgument(IllegalArgumentException ex) {
        logger.warn("Illegal argument: {}", ex.getMessage());
        
        ErrorModel errorResponse = new ErrorModel(0, "INVALID_ARGUMENT", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Manejo de NullPointerException
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorModel> handleNullPointer(NullPointerException ex) {
        logger.error("Null pointer exception", ex);
        
        ErrorModel errorResponse = new ErrorModel(0, "NULL_POINTER", "Error interno: referencia nula");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Manejo de RuntimeException genérica
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorModel> handleRuntimeException(RuntimeException ex) {
        logger.error("Runtime exception", ex);
        
        ErrorModel errorResponse = new ErrorModel(0, "RUNTIME_ERROR", "Error en tiempo de ejecución");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Manejo general de todas las demás excepciones
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorModel> handleGlobalException(Exception ex, WebRequest request) {
        logger.error("Unexpected error occurred", ex);
        
        ErrorModel errorResponse = new ErrorModel(
            0, 
            "INTERNAL_SERVER_ERROR", 
            "Ha ocurrido un error interno del servidor"
        );
        
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
