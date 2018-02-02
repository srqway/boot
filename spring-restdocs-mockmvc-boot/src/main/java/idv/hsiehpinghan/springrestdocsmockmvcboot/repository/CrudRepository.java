package idv.hsiehpinghan.springrestdocsmockmvcboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import idv.hsiehpinghan.springrestdocsmockmvcboot.entity.CrudEntity;

public interface CrudRepository extends JpaRepository<CrudEntity, Integer> {
}
