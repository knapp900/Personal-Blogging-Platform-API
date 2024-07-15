package by.ak.personal_blogging_platform_api.service.publication;

import java.time.LocalDate;
import java.util.List;

import by.ak.personal_blogging_platform_api.entity.publcationEntity.Tag;
import by.ak.personal_blogging_platform_api.entity.publcationEntity.dto.PublicationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PublicationSearchService {

    Page<PublicationDto> findAll(Pageable pageable);
    Page<PublicationDto> findByPublishedDate(LocalDate date, Pageable pageable);
    Page<PublicationDto> findByContentContaining(String words, Pageable pageable);
    Page<PublicationDto> findByTags(List<Tag> tags, Pageable pageable);

    
    
}
