package idv.hsiehpinghan.springbootstarterdatajpaboot.repository.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import idv.hsiehpinghan.springbootstarterdatajpaboot.entity.mysql.MysqlBasicTypeEntity;

@Repository
public interface IMysqlBasicTypeRepository
		extends JpaRepository<MysqlBasicTypeEntity, Long>, QuerydslPredicateExecutor<MysqlBasicTypeEntity> {
}
