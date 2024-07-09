package by.ak.personal_blogging_platform_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.ak.personal_blogging_platform_api.entity.userEntity.dto.UserCreationDto;
import by.ak.personal_blogging_platform_api.entity.userEntity.dto.UserDto;
import by.ak.personal_blogging_platform_api.service.AdminUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@RestController
public class AdminUserController {

	private final AdminUserService service;

	@GetMapping("/me")
	public UserDto getCurrentUser() {

		return service.getCurrentUser();
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
		log.info("Fetching user with id: {}", id);
		UserDto user = service.getUserById(id);
		log.info("User fetched: {}", user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUsers() {
		log.info("Fetching all users");
		List<UserDto> users = service.getAllUsers();
		log.info("Users fetched: {}", users);
		return ResponseEntity.ok(users);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @PathVariable Long id, @RequestBody UserCreationDto userDetails) {
		log.info("Updating user with id: {}, details: {}", id, userDetails);
		UserDto updatedUser = service.updateUser(id, userDetails);
		log.info("User updated: {}", updatedUser);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		log.info("Deleting user with id: {}", id);
		service.deleteUser(id);
		log.info("User deleted with id: {}", id);
		return ResponseEntity.noContent().build();
	}
}
