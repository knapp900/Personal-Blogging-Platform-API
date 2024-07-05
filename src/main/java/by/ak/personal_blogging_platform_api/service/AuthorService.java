package by.ak.personal_blogging_platform_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import by.ak.personal_blogging_platform_api.dao.AuthorRepository;
import by.ak.personal_blogging_platform_api.entity.contributionEntity.Author;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthorService {
	// TODO Implement all methods AuthorService class
	private final AuthorRepository repository;

	public Author ctrateAuthor(Author author) {
		log.info("Checking author for exist: {}", author);
		Optional<Author> existingAuthor = repository.findByName(author.getName());
		if (existingAuthor.isPresent()) {
			log.info("Author already exist: {}", author);
			return existingAuthor.get();
		} else {
			log.info("Creating author: {}", author);
			Author savedAuthor = repository.save(author);
			log.info("Author created: {} {}", author);
			return savedAuthor;

		}
	}

	public List<Author> getAllAuthors() {
		log.info("Fetching all authors");
		List<Author> authors = repository.findAll();
		log.info("Authors fetched: {}", authors);
		return authors;
	}

	public Author getAuthorById(Long id) {
		log.info("Fetching author with id: {}", id);
		Optional<Author> author = repository.findById(id);
		if (author.isPresent()) {
			log.info("Author found: {}", author.get());
			return author.get();
		} else {
			log.error("Author not found with id: {}", id);
			throw new IllegalStateException("Author not found");
		}
	}


	public void deleteAuthor(Long id) {
		log.info("Deleting author with id: {}", id);
		if (repository.existsById(id)) {
			repository.deleteById(id);
			log.info("Author deleted with id: {}", id);
		} else {
			log.error("Author not found with id: {}", id);
			throw new IllegalStateException("Author not found");
		}
	}

	public Author changeAuthor(Long id, Author updatedAuthor) {
		log.info("Updating author with id: {}", id);
		Optional<Author> existingAuthor = repository.findById(id);
		if (existingAuthor.isPresent()) {
			Author authorToUpdate = existingAuthor.get();
			authorToUpdate.setName(updatedAuthor.getName());
			Author savedAuthor = repository.save(authorToUpdate);
			log.info("Author updated: {}", savedAuthor);
			return savedAuthor;
		} else {
			log.error("Author not found with id: {}", id);
			throw new IllegalStateException("Author not found");
		}
	}

}
