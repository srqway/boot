package idv.hsiehpinghan.springbootstarterdatajpaboot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import idv.hsiehpinghan.springbootstarterdatajpaboot.entity.mysql.MysqlBasicTypeEntity;
import idv.hsiehpinghan.springbootstarterdatajpaboot.entity.postgresql.PostgresqlBasicTypeEntity;
import idv.hsiehpinghan.springbootstarterdatajpaboot.repository.mysql.IMysqlBasicTypeRepository;
import idv.hsiehpinghan.springbootstarterdatajpaboot.repository.postgresql.IPostgresqlBasicTypeRepository;

@Service
@Transactional
public class BasicTypeService {

	@Autowired
	private IPostgresqlBasicTypeRepository postgresqlRepository;
	@Autowired
	private IMysqlBasicTypeRepository mysqlRepository;

	@Transactional(transactionManager = "postgresqlTransactionManager")
	public PostgresqlBasicTypeEntity saveWithPostgresql(PostgresqlBasicTypeEntity entity) {
		return postgresqlRepository.save(entity);
	}

	@Transactional(transactionManager = "postgresqlTransactionManager", readOnly = true, propagation = Propagation.REQUIRED)
	public Optional<PostgresqlBasicTypeEntity> findByIdWithPostgresql(Long id) {
		return postgresqlRepository.findById(id);
	}

	@Transactional(transactionManager = "mysqlTransactionManager")
	public MysqlBasicTypeEntity saveWithMysql(MysqlBasicTypeEntity entity) {
		return mysqlRepository.save(entity);
	}

	@Transactional(transactionManager = "mysqlTransactionManager", readOnly = true, propagation = Propagation.REQUIRED)
	public Optional<MysqlBasicTypeEntity> findByIdWithMysql(Long id) {
		return mysqlRepository.findById(id);
	}
}
