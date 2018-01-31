package idv.hsiehpinghan.springbootstarterdatarestboot.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import idv.hsiehpinghan.springbootstarterdatarestboot.entity.UserEntity;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, String> {
	List<UserEntity> findByAge(@Param("age") Integer age);
}
