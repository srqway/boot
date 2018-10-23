package idv.hsiehpinghan.springbootstarterdataneo4jboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import idv.hsiehpinghan.springbootstarterdataneo4jboot.relationship.BasicRelationship;
import idv.hsiehpinghan.springbootstarterdataneo4jboot.repository.BasicRelationshipRepository;

@Service
@Transactional
public class BasicRelationshipService {
	@Autowired
	private BasicRelationshipRepository repository;

	public BasicRelationship save(BasicRelationship relationship) {
		return repository.save(relationship);
	}

}
