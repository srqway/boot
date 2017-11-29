package idv.hsiehpinghan.springbootstartersecurityboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import idv.hsiehpinghan.springbootstartersecurityboot.entity.RoleEntity;
import idv.hsiehpinghan.springbootstartersecurityboot.entity.UserEntity;
import idv.hsiehpinghan.springbootstartersecurityboot.repository.UserRepository;

@Service
@Transactional
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
		for (RoleEntity roleEntity : userEntity.getRoles()) {
			roleEntity.getResources().size();
		}
		return userEntity;
	}

}
