package idv.hsiehpinghan.springbootstarterdataneo4jboot.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import idv.hsiehpinghan.springbootstarterdataneo4jboot.node.BasicNode;
import idv.hsiehpinghan.springbootstarterdataneo4jboot.repository.BasicNodeRepository;

@Service
@Transactional
public class BasicNodeService {
	@Autowired
	private BasicNodeRepository repository;

	public void deleteAll() {
		repository.deleteAll();
	}

	public BasicNode save(BasicNode node) {
		return repository.save(node);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Optional<BasicNode> findById(String id) {
		return repository.findById(id);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Collection<BasicNode> query(boolean primativeBoolean) {
		return repository.query(primativeBoolean);
	}

}
