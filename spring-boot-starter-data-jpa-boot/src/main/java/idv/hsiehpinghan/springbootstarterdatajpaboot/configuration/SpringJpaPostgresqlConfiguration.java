package idv.hsiehpinghan.springbootstarterdatajpaboot.configuration;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import idv.hsiehpinghan.springbootstarterdatajpaboot.entity.postgresql.PostgresqlBasicTypeEntity;

@Configuration
// @formatter:off
@EnableJpaRepositories(
		entityManagerFactoryRef = "postgresqlEntityManagerFactory", 
		transactionManagerRef = "postgresqlTransactionManager", 
		basePackages = "idv.hsiehpinghan.springbootstarterdatajpaboot.repository.postgresql"
)
// @formatter:on
public class SpringJpaPostgresqlConfiguration {
	@Bean
	@ConfigurationProperties("spring.postgresqljpa")
	public JpaProperties postgresqlJpaProperties() {
		return new JpaProperties();
	}

	@Bean
	@ConfigurationProperties("spring.postgresqldatasource")
	public DataSourceProperties postgresqlDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	@ConfigurationProperties("spring.postgresqldatasource")
	public DataSource postgresqlDataSource() {
		return postgresqlDataSourceProperties().initializeDataSourceBuilder().build();
	}

	@Bean
	public EntityManagerFactoryBuilder postgresqlEntityManagerFactoryBuilder(
			@Qualifier("postgresqlJpaProperties") JpaProperties jpaProperties) {
		AbstractJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		return new EntityManagerFactoryBuilder(adapter, jpaProperties.getProperties(), null);
	}

	@Bean
	@PersistenceContext(unitName = "postgresql")
	public LocalContainerEntityManagerFactoryBean postgresqlEntityManagerFactory(
			@Qualifier("postgresqlEntityManagerFactoryBuilder") EntityManagerFactoryBuilder builder,
			@Qualifier("postgresqlDataSource") DataSource dataSource,
			@Qualifier("postgresqlJpaProperties") JpaProperties jpaProperties) {
		// @formatter:offO
		return builder
				.dataSource(dataSource)
				.packages(PostgresqlBasicTypeEntity.class)
				.persistenceUnit("postgresql")
				.properties(jpaProperties.getProperties())
				.build();
		// @formatter:on
	}

	@Bean
	public PlatformTransactionManager postgresqlTransactionManager(
			@Qualifier("postgresqlEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

}