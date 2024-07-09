package by.ak.personal_blogging_platform_api.controller;

import java.util.List;

import by.ak.personal_blogging_platform_api.entity.userEntity.dto.AdminUserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.ak.personal_blogging_platform_api.service.AdminUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@RestController
public class AdminUserController {
//TODO (Medium) Add findAllActiveUsers method to AdminUserController
	private final AdminUserService service;

	@GetMapping("/me")
	public AdminUserDto getCurrentUser() {

		return service.getCurrentUser();
	}

	@GetMapping("/{id}")
	public ResponseEntity<AdminUserDto> getUser(@PathVariable Long id) {
		log.info("Fetching user with id: {}", id);
		AdminUserDto user = service.getUserById(id);
		log.info("User fetched: {}", user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<AdminUserDto>> getAllUsers() {
		log.info("Fetching all users");
		List<AdminUserDto> users = service.getAllUsers();
		log.info("Users fetched: {}", users);
		return ResponseEntity.ok(users);
	}

	@PutMapping("/{id}")
	public ResponseEntity<AdminUserDto> updateUser(@Valid @PathVariable Long id, @RequestBody AdminUserDto userDetails) {
		log.info("Updating user with id: {}, details: {}", id, userDetails);
		AdminUserDto updatedUser = service.updateUser(id, userDetails);
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
	@PutMapping("/block/{id}")
	public ResponseEntity<Void> blockingUser(@PathVariable Long id) {
		log.info("Blocking user with id:{}",id);
		service.blockingUser(id);
		log.info("User with id: {} has been blocked",id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/unblock/{id}")
	public ResponseEntity<Void> unblockingUser(@PathVariable Long id) {
		log.info("Unblocking user with id:{}",id);
		service.unblockingUser(id);
		log.info("User with id: {} has been unblocked",id);
		return ResponseEntity.noContent().build();
	}
}
