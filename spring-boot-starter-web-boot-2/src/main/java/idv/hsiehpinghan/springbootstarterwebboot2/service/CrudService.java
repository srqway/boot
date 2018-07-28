package idv.hsiehpinghan.springbootstarterwebboot2.service;

import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import idv.hsiehpinghan.springbootstarterwebboot2.entity.CrudEntity;
import idv.hsiehpinghan.springbootstarterwebboot2.repository.CrudRepository;

@Service
@Transactional
public class CrudService {
	@Autowired
	private CrudRepository repository;
	
//	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
//	public List<CrudEntity> findAll() {
//		return repository.findAll();
//	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Optional<CrudEntity> getOne(Integer id) {
		return repository.findById(id);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public boolean existsById(Integer id) {
		return repository.existsById(id);
	}
	
	public void save(CrudEntity entity) {
		Integer id = entity.getId();
		Optional<CrudEntity> oldEntity = repository.findById(id);
		if (oldEntity.isPresent() == true) {
			throw new RuntimeException(String.format("oldEntity(%s) exists !!!", oldEntity));
		}
		repository.save(entity);
	}

//	public void update(CrudEntity entity) {
//		Integer id = entity.getId();
//		CrudEntity oldEntity = repository.getOne(id);
//		if (oldEntity == null) {
//			throw new RuntimeException(String.format("entity(%s) not exists !!!", entity));
//		}
//		repository.save(entity);
//	}
//
//	public void delete(Integer id) {
//		CrudEntity oldEntity = repository.getOne(id);
//		if (oldEntity == null) {
//			throw new RuntimeException(String.format("oldEntity(%s) not exists !!!", oldEntity));
//		}
//		repository.deleteById(id);
//	}
}