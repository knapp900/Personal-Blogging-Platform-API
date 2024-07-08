package by.ak.personal_blogging_platform_api.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import by.ak.personal_blogging_platform_api.entity.contributionEntity.Publication;

public interface PublicationRepository extends JpaRepository<Publication, Long> {
	
	List<Publication> findByPublishedDate(LocalDate date);

	List<Publication> findByContentContaining(String word);

//	List<Publication> findByTagsIn(List<Tag> tags);
}
