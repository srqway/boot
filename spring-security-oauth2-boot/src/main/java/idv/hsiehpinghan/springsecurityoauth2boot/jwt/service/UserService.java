package idv.hsiehpinghan.springsecurityoauth2boot.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import idv.hsiehpinghan.springsecurityoauth2boot.constant.Constant;
import idv.hsiehpinghan.springsecurityoauth2boot.jwt.entity.UserEntity;
import idv.hsiehpinghan.springsecurityoauth2boot.jwt.repository.UserRepository;

@Service
@Transactional
@Profile(Constant.JWT_TOKEN_STORE_AUTHORIZATION_SERVER_PROFILE)
public class UserService {
	@Autowired
	private UserRepository repository;

	public void save(UserEntity entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public UserEntity findOne(String username) {
		UserEntity userEntity = repository.findOne(username);
		if (userEntity == null) {
			return null;
		}
		userEntity.getRoles().size();
		return userEntity;
	}

}
