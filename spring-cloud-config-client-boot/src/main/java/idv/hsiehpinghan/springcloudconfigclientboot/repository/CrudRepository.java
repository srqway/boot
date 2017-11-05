package idv.hsiehpinghan.springcloudconfigclientboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import idv.hsiehpinghan.springcloudconfigclientboot.entity.CrudEntity;

public interface CrudRepository extends JpaRepository<CrudEntity, Integer> {
}
