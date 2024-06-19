package by.ak.personal_blogging_platform_api.service.publication;

import java.time.LocalDate;
import java.util.List;

import by.ak.personal_blogging_platform_api.entity.contributionEntity.Publication;
import by.ak.personal_blogging_platform_api.entity.contributionEntity.Tag;

public interface PublicationSearchService {

    List<Publication> searchPublicationsByDate(LocalDate date);

    List<Publication> searchPublicationsIfContainsWord(String word);

    List<Publication> searchPublicationsByTag(List<Tag> tags);
    
    List<Publication> searchUnpublishedPublication();
    
    
}
