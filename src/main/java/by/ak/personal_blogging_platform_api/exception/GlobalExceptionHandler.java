package by.ak.personal_blogging_platform_api.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ErrorResponse> handleNoSuchElementException(NoSuchElementException ex,
			HttpServletRequest request) {

		log.error("Element not found " + request.getRequestURI(), ex);

		ErrorResponse errorResponse = new ErrorResponse(ZonedDateTime.now(), HttpStatus.NOT_FOUND.value(),
				ex.getMessage(), Map.of(), request.getMethod(), request.getRequestURI());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleInvalidArgument(MethodArgumentNotValidException ex,
			HttpServletRequest request) {

		log.error("Validation error " + request.getRequestURI(), ex);

		Map<String, String> errorMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errorMap.put(error.getField(), error.getDefaultMessage());
		});

		ErrorResponse errorResponse = new ErrorResponse(ZonedDateTime.now(), HttpStatus.BAD_REQUEST.value(),
				"Validation failed", errorMap, request.getMethod(), request.getRequestURI());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex,
			HttpServletRequest request) {

		log.error("Validation error " + request.getRequestURI(), ex);

		Map<String, String> errorMap = new HashMap<>();

		ErrorResponse errorResponse = new ErrorResponse(ZonedDateTime.now(), HttpStatus.BAD_REQUEST.value(),
				"Validation failed", errorMap, request.getMethod(), request.getRequestURI());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, HttpServletRequest request) {

		log.error("An error occurred " + request.getRequestURI(), ex);

		ErrorResponse errorResponse = new ErrorResponse(ZonedDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"An unexpected error occurred", Map.of("ex", ex.getCause().getCause().getMessage()),
				request.getMethod(), request.getRequestURI());
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

@NoArgsConstructor
@AllArgsConstructor
@Data
class ErrorResponse {
	private ZonedDateTime timestamp;
	private int status;
	private String message;
	private Map<String, String> errors;
	private String method;
	private String path;

}
