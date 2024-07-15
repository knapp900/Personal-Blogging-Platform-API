package by.ak.personal_blogging_platform_api.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import by.ak.personal_blogging_platform_api.entity.publcationEntity.Tag;
import by.ak.personal_blogging_platform_api.entity.userEntity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import by.ak.personal_blogging_platform_api.entity.publcationEntity.Publication;

public interface PublicationRepository extends JpaRepository<Publication, Long> {
	List<Publication> findByUser(User user);
	Optional<Publication> findByUserAndId(User user, Long id);


}
