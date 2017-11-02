package idv.hsiehpinghan.springbootstarterwebboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import idv.hsiehpinghan.springbootstarterwebboot.entity.BasicEntity;

public interface BasicRepository extends JpaRepository<BasicEntity, Integer> {
	List<BasicEntity> findByString(String string);
}
