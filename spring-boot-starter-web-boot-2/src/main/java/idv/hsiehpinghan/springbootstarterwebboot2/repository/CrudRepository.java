package idv.hsiehpinghan.springbootstarterwebboot2.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import idv.hsiehpinghan.springbootstarterwebboot2.entity.CrudEntity;

public interface CrudRepository extends PagingAndSortingRepository<CrudEntity, Integer> {
}
