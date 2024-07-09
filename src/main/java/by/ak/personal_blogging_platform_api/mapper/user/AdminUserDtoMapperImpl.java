package by.ak.personal_blogging_platform_api.mapper.user;

import by.ak.personal_blogging_platform_api.entity.userEntity.User;
import by.ak.personal_blogging_platform_api.entity.userEntity.dto.AdminUserDto;
import by.ak.personal_blogging_platform_api.service.Mapper;
import org.springframework.stereotype.Component;

@Component
public class AdminUserDtoMapperImpl implements Mapper<User, AdminUserDto> {
    @Override
    public User toEntity(AdminUserDto dtoElement) {
        User user = new User();
        user.setId(dtoElement.id());
        user.setUsername(dtoElement.username());
        user.setFirstname(dtoElement.firstname());
        user.setLastname(dtoElement.lastname());
        user.setPassword(dtoElement.password());
        user.setEmail(dtoElement.email());
        user.setActive(dtoElement.isActive());
        user.setDateOfCreation(dtoElement.dateOfCreation());
        user.setRoles(dtoElement.roles());
        user.setPublications(dtoElement.publications());
        return user;
    }

    @Override
    public AdminUserDto toDto(User entity) {
        return new AdminUserDto(
                entity.getId(),
                entity.getUsername(),
                entity.getFirstname(),
                entity.getLastname(),
                entity.getPassword(),
                entity.getEmail(),
                entity.getDateOfCreation(),
                entity.isActive(),
                entity.getRoles(),
                entity.getPublications());
    }
}
