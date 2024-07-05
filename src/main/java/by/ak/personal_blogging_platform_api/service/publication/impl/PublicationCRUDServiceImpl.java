package by.ak.personal_blogging_platform_api.service.publication.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import by.ak.personal_blogging_platform_api.dao.PublicationRepository;
import by.ak.personal_blogging_platform_api.entity.contributionEntity.Author;
import by.ak.personal_blogging_platform_api.entity.contributionEntity.Publication;
import by.ak.personal_blogging_platform_api.entity.contributionEntity.dto.PublicationDto;
import by.ak.personal_blogging_platform_api.service.AuthorService;
import by.ak.personal_blogging_platform_api.service.Mapper;
import by.ak.personal_blogging_platform_api.service.publication.PublicationCRUDService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class PublicationCRUDServiceImpl implements PublicationCRUDService {

	private final AuthorService authorService;
	private final PublicationRepository repository;
	private final Mapper<Publication, PublicationDto> publicationDtoMapper;

	@Override
	public PublicationDto createPublication(PublicationDto publication) {
		List<Author> authors = publication.authors()
				.stream()
				.map( author -> authorService.ctrateAuthor(author))
				.toList();

		// TODO (Medium) Add throws exceptions
		Publication publicationForSave = publicationDtoMapper.toEntity(publication);
		publicationForSave.setCreationDateTime(LocalDateTime.now());
		publicationForSave.setPublished(false);
		publicationForSave.setAuthors(authors);
		log.info("Publication created: {}", publication);
		return publicationDtoMapper.toDto(repository.save(publicationForSave));
	}


	@Override
	public List<PublicationDto> getAllPublications() {

		return repository.findAll().stream().map(p -> publicationDtoMapper.toDto(p)).toList();
	}

	@Override
	public PublicationDto getPublicationById(Long id) {
		return publicationDtoMapper.toDto(repository.findById(id).orElseThrow(() -> {
			log.error("Publication not found with id: {}", id);
			return new NoSuchElementException("Publication not found with id: " + id);

		}));
	}

	@Override
	public PublicationDto updatePublication(Long id, PublicationDto publicationDetails) {
		Publication existingPublication = repository.findById(id).orElseThrow(() -> {
			log.error("Publication not found with id: {}", id);
			return new NoSuchElementException("Publication not found with id: " + id);

		});

		existingPublication.setTitle(publicationDetails.title());
		existingPublication.setAuthors(publicationDetails.authors());
		existingPublication.setTitle(publicationDetails.content());

		return publicationDtoMapper.toDto(repository.save(existingPublication));

	}

	@Override
	public void deletePublication(Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
		} else {
			log.error("Publication not found with id: {}", id);
			throw new NoSuchElementException("Publication not found with id: " + id);
		}
	}

//	TODO (High) Move publishPublication & unpublishPublication methods to PublicationManagementService

	@Override
	public PublicationDto publishPublication(Long id) {
		// TODO change publish data
		Publication publicationForPublish = repository.findById(id).orElseThrow(() -> {
			log.error("Publication not found with id: {}", id);
			return new NoSuchElementException("Publication not found with id: " + id);

		});

		publicationForPublish.setPublished(true);

		return publicationDtoMapper.toDto(repository.save(publicationForPublish));
	}

	@Override
	public PublicationDto unpublishPublication(Long id) {
		Publication publicationForPublish = repository.findById(id).orElseThrow(() -> {
			log.error("Publication not found with id: {}", id);
			return new NoSuchElementException("Publication not found with id: " + id);

		});

		publicationForPublish.setPublished(false);

		return publicationDtoMapper.toDto(repository.save(publicationForPublish));
	}

}
