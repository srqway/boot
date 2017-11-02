package idv.hsiehpinghan.springbootstarterwebboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import idv.hsiehpinghan.springbootstarterwebboot.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {
}
