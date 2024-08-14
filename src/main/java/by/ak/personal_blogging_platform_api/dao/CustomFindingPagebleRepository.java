package by.ak.personal_blogging_platform_api.dao;

import by.ak.personal_blogging_platform_api.entity.publcationEntity.Publication;
import by.ak.personal_blogging_platform_api.entity.publcationEntity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface CustomFindingPagebleRepository extends JpaRepository<Publication, Long> {

    Page<Publication> findByPublishedDate(LocalDate date, Pageable pageable);
    Page<Publication> findByContentContaining(String words, Pageable pageable);
    Page<Publication> findByTags(List<Tag> tags, Pageable pageable);
}
