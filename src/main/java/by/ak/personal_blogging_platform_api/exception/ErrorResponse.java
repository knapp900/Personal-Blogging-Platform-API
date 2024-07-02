package by.ak.personal_blogging_platform_api.exception;

import java.time.ZonedDateTime;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ErrorResponse {
	private ZonedDateTime timestamp;
	private int status;
	private String message;
	private Map<String, String> errors;
	private String method;
	private String path;

}
