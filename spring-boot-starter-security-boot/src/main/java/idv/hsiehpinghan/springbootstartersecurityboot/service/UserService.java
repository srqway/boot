package idv.hsiehpinghan.springbootstartersecurityboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public UserEntity findOne(String username) {
		return repository.findOne(username);
	}

}
