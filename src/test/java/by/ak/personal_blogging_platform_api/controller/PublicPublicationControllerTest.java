package by.ak.personal_blogging_platform_api.controller;

import by.ak.personal_blogging_platform_api.entity.publcationEntity.Tag;
import by.ak.personal_blogging_platform_api.entity.publcationEntity.dto.PublicationDto;
import by.ak.personal_blogging_platform_api.service.publication.PublicationSearchService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PublicPublicationControllerTest {

    @Mock
    private PublicationSearchService service;

    @InjectMocks
    private PublicPublicationController controller;

    @Test
    @DisplayName("getPublications возвращает все публикации")
    void getPublications_RequestIsValid_ReturnsPublications() {
        //given
        Pageable pageable = PageRequest.of(0, 10);
        PublicationDto expectedPublication = new PublicationDto(
                1L,
                "title",
                "content",
                1L,
                "user",
                List.of(new Tag(1L, "#JAVA")));
        Page<PublicationDto> expectedPublications = new PageImpl<>(Collections.singletonList(expectedPublication));
        when(this.service.findAll(pageable)).thenReturn(expectedPublications);

        //when
        ResponseEntity<Page<PublicationDto>> response = this.controller.getPublications(pageable);

        //then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPublications, response.getBody());
        verify(this.service, times(1)).findAll(pageable);
    }

    @Test
    @DisplayName("getPublicationsByDate возвращает публикации по дате")
    void getPublicationsByDate_RequestIsValid_ReturnsPublications() {
        //given
        String date = "2024-07-17";
        PublicationDto expectedPublication = new PublicationDto(
                1L,
                "title",
                "content",
                1L,
                "user",
                List.of(new Tag(1L, "#JAVA")));
        LocalDate localDate = LocalDate.parse(date);
        Pageable pageable = PageRequest.of(0, 10);
        Page<PublicationDto> expectedPublications = new PageImpl<>(Collections.singletonList(expectedPublication));
        when(this.service.findByPublishedDate(localDate, pageable)).thenReturn(expectedPublications);

        //when
        ResponseEntity<Page<PublicationDto>> response = this.controller.getPublicationsByDate(date, pageable);

        //then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPublications, response.getBody());
        verify(this.service, times(1)).findByPublishedDate(localDate, pageable);
    }

    @Test
    @DisplayName("searchPublicationsByWords возвращает публикации по словам")
    void searchPublicationsByWords_RequestIsValid_ReturnsPublications() {
        //given
        String words = "test";
        PublicationDto expectedPublication = new PublicationDto(
                1L,
                "title",
                "content",
                1L,
                "user",
                List.of(new Tag(1L, "#JAVA")));
        Pageable pageable = PageRequest.of(0, 10);
        Page<PublicationDto> expectedPublications = new PageImpl<>(Collections.singletonList(expectedPublication));
        when(service.findByContentContaining(words, pageable)).thenReturn(expectedPublications);

        //when
        ResponseEntity<Page<PublicationDto>> response = this.controller.searchPublicationsByWords(words, pageable);

        //then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPublications, response.getBody());
        verify(this.service, times(1)).findByContentContaining(words, pageable);
    }


    @Disabled
    @Test
    @DisplayName("getPublicationsByTags возвращает публикации по тегам")
    void getPublicationsByTags_RequestIsValid_ReturnsPublications() {
        //FIXME
        //given
        List<String> tagsForController = List.of("#JAVA");
        List<Tag> tags = List.of(new Tag(1L, "#JAVA"));
        PublicationDto expectedPublication = new PublicationDto(
                1L,
                "title",
                "content",
                1L,
                "user",
                List.of(new Tag(1L, "#JAVA")));
        Pageable pageable = PageRequest.of(0, 10);

        Page<PublicationDto> expectedPublications = new PageImpl<>(Collections.singletonList(expectedPublication));
        when(this.service.findByTags(tags, pageable)).thenReturn(expectedPublications);

        //when
        ResponseEntity<Page<PublicationDto>> response = this.controller.getPublicationsByTags(tagsForController, pageable);

        //then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPublications, response.getBody());
        verify(this.service, times(1)).findByTags(tags, pageable);
    }
}
