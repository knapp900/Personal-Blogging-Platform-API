
package by.ak.personal_blogging_platform_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PersonalBloggingPlatformApiApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(PersonalBloggingPlatformApiApplication.class, args);
	}
	
}

//TODO (High)- **PublicationAdminController**: Обработка публикаций (создание, редактирование, получение всех, получение одной, обновление, удаление).
//TODO (Medium)- Add internationalization
//TODO (High)- **LoginController**: Аутентификация пользователей (`/api/login`).
//-------------------------------------------------------------------------------------------
//TODO (Medium) Add security and setup accessibility to and points JWT
//TODO (Medium) Add tests
//TODO (Medium) Add openApi
