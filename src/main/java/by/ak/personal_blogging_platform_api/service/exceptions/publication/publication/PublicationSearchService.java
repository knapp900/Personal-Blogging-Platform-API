package by.ak.personal_blogging_platform_api.service.exceptions.publication.publication;

import java.time.LocalDate;
import java.util.List;

import by.ak.personal_blogging_platform_api.entity.publcationEntity.Publication;
import by.ak.personal_blogging_platform_api.entity.publcationEntity.Tag;

public interface PublicationSearchService {

    List<Publication> searchPublicationsByDate(LocalDate date);

    List<Publication> searchPublicationsIfContainsWord(String word);

    List<Publication> searchPublicationsByTag(List<Tag> tags);
    
    List<Publication> searchUnpublishedPublication();
    
    
}
