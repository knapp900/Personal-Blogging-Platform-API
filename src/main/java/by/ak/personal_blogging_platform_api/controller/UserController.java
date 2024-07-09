package by.ak.personal_blogging_platform_api.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.ak.personal_blogging_platform_api.entity.userEntity.dto.UserDto;
import by.ak.personal_blogging_platform_api.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/me")
public class UserController {
//TODO (Medium) Add deactivate method to UserController
	private final UserService service;

	@GetMapping
	public UserDto getCurrentUser() {

		return service.getCurrentUser();
	}

	@PutMapping("/edit-me")
	public UserDto editUser(@Valid @RequestBody UserDto userDto) {
		return service.updateUser(getCurrentUser().id(), userDto);
	}

	@DeleteMapping
	public void deactivateUser() {
		service.deactivateUser(getCurrentUser().id());
	}

}
