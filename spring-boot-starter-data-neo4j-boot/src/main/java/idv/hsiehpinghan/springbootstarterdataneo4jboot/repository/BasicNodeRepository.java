package idv.hsiehpinghan.springbootstarterdataneo4jboot.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import idv.hsiehpinghan.springbootstarterdataneo4jboot.node.BasicNode;

public interface BasicNodeRepository extends Neo4jRepository<BasicNode, String> {

}
