package by.ak.personal_blogging_platform_api.controller;

import java.util.List;

import by.ak.personal_blogging_platform_api.service.exceptions.publication.publication.PublicationUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.ak.personal_blogging_platform_api.entity.publcationEntity.dto.PublicationDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/me/publications")
public class PublicationUserController {

	private final PublicationUserService service;

	@PostMapping
	public ResponseEntity<PublicationDto> createPublication(@Valid @RequestBody PublicationDto publicationDto) {
		log.info("Creating publication: {}" + publicationDto);
		PublicationDto createdPublication = service.createOwnPublication(publicationDto);
		log.info("Publication created: {}" + createdPublication);

		return new ResponseEntity<PublicationDto>(createdPublication, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PublicationDto> getPublication(@PathVariable Long id) {
		log.info("Fetching publication with id: {}", id);
		PublicationDto publicationDto = service.getOwnPublicationById(id);
		log.info("Publication fetched: {}", publicationDto);
		return new ResponseEntity<>(publicationDto, HttpStatus.OK);

	}

	@GetMapping
	public ResponseEntity<List<PublicationDto>> getAllPublication() {
		log.info("Fetching all publications");
		List<PublicationDto> publications = service.getAllOwnPublications();
		log.info("Publications fetched: {}", publications);
		return new ResponseEntity<>(publications, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PublicationDto> updatePublication(@Valid @PathVariable Long id, @RequestBody PublicationDto publicationDto) {
		log.info("Updating publication with id: {}, details: {}", id, publicationDto);
		PublicationDto updatedPublication = service.updateOwnPublication(id, publicationDto);
		log.info("Publication updated: {}", publicationDto);
		return new ResponseEntity<>(updatedPublication, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePublication(@PathVariable Long id) {
		log.info("Deleting publication with id: {}", id);
		service.deleteOwnPublication(id);
		log.info("Publication deleted with id: {}", id);
		return ResponseEntity.noContent().build();
	}

}
