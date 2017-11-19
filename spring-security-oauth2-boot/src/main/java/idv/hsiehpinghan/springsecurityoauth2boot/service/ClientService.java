package idv.hsiehpinghan.springsecurityoauth2boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import idv.hsiehpinghan.springsecurityoauth2boot.entity.ClientEntity;
import idv.hsiehpinghan.springsecurityoauth2boot.repository.ClientRepository;

@Service
@Transactional
public class ClientService {
	@Autowired
	private ClientRepository repository;

	public void save(ClientEntity entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public ClientEntity findOne(String clientId) {
		return repository.findOne(clientId);
	}

}
