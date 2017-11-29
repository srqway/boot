package idv.hsiehpinghan.springbootstartersecurityboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import idv.hsiehpinghan.springbootstartersecurityboot.entity.ResourceEntity;
import idv.hsiehpinghan.springbootstartersecurityboot.repository.ResourceRepository;

@Service
@Transactional
public class ResourceService {
	@Autowired
	private ResourceRepository repository;

	public void save(ResourceEntity entity) {
		repository.save(entity);
	}

}
