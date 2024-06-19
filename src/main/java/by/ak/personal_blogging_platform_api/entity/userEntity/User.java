package by.ak.personal_blogging_platform_api.entity.userEntity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

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
public class User implements Serializable {

	// TODO (High) Add @Table @Columns and validation of fields
	// TODO (High) Set up relationships between tables
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nickname;
	private String firstName;
	private String lastName;
	private String email;
	private LocalDate dateOfCreation;
	private boolean isActive;
	private List<Role> roles;
}
