package idv.hsiehpinghan.springbootstartersecurityboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import idv.hsiehpinghan.springbootstartersecurityboot.entity.ConfigurationEntity;

public interface ConfigurationRepository extends JpaRepository<ConfigurationEntity, ConfigurationEntity.Id> {
}
