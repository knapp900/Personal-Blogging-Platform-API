package by.ak.personal_blogging_platform_api.service.publication.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import by.ak.personal_blogging_platform_api.dao.PublicationRepository;
import by.ak.personal_blogging_platform_api.entity.contributionEntity.Publication;
import by.ak.personal_blogging_platform_api.service.publication.PublicationCRUDService;

@Service
public class PublicationCRUDServiceImpl implements PublicationCRUDService {
	//TODO (High) Implement PublicationCRUDServiceImpl class
	//TODO (High) Add custom exception class
	//TODO (High) Add logging

	private final PublicationRepository repository;

	public PublicationCRUDServiceImpl(PublicationRepository repository) {
		this.repository = repository;
	}

	@Override
	public Publication createPublication(Publication publication) {
		publication.setCreationDateTime(LocalDateTime.now());
        publication.setPublished(false);
        return repository.save(publication);
	}

	@Override
	public List<Publication> getAllPublications() {
		return null;
	}

	@Override
	public Publication getPublicationById(Long id) {
		return null;
	}

	@Override
	public Publication updatePublication(Long id, Publication publicationDetails) {
		return null;
	}

	@Override
	public boolean deletePublication(Long id) {
		return false;
	}

	@Override
	public Publication publishPublication(Long id) {
		return null;
	}

	@Override
	public Publication unpublishPublication(Long id) {
		return null;
	}

}
