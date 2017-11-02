package idv.hsiehpinghan.springbootstarterwebboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import idv.hsiehpinghan.springbootstarterwebboot.entity.BasicEntity;
import idv.hsiehpinghan.springbootstarterwebboot.repository.BasicRepository;

@Service
@Transactional
public class BasicService {
	@Autowired
	private BasicRepository repository;

	public void save(BasicEntity entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<BasicEntity> findByString(String string) {
		return repository.findByString(string);
	}
}
