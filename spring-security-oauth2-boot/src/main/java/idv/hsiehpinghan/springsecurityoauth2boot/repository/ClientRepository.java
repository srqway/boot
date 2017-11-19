package idv.hsiehpinghan.springsecurityoauth2boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import idv.hsiehpinghan.springsecurityoauth2boot.entity.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity, String> {
}
