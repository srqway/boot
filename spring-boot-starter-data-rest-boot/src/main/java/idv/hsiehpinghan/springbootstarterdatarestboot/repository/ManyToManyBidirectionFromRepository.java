package idv.hsiehpinghan.springbootstarterdatarestboot.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import idv.hsiehpinghan.springbootstarterdatarestboot.entity.ManyToManyBidirectionFromEntity;

public interface ManyToManyBidirectionFromRepository
		extends PagingAndSortingRepository<ManyToManyBidirectionFromEntity, Integer> {
	List<ManyToManyBidirectionFromEntity> findByName(@Param("name") String name);
}
