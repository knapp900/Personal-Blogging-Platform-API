package by.ak.personal_blogging_platform_api.entity.userEntity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min=2, max=30)
	@Column(name = "nickname", nullable = false, length = 30)
	private String nickname;
	
	@NotNull
	@Size(min=2, max=30)
	@Column(name = "firstName", nullable = false, length = 30)
	private String firstName;
	
	@NotNull
	@Size(min=2, max=30)
	@Column(name = "lastName", nullable = false, length = 30)
	private String lastName;
	
	@NotNull
	@Email
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@NotNull
	@Column(name = "date_of_creation", nullable = false)
	private LocalDate dateOfCreation;

	@Column(name = "is_active")
	private boolean isActive;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private List<Role> roles;
}
