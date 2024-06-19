package by.ak.personal_blogging_platform_api.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import by.ak.personal_blogging_platform_api.entity.userEntity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
