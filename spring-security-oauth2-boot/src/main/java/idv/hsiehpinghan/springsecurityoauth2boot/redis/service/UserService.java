package idv.hsiehpinghan.springsecurityoauth2boot.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import idv.hsiehpinghan.springsecurityoauth2boot.constant.Constant;
import idv.hsiehpinghan.springsecurityoauth2boot.redis.entity.UserEntity;
import idv.hsiehpinghan.springsecurityoauth2boot.redis.repository.UserRepository;

@Service
@Transactional
@Profile(Constant.REDIS_TOKEN_STORE_AUTHORIZATION_SERVER_PROFILE)
public class UserService {
	@Autowired
	private UserRepository repository;

	public void save(UserEntity entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public UserEntity findOne(String username) {
		return repository.findOne(username);
	}

}
