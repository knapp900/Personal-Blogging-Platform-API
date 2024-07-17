package by.ak.personal_blogging_platform_api.controller;

import by.ak.personal_blogging_platform_api.entity.userEntity.dto.UserCreationDto;
import by.ak.personal_blogging_platform_api.entity.userEntity.dto.UserDto;
import by.ak.personal_blogging_platform_api.service.common.RegistrationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RegistrationControllerTest {
    @Mock
    RegistrationService service;

    @InjectMocks
    RegistrationController controller;

    @Test
    @DisplayName("registration регистрирует пользователя")
    void registration_RequestIsValid_ReturnRegisteredUser(){
        //given
        UserCreationDto expectedCreationUser = new UserCreationDto(
                1L,
                "testuser",
                "Sam",
                "Sam",
                "password",
                "sam@mail.com");

        UserDto expectedUser = new UserDto(
                1L,
                "testuser",
                "Sam",
                "Sam",
                "sam@mail.com",
                null);
        when(this.service.registration(expectedCreationUser)).thenReturn(expectedUser);
        //when

        ResponseEntity<UserDto> actualUser = this.controller.registration(expectedCreationUser);

        //then
        assertEquals(expectedUser,actualUser.getBody());
        verify(this.service,times(1)).registration(expectedCreationUser);
        verifyNoMoreInteractions(this.service);
    }
}

