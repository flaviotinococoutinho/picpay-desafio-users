package com.picpay.testebackend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.picpay.testebackend.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query(value = "SELECT * FROM picpay_teste.user WHERE (picpay_teste.user.nome LIKE :query OR picpay_teste.user.username LIKE :query)",nativeQuery = true)
	Page<User> buscaNomeOuUsername(@Param("query") String name,Pageable pageable);
}
