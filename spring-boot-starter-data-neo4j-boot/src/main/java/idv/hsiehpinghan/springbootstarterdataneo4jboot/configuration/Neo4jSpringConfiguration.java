package idv.hsiehpinghan.springbootstarterdataneo4jboot.configuration;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = { "idv.hsiehpinghan.springbootstarterdataneo4jboot.node",
		"idv.hsiehpinghan.springbootstarterdataneo4jboot.relationship.BaseRelationship" })
@EnableNeo4jRepositories(basePackages = { "idv.hsiehpinghan.springbootstarterdataneo4jboot.repository" })
public class Neo4jSpringConfiguration {

	@Bean
	public Neo4jTransactionManager transactionManager(SessionFactory sessionFactory) {
		return new Neo4jTransactionManager(sessionFactory);
	}

}