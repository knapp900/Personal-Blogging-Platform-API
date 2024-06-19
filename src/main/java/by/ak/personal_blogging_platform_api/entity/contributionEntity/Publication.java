package by.ak.personal_blogging_platform_api.entity.contributionEntity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "publication")
public class Publication implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "authors")
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "publication_authors", joinColumns = @JoinColumn(name = "publication_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
	private List<Author> authors;

	@Column(name = "creationDateTime")
	private LocalDateTime creationDateTime;

	@Column(name = "publishedDate")
	private LocalDateTime publishedDate;

	@Column(name = "isPublished")
	private boolean isPublished;

	@Column(name = "content", nullable = false, columnDefinition = "TEXT")
	private String content;

}