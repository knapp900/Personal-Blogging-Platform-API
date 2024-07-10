package by.ak.personal_blogging_platform_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.ak.personal_blogging_platform_api.entity.userEntity.dto.UserCreationDto;
import by.ak.personal_blogging_platform_api.entity.userEntity.dto.UserDto;
import by.ak.personal_blogging_platform_api.service.common.RegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/signin/registration")
public class RegistrationController {
	
	   private final RegistrationService service;

	    @PostMapping
	    public ResponseEntity<UserDto> registration (@Valid @RequestBody UserCreationDto user) {
	        log.info("Creating user: {}", user);
	        UserDto createdUser = service.registration(user);
	        log.info("User created: {}", createdUser);
	        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	    }

}
