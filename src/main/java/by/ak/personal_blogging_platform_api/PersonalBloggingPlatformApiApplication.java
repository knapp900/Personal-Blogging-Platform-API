package by.ak.personal_blogging_platform_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PersonalBloggingPlatformApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalBloggingPlatformApiApplication.class, args);
	}

}

//TODO (High) Add Publication controller
//TODO (Medium) Add security and setup accessibility to and points first basic auth after JWT
//TODO (Medium) Add tests
//TODO (low) Add ability for comentation for authorizat
//TODO (High) Create DTO for User