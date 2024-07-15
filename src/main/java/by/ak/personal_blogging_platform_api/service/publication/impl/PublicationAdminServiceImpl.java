package by.ak.personal_blogging_platform_api.service.publication.impl;

import by.ak.personal_blogging_platform_api.entity.publcationEntity.dto.PublicationDto;
import by.ak.personal_blogging_platform_api.entity.userEntity.User;
import by.ak.personal_blogging_platform_api.service.mapper.publication.PublicationDtoMapper;
import by.ak.personal_blogging_platform_api.service.publication.PublicationAdminService;
import by.ak.personal_blogging_platform_api.service.publication.PublicationCrudServiceOfRawEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PublicationAdminServiceImpl implements PublicationAdminService {

    private final PublicationCrudServiceOfRawEntity repository;

    private final PublicationDtoMapper publicationDtoMapper;


    @Override
    public List<PublicationDto> getAllPublications() {
        return repository.getAllPublications().stream()
                .map(p -> publicationDtoMapper.toDto(p))
                .toList();
    }

    @Override
    public List<PublicationDto> getAllPublicationsByUser(User user) {
        return repository.getAllPublicationsByUser(user).stream()
                .map(p -> publicationDtoMapper.toDto(p))
                .toList();
    }

    @Override
    public PublicationDto getPublicationById(Long id) {
        return publicationDtoMapper
                .toDto(repository.getPublicationById(id));
    }

    @Override
    public PublicationDto updatePublication(Long id, PublicationDto publicationDetails) {
        return publicationDtoMapper
                .toDto(repository.updatePublication(
                        id,publicationDtoMapper.toEntity(publicationDetails)));
    }


    @Override
    public void deletePublication(Long id) {
        repository.deletePublication(id);
    }

    @Override
    public void publishPublication(Long id) {
        repository.publishPublication(id);
    }

    @Override
    public void unpublishPublication(Long id) {
        repository.unpublishPublication(id);
    }
}
