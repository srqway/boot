package idv.hsiehpinghan.springbootstarterdataneo4jboot.repository;

import java.util.Collection;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import idv.hsiehpinghan.springbootstarterdataneo4jboot.node.BasicNode;

public interface BasicRepository extends Neo4jRepository<BasicNode, String> {
//	@Query("MATCH (n_0)-[r]->(n_1 {primativeBoolean:{primativeBoolean}}) RETURN n_0, r, n_1")
//	Collection<BasicNode> query(@Param("primativeBoolean") boolean primativeBoolean);
}
