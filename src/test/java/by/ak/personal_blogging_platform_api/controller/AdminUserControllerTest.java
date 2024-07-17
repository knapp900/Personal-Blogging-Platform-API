package by.ak.personal_blogging_platform_api.controller;

import by.ak.personal_blogging_platform_api.entity.userEntity.Role;
import by.ak.personal_blogging_platform_api.entity.userEntity.dto.AdminUserDto;
import by.ak.personal_blogging_platform_api.service.admin.AdminUserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdminUserControllerTest {

    @Mock
    AdminUserService service;

    @InjectMocks
    AdminUserController controller;

    @Test
    @DisplayName("getCurrentUser возвращает текущего пользователя")
    void getCurrentUser_RequestIsValid_ReturnCurrentUser() {
        //given
        AdminUserDto expectedUser = new AdminUserDto(
                1L,
                "testuser",
                "Sam",
                "Sam",
                "pasword",
                "sam@mail.com",
                LocalDate.of(2024, 07, 17),
                true,
                List.of(Role.USER),
                null);


        when(service.getCurrentUser()).thenReturn(expectedUser);
        //when

        AdminUserDto actualUser = this.controller.getCurrentUser();

        //then

        assertEquals(expectedUser, actualUser);
        verify(this.service, times(1)).getCurrentUser();
        verifyNoMoreInteractions(this.service);
    }

    @Test
    @DisplayName("getUser возвращает пользователя")
    void getUser_RequestIsValid_ReturnUser() {
        //given
        Long userId = 1L;
        AdminUserDto expectedUser = new AdminUserDto(
                1L,
                "testuser",
                "Sam",
                "Sam",
                "pasword",
                "sam@mail.com",
                LocalDate.of(2024, 07, 17),
                true,
                List.of(Role.USER),
                null);

        when(this.service.getUserById(userId)).thenReturn(expectedUser);

        //when

        ResponseEntity<AdminUserDto> actualUser = this.controller.getUser(userId);
        //then

        assertEquals(expectedUser, actualUser.getBody());
        verify(this.service, times(1)).getUserById(userId);
        verifyNoMoreInteractions(this.service);

    }


    @Test
    @DisplayName("getAllUsers возвращает всех пользователей")
    void getAllUsers_RequestIsValid_ReturnAllUsers() {
        //given
        List<AdminUserDto> expectedUsers = Arrays.asList(new AdminUserDto(
                1L,
                "testuser",
                "Sam",
                "Sam",
                "pasword",
                "sam@mail.com",
                LocalDate.of(2024, 07, 17),
                true,
                List.of(Role.USER),
                null), new AdminUserDto(
                1L,
                "testadmin",
                "Sam",
                "Sam",
                "pasword",
                "sam@mail.com",
                LocalDate.of(2024, 07, 17),
                true,
                List.of(Role.ADMIN),
                null));

        when(this.service.getAllUsers()).thenReturn(expectedUsers);

        //when
        ResponseEntity<List<AdminUserDto>> actualUsers = this.controller.getAllUsers();
        //then
        assertEquals(actualUsers.getBody(), expectedUsers);
        verify(this.service, times(1)).getAllUsers();
        verifyNoMoreInteractions(this.service);
    }


    @Test
    @DisplayName("updateUser возвращает обнавленного пользователя")
    void updateUser_RequestIsValid_UpdateUser() {
        //given
        Long userId = 1L;
        AdminUserDto expectedUser = new AdminUserDto(
                1L,
                "testuser",
                "Sam",
                "Sam",
                "pasword",
                "sam@mail.com",
                LocalDate.of(2024, 07, 17),
                true,
                List.of(Role.USER),
                null);

        when(this.service.updateUser(userId, expectedUser)).thenReturn(expectedUser);
        //when
        ResponseEntity<AdminUserDto> actualUser = this.controller.updateUser(userId, expectedUser);
        //then

        assertEquals(expectedUser, actualUser.getBody());
        verify(this.service, times(1)).updateUser(userId, expectedUser);
        verifyNoMoreInteractions(this.service);

    }

    @Test
    @DisplayName("deleteUser удаляет пользователя")
    void deleteUser_RequestIsValid_DeleteUser() {
        //given
        Long userId = 1L;
        doNothing().when(this.service).deleteUser(userId);

        //when
        ResponseEntity<Void> response = this.controller.deleteUser(userId);
        //then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(this.service, times(1)).deleteUser(userId);
        verifyNoMoreInteractions(this.service);
    }


    @Test
    @DisplayName("blockingUser блокирует пользователя")
    void blockingUser_RequestIsValid_BlockingUser() {
        //given
        Long userId = 1L;
        doNothing().when(this.service).blockingUser(userId);

        //when
        ResponseEntity<Void> response = this.controller.blockingUser(userId);
        //then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(this.service, times(1)).blockingUser(userId);
        verifyNoMoreInteractions(this.service);
    }

    @Test
    @DisplayName("unblockingUser разблокирует пользователя")
    void unblockingUser_RequestIsValid_UnblockingUser() {
        //given
        Long userId = 1L;
        doNothing().when(this.service).unblockingUser(userId);

        //when
        ResponseEntity<Void> response = this.controller.unblockingUser(userId);
        //then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(this.service, times(1)).unblockingUser(userId);
        verifyNoMoreInteractions(this.service);
    }
}

