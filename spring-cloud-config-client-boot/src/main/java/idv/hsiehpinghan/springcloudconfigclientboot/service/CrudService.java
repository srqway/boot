package idv.hsiehpinghan.springcloudconfigclientboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import idv.hsiehpinghan.springcloudconfigclientboot.entity.CrudEntity;
import idv.hsiehpinghan.springcloudconfigclientboot.repository.CrudRepository;

@Service
@Transactional
public class CrudService {
	@Autowired
	private CrudRepository repository;

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<CrudEntity> findAll() {
		return repository.findAll();
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public CrudEntity findOne(Integer id) {
		return repository.findOne(id);
	}

	public void save(CrudEntity entity) {
		Integer id = entity.getId();
		CrudEntity oldEntity = repository.findOne(id);
		if (oldEntity != null) {
			throw new RuntimeException(String.format("oldEntity(%s) exists !!!", oldEntity));
		}
		repository.save(entity);
	}

	public void update(CrudEntity entity) {
		Integer id = entity.getId();
		CrudEntity oldEntity = repository.findOne(id);
		if (oldEntity == null) {
			throw new RuntimeException(String.format("entity(%s) not exists !!!", entity));
		}
		repository.save(entity);
	}

	public void delete(Integer id) {
		CrudEntity oldEntity = repository.findOne(id);
		if (oldEntity == null) {
			throw new RuntimeException(String.format("oldEntity(%s) not exists !!!", oldEntity));
		}
		repository.delete(id);
	}
}