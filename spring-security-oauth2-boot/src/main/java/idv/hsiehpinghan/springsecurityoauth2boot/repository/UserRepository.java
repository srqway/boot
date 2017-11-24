package idv.hsiehpinghan.springsecurityoauth2boot.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import idv.hsiehpinghan.springsecurityoauth2boot.constant.Constant;
import idv.hsiehpinghan.springsecurityoauth2boot.entity.UserEntity;

@Profile(Constant.AUTHORIZATION_SERVER_PROFILE)
public interface UserRepository extends JpaRepository<UserEntity, String> {
}
