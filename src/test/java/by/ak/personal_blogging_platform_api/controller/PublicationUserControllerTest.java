package by.ak.personal_blogging_platform_api.controller;

import by.ak.personal_blogging_platform_api.entity.publcationEntity.Tag;
import by.ak.personal_blogging_platform_api.entity.publcationEntity.dto.PublicationDto;
import by.ak.personal_blogging_platform_api.service.publication.PublicationUserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class PublicationUserControllerTest {

    @Mock
    PublicationUserService service;

    @InjectMocks
    PublicationUserController controller;

    @Test
    @DisplayName("createPublication возвращает новую публикацию")
    void createPublication_RequestIsValid_ReturnNewPublication() {
        //given
        PublicationDto expectedPublication = new PublicationDto(
                1L,
                "title",
                "content",
                1L,
                "user",
                List.of(new Tag(1L, "#JAVA")));

        when(this.service.createOwnPublication(expectedPublication)).thenReturn(expectedPublication);
        //when
        ResponseEntity<PublicationDto> actualPublication = this.controller.createPublication(expectedPublication);
        //then
        assertEquals(expectedPublication, actualPublication.getBody());
        verify(this.service, times(1)).createOwnPublication(expectedPublication);
        verifyNoMoreInteractions(this.service);

    }

    @Test
    @DisplayName("getPublication возвращает публикацию")
    void getPublication_RequestIsValid_ReturnPublication() {
        //given
        Long publicationId = 1L;
        PublicationDto expectedPublication = new PublicationDto(
                1L,
                "title",
                "content",
                1L,
                "user",
                List.of(new Tag(1L, "#JAVA")));

        when(this.service.getOwnPublicationById(publicationId)).thenReturn(expectedPublication);
        //when

        ResponseEntity<PublicationDto> actualPublication = this.controller.getPublication(publicationId);

        //then

        assertEquals(expectedPublication, actualPublication.getBody());
        verify(this.service, times(1)).getOwnPublicationById(publicationId);
        verifyNoMoreInteractions(this.service);
    }

    @Test
    @DisplayName("getAllPublication возвращает все публикации")
    void getAllPublication_RequestIsValid_ReturnAllPublications() {
        //given
        List<PublicationDto> expectedPublications = Arrays.asList(new PublicationDto(
                1L,
                "title",
                "content",
                1L,
                "user",
                List.of(new Tag(1L, "#JAVA"))));

        when(this.service.getAllOwnPublications()).thenReturn(expectedPublications);

        //when
        ResponseEntity<List<PublicationDto>> actualPublications = this.controller.getAllPublication();
        //then

        assertEquals(expectedPublications, actualPublications.getBody());
        verify(this.service, times(1)).getAllOwnPublications();
        verifyNoMoreInteractions(this.service);
    }

    @Test
    @DisplayName("updatePublication возвращает обнавленную публикацию")
    void updatePublication_RequestIsValid_ReturnUpdatedPublication() {
        //given
        Long publicationId = 1L;
        PublicationDto expectedPublication = new PublicationDto(
                1L,
                "title",
                "content",
                1L,
                "user",
                List.of(new Tag(1L, "#JAVA")));
        when(this.service.updateOwnPublication(publicationId, expectedPublication)).thenReturn(expectedPublication);
        //when
        ResponseEntity<PublicationDto> actualPublication = this.controller.updatePublication(publicationId, expectedPublication);
        //then
        assertEquals(expectedPublication, actualPublication.getBody());
        verify(this.service, times(1)).updateOwnPublication(publicationId, expectedPublication);
        verifyNoMoreInteractions(this.service);
    }

    @Test
    @DisplayName("deletePublication удаляет публикацию")
    void deletePublication_RequestIsValid_ReturnNoContent() {
        //given
        Long publicationId = 1L;
        doNothing().when(this.service).deleteOwnPublication(publicationId);
        //when
        ResponseEntity<Void> response = this.controller.deletePublication(publicationId);
        //then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(this.service, times(1)).deleteOwnPublication(publicationId);
        verifyNoMoreInteractions(this.service);
    }
}
