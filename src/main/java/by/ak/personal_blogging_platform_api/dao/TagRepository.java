package by.ak.personal_blogging_platform_api.dao;

import by.ak.personal_blogging_platform_api.entity.publcationEntity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {
    Optional<Tag> findByName (String name);
}
