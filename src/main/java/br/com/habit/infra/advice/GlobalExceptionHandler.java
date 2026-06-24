package br.com.habit.infra.advice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiErrorResponse> handleGenericException(
      Exception exception, HttpServletRequest request) {
    ApiErrorResponse apiError =
        new ApiErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            exception.getMessage(),
            request.getRequestURI(),
            null,
            LocalDateTime.now());

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
  }

  @ExceptionHandler(ApiException.class)
  public ResponseEntity<ApiErrorResponse> handleApiException(
      ApiException exception, HttpServletRequest request) {
    ApiErrorResponse apiError =
        new ApiErrorResponse(
            exception.getStatus().value(),
            exception.getMessage(),
            request.getRequestURI(),
            null,
            LocalDateTime.now());

    return ResponseEntity.status(exception.getStatus()).body(apiError);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiErrorResponse> handleValidationErrors(
      MethodArgumentNotValidException exception, HttpServletRequest request) {
    List<Map<String, Object>> errors =
        exception.getBindingResult().getFieldErrors().stream()
            .map(
                e -> {
                  Map<String, Object> error = new java.util.HashMap<>();
                  error.put("field", e.getField());
                  error.put(
                      "message",
                      e.getDefaultMessage() != null ? e.getDefaultMessage() : e.getCode());
                  error.put("rejectedValue", e.getRejectedValue());
                  return error;
                })
            .toList();

    ApiErrorResponse apiError =
        new ApiErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Validation failed",
            request.getRequestURI(),
            errors,
            LocalDateTime.now());

    return ResponseEntity.badRequest().body(apiError);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ApiErrorResponse> handleDataIntegrityViolation(
      DataIntegrityViolationException exception, HttpServletRequest request) {
    ApiErrorResponse error =
        new ApiErrorResponse(
            HttpStatus.CONFLICT.value(),
            "Data integrity violation",
            request.getRequestURI(),
            null,
            LocalDateTime.now());

    return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
  }
}
