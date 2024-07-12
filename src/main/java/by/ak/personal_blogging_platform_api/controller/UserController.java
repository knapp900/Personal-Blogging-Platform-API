package by.ak.personal_blogging_platform_api.controller;

import by.ak.personal_blogging_platform_api.entity.userEntity.dto.UserDto;
import by.ak.personal_blogging_platform_api.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/me")
public class UserController {
    private final UserService service;

    @GetMapping
    public UserDto getCurrentUser() {
        return service.getCurrentUser();
    }

    @PutMapping("/edit-me")
    public UserDto editUser(@Valid @RequestBody UserDto userDto) {
        return service.updateUser(userDto);
    }

    @PutMapping("/delete-me")
    public void deactivateUser() {
        service.deactivateUser();
    }

}
