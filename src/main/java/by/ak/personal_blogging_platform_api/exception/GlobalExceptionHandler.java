package by.ak.personal_blogging_platform_api.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ErrorResponse> handleNoSuchElementException(NoSuchElementException ex,
			HttpServletRequest request) {

		log.error("Element not found " + request.getRequestURI(), ex);

		ErrorResponse errorResponse = ErrorResponse.builder()
				.timestamp(ZonedDateTime.now())
				.status(HttpStatus.NOT_FOUND.value())
				.message("Element not found")
				.errors(Map.of("error", ex.getMessage()))
				.method(request.getMethod())
				.path(request.getRequestURI())
				.build();

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

//	@ExceptionHandler(DataIntegrityViolationException.class)
//	public ResponseEntity<ErrorResponse> handleDataViolation(DataIntegrityViolationException ex,
//			HttpServletRequest request) {
//
//		log.error("Database error " + request.getRequestURI(), ex);
//
//		ErrorResponse errorResponse = ErrorResponse.builder()
//				.timestamp(ZonedDateTime.now())
//				.status(HttpStatus.BAD_REQUEST.value())
//				.message("Validation failed")
//				.errors(Map.of("error", ex.getRootCause().getMessage()))
//				.method(request.getMethod())
//				.path(request.getRequestURI())
//				.build();
//
//		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleInvalidArgument(MethodArgumentNotValidException ex,
			HttpServletRequest request) {

		log.error("Validation error " + request.getRequestURI(), ex);

		Map<String, String> errorMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errorMap.put(error.getField(), error.getDefaultMessage());
		});

		ErrorResponse errorResponse = ErrorResponse.builder()
				.timestamp(ZonedDateTime.now())
				.status(HttpStatus.BAD_REQUEST.value())
				.message("Validation failed").errors(errorMap)
				.method(request.getMethod())
				.path(request.getRequestURI())
				.build();

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, HttpServletRequest request) {

		log.error("An error occurred " + request.getRequestURI(), ex);

		String causeMessage = (ex.getCause() != null) ? ex.getCause().getLocalizedMessage() : ex.getMessage();

		ErrorResponse errorResponse = ErrorResponse.builder()
				.timestamp(ZonedDateTime.now())
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.message("An unexpected error occurred")
				.errors(Map.of("error", causeMessage))
				.method(request.getMethod())
				.path(request.getRequestURI())
				.build();
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
