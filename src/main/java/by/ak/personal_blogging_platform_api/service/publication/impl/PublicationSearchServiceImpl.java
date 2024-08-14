package by.ak.personal_blogging_platform_api.service.publication.impl;

import by.ak.personal_blogging_platform_api.dao.CustomFindingPagebleRepository;
import by.ak.personal_blogging_platform_api.dao.TagRepository;
import by.ak.personal_blogging_platform_api.entity.publcationEntity.Tag;
import by.ak.personal_blogging_platform_api.entity.publcationEntity.dto.PublicationDto;
import by.ak.personal_blogging_platform_api.service.exceptions.publication.PublicationServiceExceptoin;
import by.ak.personal_blogging_platform_api.service.mapper.publication.PublicationDtoMapper;
import by.ak.personal_blogging_platform_api.service.publication.PublicationSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PublicationSearchServiceImpl implements PublicationSearchService {

    private final CustomFindingPagebleRepository repository;

    private final PublicationDtoMapper publicationDtoMapper;

    private final TagRepository tagRepository;

    @Override
    public Page<PublicationDto> findAll(Pageable pageable) {
        try {
            return repository.findAll(pageable)
                    .map(publicationDtoMapper::toDto);
        } catch (Exception e) {
            log.error("Error finding publications:", e);
            throw new PublicationServiceExceptoin("Failed to find publications: ", e);
        }
    }

    @Override
    public Page<PublicationDto> findByPublishedDate(LocalDate date, Pageable pageable) {
        try {
            return repository.findByPublishedDate(date, pageable)
                    .map(publicationDtoMapper::toDto);
        } catch (Exception e) {
            log.error("Error finding publications by date: {}", date, e);
            throw new PublicationServiceExceptoin("Failed to find publications by date: " + date, e);
        }
    }

    @Override
    public Page<PublicationDto> findByContentContaining(String words, Pageable pageable) {
        try {
            return repository.findByContentContaining(words, pageable)
                    .map(publicationDtoMapper::toDto);
        } catch (Exception e) {
            log.error("Error finding publications by words: {}", words, e);
            throw new PublicationServiceExceptoin("Failed to find publications by words: " + words, e);
        }
    }

    @Override
    public Page<PublicationDto> findByTags(List<Tag> tags, Pageable pageable) {
        List<Tag> tagsForSearch = new ArrayList<>();

        tags.forEach(tag -> {
            tagsForSearch.add(tagRepository.findByName(tag.getName()).orElseThrow(() ->
                    new IllegalArgumentException("Tag not found: " + tag.getName())));
        });

        try {
            return repository.findByTags(tagsForSearch, pageable)
                    .map(publicationDtoMapper::toDto);
        } catch (Exception e) {
            log.error("Error finding publications by tags: {}", tags, e);
            throw new PublicationServiceExceptoin("Failed to find publications by tags: " + tags, e);
        }
    }


}
