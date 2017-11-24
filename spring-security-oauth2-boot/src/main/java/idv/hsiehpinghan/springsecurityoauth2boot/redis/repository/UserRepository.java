package idv.hsiehpinghan.springsecurityoauth2boot.redis.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import idv.hsiehpinghan.springsecurityoauth2boot.constant.Constant;
import idv.hsiehpinghan.springsecurityoauth2boot.redis.entity.UserEntity;

@Profile(Constant.REDIS_TOKEN_STORE_AUTHORIZATION_SERVER_PROFILE)
public interface UserRepository extends JpaRepository<UserEntity, String> {
}
