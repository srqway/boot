package idv.hsiehpinghan.springsecurityoauth2boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import idv.hsiehpinghan.springsecurityoauth2boot.constant.Constant;
import idv.hsiehpinghan.springsecurityoauth2boot.entity.UserEntity;
import idv.hsiehpinghan.springsecurityoauth2boot.repository.UserRepository;

@Service
@Transactional
@Profile(Constant.AUTHORIZATION_SERVER_PROFILE)
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
