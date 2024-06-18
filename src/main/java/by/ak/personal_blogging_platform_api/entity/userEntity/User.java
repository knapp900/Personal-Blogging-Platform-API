package by.ak.personal_blogging_platform_api.entity.userEntity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

@Component
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nickname;
	private String firstName;
	private String lastName;
	private String email;
	private LocalDate dateOfCreation;
	private boolean isActive;
	private List<Role> roles;

	public User() {
		super();
	}

	public User(Long id, String nickname, String firstName, String lastName, String email, LocalDate dateOfCreation,
			boolean isActive, List<Role> roles) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.dateOfCreation = dateOfCreation;
		this.isActive = isActive;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDateOfCreation() {
		return dateOfCreation;
	}

	public void setDateOfCreation(LocalDate dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateOfCreation, email, firstName, id, isActive, lastName, nickname, roles);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(dateOfCreation, other.dateOfCreation) && Objects.equals(email, other.email)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(id, other.id)
				&& isActive == other.isActive && Objects.equals(lastName, other.lastName)
				&& Objects.equals(nickname, other.nickname) && Objects.equals(roles, other.roles);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nickname=" + nickname + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", dateOfCreation=" + dateOfCreation + ", isActive=" + isActive + ", roles="
				+ roles + "]";
	}

}
