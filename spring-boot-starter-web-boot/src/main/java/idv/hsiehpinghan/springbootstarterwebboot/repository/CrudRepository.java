package idv.hsiehpinghan.springbootstarterwebboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import idv.hsiehpinghan.springbootstarterwebboot.entity.CrudEntity;

public interface CrudRepository extends JpaRepository<CrudEntity, Integer> {
}
