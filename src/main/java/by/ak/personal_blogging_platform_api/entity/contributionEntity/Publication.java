package by.ak.personal_blogging_platform_api.entity.contributionEntity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Publication implements Serializable {

	// TODO (High) Add @Table @Columns and validation of fields
	// TODO (High) Set up relationships between tables

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private List<Author> authors;
	private LocalDateTime creationDateTime;
	private LocalDateTime publishedDate;
	private boolean isPublished;
	private String content;

}