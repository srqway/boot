package idv.hsiehpinghan.springbootstarterdataneo4jboot.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.hsiehpinghan.springbootstarterdataneo4jboot.node.BasicNode;
import idv.hsiehpinghan.springbootstarterdataneo4jboot.repository.BasicRepository;

@Service
public class BasicService {
	@Autowired
	private BasicRepository basicRepository;

	public void deleteAll() {
		basicRepository.deleteAll();
	}

	public BasicNode save(BasicNode node) {
		return basicRepository.save(node);
	}

	public Optional<BasicNode> findById(String id) {
		return basicRepository.findById(id);
	}

//	public Collection<BasicNode> query(boolean primativeBoolean) {
//		return basicRepository.query(primativeBoolean);
//	}

}
