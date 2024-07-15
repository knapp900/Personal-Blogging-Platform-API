package by.ak.personal_blogging_platform_api.service.publication;

import by.ak.personal_blogging_platform_api.entity.publcationEntity.Publication;
import by.ak.personal_blogging_platform_api.entity.publcationEntity.dto.PublicationDto;
import by.ak.personal_blogging_platform_api.entity.userEntity.User;

import java.util.List;

public interface PublicationAdminService {

    List<PublicationDto> getAllPublications();

    List<PublicationDto> getAllPublicationsByUser(User user);

    PublicationDto getPublicationById(Long id);

    PublicationDto updatePublication(Long id, PublicationDto publicationDetails);

    void deletePublication(Long id);

    void publishPublication(Long id);

    void unpublishPublication(Long id);
}
