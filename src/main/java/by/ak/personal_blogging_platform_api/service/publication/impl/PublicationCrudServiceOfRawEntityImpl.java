package by.ak.personal_blogging_platform_api.service.publication.impl;

import by.ak.personal_blogging_platform_api.dao.PublicationRepository;
import by.ak.personal_blogging_platform_api.entity.publcationEntity.Publication;
import by.ak.personal_blogging_platform_api.entity.publcationEntity.Tag;
import by.ak.personal_blogging_platform_api.entity.userEntity.User;
import by.ak.personal_blogging_platform_api.service.exceptions.publication.PublicationNotFoundException;
import by.ak.personal_blogging_platform_api.service.exceptions.publication.PublicationServiceExceptoin;
import by.ak.personal_blogging_platform_api.service.exceptions.user.UserServiceException;
import by.ak.personal_blogging_platform_api.service.publication.PublicationCrudServiceOfRawEntity;
import by.ak.personal_blogging_platform_api.service.publication.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class PublicationCrudServiceOfRawEntityImpl implements PublicationCrudServiceOfRawEntity {

    private final PublicationRepository repository;

    private final TagService tagRepository;


    @Override
    public Publication createPublication(Publication publication, User creator) {
        try {
            List<Tag> tags = new ArrayList<>();

            Publication publicationForSave = new Publication();
            publicationForSave.setTitle(publication.getTitle());
            publicationForSave.setContent(publication.getContent());
            publicationForSave.setUser(creator);
            publicationForSave.setCreationDateTime(LocalDateTime.now());
            publicationForSave.setPublished(false);
            publicationForSave.setUser(creator);


            preparingTagsForSaving(publication.getTags());
            publication.getTags().stream().forEach(tag -> tags.add(tagRepository.createTag(tag)));

            publicationForSave.setTags(tags);

            return repository.save(publicationForSave);
        } catch (Exception e) {
            log.error("Error creating publication for user: {}", creator.getId(), e);
            throw new UserServiceException("Failed to create publication for user:" + creator.getId(), e);
        }

    }

    @Override
    public List<Publication> getAllPublications() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            log.error("Error getting all publications", e);
            throw new UserServiceException("Failed to get all publications", e);
        }
    }

    @Override
    public List<Publication> getAllPublicationsByUser(User user) {
        try {
            return repository.findByUser(user);
        } catch (Exception e) {
            log.error("Error getting all publications", e);
            throw new UserServiceException("Failed to get all publications", e);
        }
    }

    @Override
    public Publication getPublicationById(Long id) {
        try {
            return repository.findById(id).orElseThrow(() ->
                    {
                        log.error("Publication not found with id:{}", id);
                        return new PublicationNotFoundException("Publication not found with id: " + id);
                    }
            );
        } catch (Exception e) {
            log.error("Error getting publication with id: {}", id, e);
            throw new PublicationServiceExceptoin("Failed to update publication with id: " + id, e);
        }
    }

    @Override
    public Publication updatePublication(Long id, Publication publicationDetails) {
        try {

            Publication existingPublication = repository.findById(id).orElseThrow(() ->
                    {
                        log.error("Publication not found with id:{}", id);
                        return new PublicationNotFoundException("Publication not found with id: " + id);
                    }
            );

            existingPublication.setTitle(Objects.nonNull(publicationDetails.getTitle()) ? publicationDetails.getTitle() : existingPublication.getTitle());
            existingPublication.setContent(Objects.nonNull(publicationDetails.getContent()) ? publicationDetails.getContent() : existingPublication.getTitle());
            existingPublication.setTags((!publicationDetails.getTags().isEmpty()) ? publicationDetails.getTags() : existingPublication.getTags());

            return repository.save(existingPublication);

        } catch (Exception e) {
            log.error("Error updating publication with id: {}", id, e);
            throw new PublicationServiceExceptoin("Failed to update publication with id: " + id, e);
        }
    }

    @Override
    public Publication findByUserAndId(User user, Long publicationId) {
        try {
            return repository.findByUserAndId(user, publicationId).orElseThrow(() ->
                    {
                        log.error("Publication not found with id and user:{}{}", publicationId, user.getId());
                        return new PublicationNotFoundException("Publication not found with id: " + publicationId);
                    }
            );
        } catch (Exception e) {
            log.error("Error finding publication with id and user: {}{}", publicationId, user.getId(), e);
            throw new PublicationServiceExceptoin("Failed to find publication with id: " + publicationId, e);
        }
    }

    @Override
    public void deletePublication(Long id) {
        try {
            if (repository.findById(id).isPresent()) {
                repository.deleteById(id);
            } else {
                throw new PublicationNotFoundException("Publication not found with id: " + id);
            }
        } catch (Exception e) {
            log.error("Error deleting publication with id: {}", id, e);
            throw new PublicationServiceExceptoin("Failed to delete publication with id: " + id, e);
        }
    }

    @Override
    public void publishPublication(Long id) {
        try {
            Publication publicationForPublish = repository.findById(id).orElseThrow(() ->
                    {
                        log.error("Publication not found with id:{}", id);
                        return new PublicationNotFoundException("Publication not found with id: " + id);
                    }
            );
            publicationForPublish.setPublished(true);
            repository.save(publicationForPublish);

        } catch (Exception e) {
            log.error("Error of publishing with id:{}", id, e);
            throw new PublicationServiceExceptoin("Failed of publishing with id: " + id, e);
        }
    }

    @Override
    public void unpublishPublication(Long id) {
        try {
            Publication publicationForUnpublish = repository.findById(id).orElseThrow(() ->
                    {
                        log.error("Publication not found with id:{}", id);
                        return new PublicationNotFoundException("Publication not found with id: " + id);
                    }
            );
            publicationForUnpublish.setPublished(false);
            repository.save(publicationForUnpublish);

        } catch (Exception e) {
            log.error("Error of unpublishing with id:{}", id, e);
            throw new PublicationServiceExceptoin("Failed of unpublishing with id: " + id, e);
        }
    }

    private List<Tag> preparingTagsForSaving(List<Tag> tags) {
        if (!tags.isEmpty()) {

            return tags.stream()
                    .map(tag -> {
                        String trimmedName = tag.getName().trim().toUpperCase(Locale.ROOT);
                        tag.setName(trimmedName);

                        if (tag.getName().charAt(0) != '#') {
                            tag.setName("#" + tag.getName());
                        }
                        return tag;
                    })
                    .toList();
        } else {
            return tags;
        }
    }
}
