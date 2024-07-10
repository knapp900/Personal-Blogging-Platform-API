package by.ak.personal_blogging_platform_api.entity.publcationEntity;

import by.ak.personal_blogging_platform_api.entity.userEntity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

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

    @Column(name = "creationDateTime")
    private LocalDateTime creationDateTime;

    @Column(name = "publishedDate")
    private LocalDateTime publishedDate;

    @Column(name = "isPublished")
    private boolean isPublished;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user;

}