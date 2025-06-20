package com.alphafolio.backend.config.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

public class ApiResponseBuilder {

    // Base structure for all responses
    private static Map<String, Object> baseBody(HttpStatus status, String message) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", ZonedDateTime.now());
        body.put("status", status.value());
        body.put("message", message);
        return body;
    }

    private static <T> ResponseEntity<Object> buildResponse(HttpStatus status, String message, T data) {
        Map<String, Object> body = baseBody(status, message);
        if (data != null) body.put("data", data);
        return new ResponseEntity<>(body, status);
    }

    // 200 OK
    public static ResponseEntity<Object> success(String message) {
        return buildResponse(HttpStatus.OK, message, null);
    }

    public static <T> ResponseEntity<Object> success(String message, T data) {
        return buildResponse(HttpStatus.OK, message, data);
    }

    // 201 Created
    public static ResponseEntity<Object> created(String message) {
        return buildResponse(HttpStatus.CREATED, message, null);
    }

    public static <T> ResponseEntity<Object> created(String message, T data) {
        return buildResponse(HttpStatus.CREATED, message, data);
    }

    // 204 No Content (rarely used with body)
    public static ResponseEntity<Object> noContent(String message) {
        return buildResponse(HttpStatus.NO_CONTENT, message, null);
    }

    // 400 Bad Request
    public static ResponseEntity<Object> badRequest(String message) {
        return buildResponse(HttpStatus.BAD_REQUEST, message, null);
    }

    public static <T> ResponseEntity<Object> badRequest(String message, T data) {
        return buildResponse(HttpStatus.BAD_REQUEST, message, data);
    }

    // 401 Unauthorized
    public static ResponseEntity<Object> unauthorized(String message) {
        return buildResponse(HttpStatus.UNAUTHORIZED, message, null);
    }

    // 403 Forbidden
    public static ResponseEntity<Object> forbidden(String message) {
        return buildResponse(HttpStatus.FORBIDDEN, message, null);
    }

    // 404 Not Found
    public static ResponseEntity<Object> notFound(String message) {
        return buildResponse(HttpStatus.NOT_FOUND, message, null);
    }

    // 409 Conflict
    public static ResponseEntity<Object> conflict(String message) {
        return buildResponse(HttpStatus.CONFLICT, message, null);
    }

    // 500 Internal Server Error
    public static ResponseEntity<Object> error(String message) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, message, null);
    }

    public static <T> ResponseEntity<Object> error(String message, T data) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, message, data);
    }

    // Custom Status Support
    public static ResponseEntity<Object> custom(HttpStatus status, String message) {
        return buildResponse(status, message, null);
    }

    public static <T> ResponseEntity<Object> custom(HttpStatus status, String message, T data) {
        return buildResponse(status, message, data);
    }
}


// use the ApiResponseBuilder in controller like this
//return ApiResponseBuilder.success("User fetched", userDTO);
//return ApiResponseBuilder.badRequest("Missing required fields");
//return ApiResponseBuilder.error("Unexpected failure", exceptionDetails);
//return ApiResponseBuilder.created("Project created", savedProjectDTO);
