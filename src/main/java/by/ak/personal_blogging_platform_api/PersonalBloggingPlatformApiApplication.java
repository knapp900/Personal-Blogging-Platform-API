
package by.ak.personal_blogging_platform_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PersonalBloggingPlatformApiApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(PersonalBloggingPlatformApiApplication.class, args);
	}
	
}
//TODO (High)- Добавить префикс "api/blog" во все контроллеры
//TODO (High)- Добавить публичный контроллер "api/blog" (чтение всех статей, поиск по содержимому title,--поиск по тегам--).
//TODO (High)- Проверить возвращаемые статусы в контроллерах
//TODO (High)- Добавить тэги в публикацию
//TODO (High)- **PublicationAdminController**: Обработка публикаций (создание, редактирование, получение всех, получение одной, обновление, удаление).
//TODO (Medium)- Add internationalization
//TODO (High)- **LoginController**: Аутентификация пользователей (`/api/login`).
//-------------------------------------------------------------------------------------------
//TODO (Medium) Add security and setup accessibility to and points JWT
//TODO (Medium) Add tests
//TODO (Medium) Add openApi
