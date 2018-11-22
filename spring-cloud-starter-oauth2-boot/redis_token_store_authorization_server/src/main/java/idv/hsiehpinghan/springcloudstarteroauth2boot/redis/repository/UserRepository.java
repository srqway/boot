package idv.hsiehpinghan.springcloudstarteroauth2boot.redis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import idv.hsiehpinghan.springcloudstarteroauth2boot.redis.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {
}
