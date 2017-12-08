package idv.hsiehpinghan.springbootstartersecurityboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import idv.hsiehpinghan.springbootstartersecurityboot.entity.ConfigurationEntity;
import idv.hsiehpinghan.springbootstartersecurityboot.repository.ConfigurationRepository;

@Service
@Transactional
public class ConfigurationService {
	@Autowired
	private ConfigurationRepository repository;

	public void save(ConfigurationEntity entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public ConfigurationEntity findOne(ConfigurationEntity.Id id) {
		return repository.findOne(id);
	}

}
