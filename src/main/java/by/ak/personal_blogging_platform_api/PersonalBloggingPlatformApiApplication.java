
package by.ak.personal_blogging_platform_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PersonalBloggingPlatformApiApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(PersonalBloggingPlatformApiApplication.class, args);
	}
	
}
//TODO (high)- Refresh globalException class
//TODO (High)- Add logging for user and admin
//TODO (High)- Add logging for user and admin
//TODO (High)- Add throws exceptions for user and admin
//TODO (High)- **LoginController**: Аутентификация пользователей (`/api/login`).
//TODO (High)- **PublicationController**: Обработка публикаций (создание, редактирование, получение всех, получение одной, обновление, удаление).
//TODO (High)- **AuthorController**: Обработка авторов (создание, получение всех, получение одного, обновление, удаление).
//-------------------------------------------------------------------------------------------
//TODO (Medium) Add security and setup accessibility to and points JWT
//TODO (Medium) Add tests
//TODO (low) Add ability for comentation for authorizat