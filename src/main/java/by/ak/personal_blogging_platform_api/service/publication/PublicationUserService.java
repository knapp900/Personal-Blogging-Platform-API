package by.ak.personal_blogging_platform_api.service.publication;

import java.util.List;
import by.ak.personal_blogging_platform_api.entity.publcationEntity.dto.PublicationDto;

public interface PublicationUserService {

	PublicationDto createOwnPublication(PublicationDto publication);

    List<PublicationDto> getAllOwnPublications();

    PublicationDto getOwnPublicationById(Long id);

    PublicationDto updateOwnPublication(Long id, PublicationDto publicationDetails);

    void deleteOwnPublication(Long id);

    void publishPublication(Long id);

    void unpublishPublication(Long id);
}