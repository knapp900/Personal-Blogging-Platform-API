package by.ak.personal_blogging_platform_api.service.publication.impl;

import by.ak.personal_blogging_platform_api.entity.publcationEntity.Publication;
import by.ak.personal_blogging_platform_api.entity.publcationEntity.dto.PublicationDto;
import by.ak.personal_blogging_platform_api.entity.userEntity.User;
import by.ak.personal_blogging_platform_api.service.mapper.Mapper;
import by.ak.personal_blogging_platform_api.service.publication.PublicationUserService;
import by.ak.personal_blogging_platform_api.service.user.UserCrudServiceOfRawEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PublicationUserServiceImpl implements PublicationUserService {

    private final UserCrudServiceOfRawEntity userCrudServiceOfRawEntity;
    private final PublicationCrudServiceOfRawEntityImpl repository;
    private final Mapper<Publication, PublicationDto> publicationDtoMapper;

    @Override
    public PublicationDto createOwnPublication(PublicationDto publication) {
        User user = userCrudServiceOfRawEntity.getCurrentUser();

        return publicationDtoMapper.toDto(repository
                .createPublication(publicationDtoMapper.toEntity(publication), user));
    }


    @Override
    public List<PublicationDto> getAllOwnPublications() {
        User user = userCrudServiceOfRawEntity.getCurrentUser();
        return repository.getAllPublicationsByUser(user).stream()
                .map(publicationDtoMapper::toDto)
                .toList();
    }

    @Override
    public PublicationDto getOwnPublicationById(Long id) {
        User user = userCrudServiceOfRawEntity.getCurrentUser();
        return publicationDtoMapper.toDto(repository
                .findByUserAndId(user, id));
    }

    @Override
    public PublicationDto updateOwnPublication(Long id, PublicationDto publicationDetails) {
        User user = userCrudServiceOfRawEntity.getCurrentUser();
        repository.findByUserAndId(user, id);
        return publicationDtoMapper.toDto(repository
                .updatePublication(id, publicationDtoMapper.toEntity(publicationDetails)));

    }

    @Override
    public void deleteOwnPublication(Long id) {
        User user = userCrudServiceOfRawEntity.getCurrentUser();
        repository.findByUserAndId(user, id);
        repository.deletePublication(id);
    }

    @Override
    public void publishPublication(Long id) {
        User user = userCrudServiceOfRawEntity.getCurrentUser();
        repository.findByUserAndId(user, id);
        repository.publishPublication(id);
    }

    @Override
    public void unpublishPublication(Long id) {
        User user = userCrudServiceOfRawEntity.getCurrentUser();
        repository.findByUserAndId(user, id);
        repository.unpublishPublication(id);
    }


}
