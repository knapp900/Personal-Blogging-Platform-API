package by.ak.personal_blogging_platform_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import by.ak.personal_blogging_platform_api.service.impl.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

	private final CustomUserDetailsService userDetailsService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/api/signin/registration").permitAll() 
				.requestMatchers("/api/admin/**").hasAuthority("ROLE_ADMIN") // Только для пользователей с ролью ADMIN
				.requestMatchers("/api/me").hasAuthority("ROLE_USER") //																						// путь
				.anyRequest().authenticated() // Остальные запросы требуют аутентификации
		).csrf().disable() // Отключаем CSRF (опционально)
				.httpBasic(); // Используем базовую аутентификацию

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
		authManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		return authManagerBuilder.build();
	}
	
	
}
