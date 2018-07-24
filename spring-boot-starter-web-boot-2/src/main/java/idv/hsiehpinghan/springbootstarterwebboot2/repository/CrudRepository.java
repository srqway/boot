package idv.hsiehpinghan.springbootstarterwebboot2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import idv.hsiehpinghan.springbootstarterwebboot2.entity.CrudEntity;

public interface CrudRepository extends JpaRepository<CrudEntity, Integer> {
}
