package by.ak.personal_blogging_platform_api.service.publication.impl;

import by.ak.personal_blogging_platform_api.dao.PublicationRepository;
import by.ak.personal_blogging_platform_api.entity.publcationEntity.Publication;
import by.ak.personal_blogging_platform_api.entity.publcationEntity.dto.PublicationDto;
import by.ak.personal_blogging_platform_api.entity.userEntity.User;
import by.ak.personal_blogging_platform_api.entity.userEntity.dto.UserDto;
import by.ak.personal_blogging_platform_api.service.mapper.Mapper;
import by.ak.personal_blogging_platform_api.service.publication.PublicationUserService;
import by.ak.personal_blogging_platform_api.service.user.UserService;
import by.ak.personal_blogging_platform_api.service.user.UserServiceOfRawEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class PublicationUserServiceImpl implements PublicationUserService {

    private final Mapper<User, UserDto> userDtoMapper;
    private final UserService userService;
    private final UserServiceOfRawEntity userServiceOfRawEntity;
    private final PublicationRepository repository;
    private final Mapper<Publication, PublicationDto> publicationDtoMapper;
//TODO (High)- При создании публикации вкладывать ее в пользователя и подумать над заведением поля user

    @Override
    public PublicationDto createPublication(PublicationDto publication) {
        User user = userServiceOfRawEntity.getCurrentUser();
        PublicationDto publicationDtoForCreation = new PublicationDto(null, publication.title(), publication.content(), user.getId(), user.getUsername());
        // TODO (Medium) Add throws exceptions
        Publication publicationForSave = publicationDtoMapper.toEntity(publicationDtoForCreation);
        publicationForSave.setCreationDateTime(LocalDateTime.now());
        publicationForSave.setPublished(false);
        publicationForSave.setUser(userDtoMapper.toEntity(userService.getCurrentUser()));
        log.info("Publication of user: {} created.", user.getId());
        return publicationDtoMapper.toDto(repository.save(publicationForSave));
    }


    @Override
    public List<PublicationDto> getAllOwnPublications() {
        //FIXME Не дает все публикации только одну
        User user = userServiceOfRawEntity.getCurrentUser();
        return repository.findByUser(user).stream()
                .map(publicationDtoMapper::toDto)
                .toList();
    }

    @Override
    public PublicationDto getOwnPublicationById(Long id) {
        User user = userServiceOfRawEntity.getCurrentUser();
        return publicationDtoMapper.toDto(repository.findByUserAndId(user, id).orElseThrow(() -> {
            log.error("Publication not found with id: {}", id);
            return new NoSuchElementException("Publication not found with id: " + id);

        }));
    }

    @Override
    public PublicationDto updateOwnPublication(Long id, PublicationDto publicationDetails) {
        User user = userServiceOfRawEntity.getCurrentUser();
        Publication existingPublication = repository.findByUserAndId(user,id).orElseThrow(() -> {
            log.error("Publication of user: {} not found with id: {}", id, user.getId());
            return new NoSuchElementException("Publication not found with id: " + id);

        });

        existingPublication.setTitle(Objects.nonNull(publicationDetails.title()) ? publicationDetails.title() : existingPublication.getTitle());
        existingPublication.setTitle(Objects.nonNull(publicationDetails.content()) ? publicationDetails.content() : existingPublication.getTitle());

        return publicationDtoMapper.toDto(repository.save(existingPublication));

    }

    @Override
    public void deleteOwnPublication(Long id) {
        User user = userServiceOfRawEntity.getCurrentUser();
        if (repository.findByUserAndId(user, id).isPresent()) {
            repository.deleteById(id);
        } else {
            log.error("Publication of user: {} not found with id: {}", id, user.getId());
            throw new NoSuchElementException("Publication not found with id: " + id);
        }
    }

    @Override
    public PublicationDto publishPublication(Long id) {
        User user = userServiceOfRawEntity.getCurrentUser();
        Publication publicationForPublish = repository.findByUserAndId(user, id).orElseThrow(() -> {
            log.error("Publication of user: {} not found with id: {}", id, user.getId());
            return new NoSuchElementException("Publication not found with id: " + id);

        });

        publicationForPublish.setPublished(true);

        return publicationDtoMapper.toDto(repository.save(publicationForPublish));
    }

    @Override
    public PublicationDto unpublishPublication(Long id) {
        User user = userServiceOfRawEntity.getCurrentUser();
        Publication publicationForPublish = repository.findByUserAndId(user, id).orElseThrow(() -> {
            log.error("Publication of user: {} not found with id: {}", id, user.getId());
            return new NoSuchElementException("Publication not found with id: " + id);

        });

        publicationForPublish.setPublished(false);

        return publicationDtoMapper.toDto(repository.save(publicationForPublish));
    }


}
