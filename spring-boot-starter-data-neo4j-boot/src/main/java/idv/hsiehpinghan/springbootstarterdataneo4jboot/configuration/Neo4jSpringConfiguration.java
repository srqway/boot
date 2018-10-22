package idv.hsiehpinghan.springbootstarterdataneo4jboot.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@Configuration
@EnableNeo4jRepositories("idv.hsiehpinghan.springbootstarterdataneo4jboot.repository")
public class Neo4jSpringConfiguration {

}