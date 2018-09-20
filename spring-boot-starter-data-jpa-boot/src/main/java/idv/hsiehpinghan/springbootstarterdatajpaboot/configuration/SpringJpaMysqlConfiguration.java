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

import idv.hsiehpinghan.springbootstarterdatajpaboot.entity.mysql.MysqlBasicTypeEntity;

@Configuration
// @formatter:off
@EnableJpaRepositories(
		entityManagerFactoryRef = "mysqlEntityManagerFactory", 
		transactionManagerRef = "mysqlTransactionManager", 
		basePackages = "idv.hsiehpinghan.springbootstarterdatajpaboot.repository.mysql"
)
// @formatter:on
public class SpringJpaMysqlConfiguration {

	@Bean
	@ConfigurationProperties("spring.mysqljpa")
	public JpaProperties mysqlJpaProperties() {
		return new JpaProperties();
	}

	@Bean
	@ConfigurationProperties("spring.mysqldatasource")
	public DataSourceProperties mysqlDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	@ConfigurationProperties("spring.mysqldatasource")
	public DataSource mysqlDataSource() {
		return mysqlDataSourceProperties().initializeDataSourceBuilder().build();
	}

	@Bean
	public EntityManagerFactoryBuilder mysqlEntityManagerFactoryBuilder(
			@Qualifier("mysqlJpaProperties") JpaProperties jpaProperties) {
		AbstractJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		return new EntityManagerFactoryBuilder(adapter, jpaProperties.getProperties(), null);
	}

	@Bean
	@PersistenceContext(unitName = "mysql")
	public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(
			@Qualifier("mysqlEntityManagerFactoryBuilder") EntityManagerFactoryBuilder builder,
			@Qualifier("mysqlDataSource") DataSource dataSource,
			@Qualifier("mysqlJpaProperties") JpaProperties jpaProperties) {
		// @formatter:offO
		return builder
				.dataSource(dataSource)
				.packages(MysqlBasicTypeEntity.class)
				.persistenceUnit("mysql")
				.properties(jpaProperties.getProperties())
				.build();
		// @formatter:on
	}

	@Bean
	public PlatformTransactionManager mysqlTransactionManager(
			@Qualifier("mysqlEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

}