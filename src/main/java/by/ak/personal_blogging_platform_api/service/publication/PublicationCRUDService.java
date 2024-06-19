package by.ak.personal_blogging_platform_api.service.publication;

import java.util.List;
import by.ak.personal_blogging_platform_api.entity.contributionEntity.Publication;

public interface PublicationCRUDService {

    Publication createPublication(Publication publication);

    List<Publication> getAllPublications();

    Publication getPublicationById(Long id);

    Publication updatePublication(Long id, Publication publicationDetails);

    boolean deletePublication(Long id);

    Publication publishPublication(Long id);

    Publication unpublishPublication(Long id);
}