package idv.hsiehpinghan.springbootstarterhateoasboot.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import idv.hsiehpinghan.springbootstarterhateoasboot.entity.CrudEntity;

public interface CrudRepository extends PagingAndSortingRepository<CrudEntity, Integer> {
}
