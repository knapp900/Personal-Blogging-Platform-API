package by.ak.personal_blogging_platform_api.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import by.ak.personal_blogging_platform_api.entity.contributionEntity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>{
    Optional<Author> findByName(String name);

}
