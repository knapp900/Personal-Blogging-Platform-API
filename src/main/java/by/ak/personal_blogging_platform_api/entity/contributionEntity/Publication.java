package by.ak.personal_blogging_platform_api.entity.contributionEntity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

@Component
public class Publication implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String title;
	private List<Author> authors;
	private LocalDateTime creationDateTime;
	private LocalDateTime publishedDate;
	private boolean isPublished;
	private String content;

	public Publication() {
		super();
	}

	public Publication(Long id, String title, List<Author> authors, LocalDateTime creationDateTime,
			LocalDateTime publishedDate, boolean isPublished, String content) {
		super();
		this.id = id;
		this.title = title;
		this.authors = authors;
		this.creationDateTime = creationDateTime;
		this.publishedDate = publishedDate;
		this.isPublished = isPublished;
		this.content = content;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public LocalDateTime getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(LocalDateTime creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public LocalDateTime getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(LocalDateTime publishedDate) {
		this.publishedDate = publishedDate;
	}

	public boolean isPublished() {
		return isPublished;
	}

	public void setPublished(boolean isPublished) {
		this.isPublished = isPublished;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public int hashCode() {
		return Objects.hash(authors, content, creationDateTime, id, isPublished, publishedDate, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Publication other = (Publication) obj;
		return Objects.equals(authors, other.authors) && Objects.equals(content, other.content)
				&& Objects.equals(creationDateTime, other.creationDateTime) && Objects.equals(id, other.id)
				&& isPublished == other.isPublished && Objects.equals(publishedDate, other.publishedDate)
				&& Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "Publication [id=" + id + ", title=" + title + ", authors=" + authors + ", creationDateTime="
				+ creationDateTime + ", publishedDate=" + publishedDate + ", isPublished=" + isPublished + ", content="
				+ content + "]";
	}

}
