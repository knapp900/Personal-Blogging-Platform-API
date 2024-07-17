package by.ak.personal_blogging_platform_api.controller;

import by.ak.personal_blogging_platform_api.entity.userEntity.dto.UserDto;
import by.ak.personal_blogging_platform_api.service.user.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    UserService service;

    @InjectMocks
    UserController userController;

    @Test
    @DisplayName("getCurrentUser возвращает текущего пользователя")
    void getCurrentUser_RequestIsValid_ReturnCurrentUser() {
        // given
        UserDto expectedUser = new UserDto(
                1L,
                "testuser",
                "Sam",
                "Sam",
                "sam@mail.com",
                null);


        doReturn(new UserDto(
                1L,
                "testuser",
                "Sam",
                "Sam",
                "sam@mail.com",
                null)).when(this.service).getCurrentUser();

        // when
        UserDto actualUser = this.userController.getCurrentUser();

        // then
        assertEquals(actualUser, expectedUser);
        verify(this.service, times(1)).getCurrentUser();
        verifyNoMoreInteractions(this.service);
    }

    @Test
    @DisplayName("editUser обновляет информацию пользователя")
    void editUser_RequestIsValid_UpdateUser() {
        //given
        UserDto expectedUser = new UserDto(
                1L,
                "testuser",
                "Sam",
                "Sam",
                "sam@mail.com",
                null);


        doReturn(new UserDto(
                1L,
                "testuser",
                "Sam",
                "Sam",
                "sam@mail.com",
                null)).when(this.service).updateUser(expectedUser);
        //when
        UserDto updatedUser = this.userController.editUser(expectedUser);

        //then
        assertEquals(expectedUser, updatedUser);
        verify(this.service, times(1)).updateUser(expectedUser);
        verifyNoMoreInteractions(this.service);
    }

    @Test
    @DisplayName("deactivateUser деактивирует пользователя")
    void deactivateUser_RequestIsValid_DeactivateUser() {
        // when
        this.userController.deactivateUser();

        // then
        verify(service, times(1)).deactivateUser();
        verifyNoMoreInteractions(this.service);
    }


}
