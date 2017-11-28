package idv.hsiehpinghan.springbootstartersecurityboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import idv.hsiehpinghan.springbootstartersecurityboot.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {
}
