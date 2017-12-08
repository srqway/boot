package idv.hsiehpinghan.springbootstartersecurityboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import idv.hsiehpinghan.springbootstartersecurityboot.entity.ResourceEntity;

public interface ResourceRepository extends JpaRepository<ResourceEntity, Integer> {
}
