package idv.hsiehpinghan.springbootstarterdataneo4jboot.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import idv.hsiehpinghan.springbootstarterdataneo4jboot.relationship.BasicRelationship;

public interface BasicRelationshipRepository extends Neo4jRepository<BasicRelationship, String> {
}
