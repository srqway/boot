package idv.hsiehpinghan.springsecurityoauth2boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import idv.hsiehpinghan.springsecurityoauth2boot.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {
}
