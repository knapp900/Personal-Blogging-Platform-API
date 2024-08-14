package by.ak.personal_blogging_platform_api.service.publication.impl;

import by.ak.personal_blogging_platform_api.dao.TagRepository;
import by.ak.personal_blogging_platform_api.entity.publcationEntity.Tag;
import by.ak.personal_blogging_platform_api.service.exceptions.publication.PublicationServiceExceptoin;
import by.ak.personal_blogging_platform_api.service.publication.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class TagServiceImpl implements TagService {

    private final TagRepository repository;

    @Override
    public Tag createTag(Tag tag) {
        try{

            Optional<Tag> tagForSave = repository.findByName(tag.getName());

            return tagForSave.orElseGet(() -> repository.save(tag));

        } catch (Exception e) {
            log.error("Error creating tag : {}",tag,e);
            throw new PublicationServiceExceptoin("Error creating tag : {}" + tag,e);
        }

    }
}
