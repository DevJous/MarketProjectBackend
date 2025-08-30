package com.market.project.exception;

public class BaseException extends RuntimeException {
    private final String type;
    
    public BaseException(String message, String type) {
        super(message);
        this.type = type;
    }
    
    public String getType() {
        return type;
    }
}

class ResourceNotFoundException extends BaseException {
    public ResourceNotFoundException(String message) {
        super(message, "RESOURCE_NOT_FOUND");
    }
}

class ValidationException extends BaseException {
    public ValidationException(String message) {
        super(message, "VALIDATION_ERROR");
    }
}

class BusinessException extends BaseException {
    public BusinessException(String message) {
        super(message, "BUSINESS_ERROR");
    }
    
    public BusinessException(String message, String type) {
        super(message, type);
    }
}

class AuthenticationException extends BaseException {
    public AuthenticationException(String message) {
        super(message, "AUTHENTICATION_ERROR");
    }
}

class AuthorizationException extends BaseException {
    public AuthorizationException(String message) {
        super(message, "AUTHORIZATION_ERROR");
    }
}