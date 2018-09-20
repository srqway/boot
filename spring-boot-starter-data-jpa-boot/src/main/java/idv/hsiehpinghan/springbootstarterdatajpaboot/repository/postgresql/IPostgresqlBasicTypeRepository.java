package idv.hsiehpinghan.springbootstarterdatajpaboot.repository.postgresql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import idv.hsiehpinghan.springbootstarterdatajpaboot.entity.postgresql.PostgresqlBasicTypeEntity;

@Repository
public interface IPostgresqlBasicTypeRepository
		extends JpaRepository<PostgresqlBasicTypeEntity, Long>, QuerydslPredicateExecutor<PostgresqlBasicTypeEntity> {
}
