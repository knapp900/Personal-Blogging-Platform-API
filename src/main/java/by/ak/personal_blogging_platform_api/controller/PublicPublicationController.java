package by.ak.personal_blogging_platform_api.controller;

import by.ak.personal_blogging_platform_api.entity.publcationEntity.Publication;
import by.ak.personal_blogging_platform_api.entity.publcationEntity.Tag;
import by.ak.personal_blogging_platform_api.entity.publcationEntity.dto.PublicationDto;
import by.ak.personal_blogging_platform_api.service.exceptions.publication.PublicationServiceExceptoin;
import by.ak.personal_blogging_platform_api.service.publication.PublicationSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/blog")
@RestController
public class PublicPublicationController {
    private final PublicationSearchService publicationSearchService;

    @GetMapping
    public ResponseEntity<Page<PublicationDto>> getPublications(Pageable pageable) {
        try {
            Page<PublicationDto> publications = publicationSearchService.findAll(pageable);
            return new ResponseEntity<>(publications, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching publications by date", e);
            throw new PublicationServiceExceptoin("Error fetching publications by date", e);
        }
    }

    @GetMapping("/date")
    public ResponseEntity<Page<PublicationDto>> getPublicationsByDate(
            @RequestParam("date") String date, //FIXME не работает метод
            Pageable pageable) {
        try {
            LocalDate localDate = LocalDate.parse(date);
            Page<PublicationDto> publications = publicationSearchService.findByPublishedDate(localDate, pageable);
            return new ResponseEntity<>(publications, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching publications by date", e);
            throw new PublicationServiceExceptoin("Error fetching publications by date", e);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Page<PublicationDto>> searchPublicationsByWords(
            @RequestParam("words") String words,
            Pageable pageable) {
        try {
            Page<PublicationDto> publications = publicationSearchService.findByContentContaining(words, pageable);
            return new ResponseEntity<>(publications, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error searching publications by words", e);
            throw new PublicationServiceExceptoin("Error searching publications by words", e);
        }
    }

    @GetMapping("/tags")
    public ResponseEntity<Page<PublicationDto>> getPublicationsByTags(
            @RequestParam("tags") List<String> tags,
            Pageable pageable) {
        try {
            log.info("Fetching publications by tags: {}",tags.toString());
            log.info("Tags : {}",tags.size());
            List<Tag> tagList = tags.stream().map(tag -> {
                Tag newTag = new Tag();
                newTag.setName(tag);
                return newTag;
            }).toList();
            Page<PublicationDto> publications = publicationSearchService.findByTags(tagList, pageable);
            log.info("Fetched publications by tags: {}{}",tagList,publications);
            return new ResponseEntity<>(publications, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching publications by tags", e);
            throw new PublicationServiceExceptoin("Error fetching publications by tags", e);
        }
    }


}

