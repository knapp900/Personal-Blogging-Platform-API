package by.ak.personal_blogging_platform_api.service.publication;

import by.ak.personal_blogging_platform_api.entity.publcationEntity.Publication;
import by.ak.personal_blogging_platform_api.entity.userEntity.User;

import java.util.List;

public interface PublicationCrudServiceOfRawEntity {

    Publication createPublication(Publication publication,User creator);

    List<Publication> getAllPublications();

    List<Publication> getAllPublicationsByUser(User user);

    Publication getPublicationById(Long id);

    Publication updatePublication(Long id, Publication publicationDetails);

    Publication findByUserAndId(User user,Long publicationId);
    void deletePublication(Long id);

    void publishPublication(Long id);

    void unpublishPublication(Long id);


}
