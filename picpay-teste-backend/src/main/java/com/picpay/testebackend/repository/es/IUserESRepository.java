package com.picpay.testebackend.repository.es;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.picpay.testebackend.model.UserModel;

@Repository
public interface IUserESRepository extends ElasticsearchRepository<UserModel, Long> {

	@Query("{\"match\": {\"nome\": {\"query\": \"?0\"}}}")
	Page <UserModel> findByNome(String query, Pageable pageable);
	
	@Query("{\"match\": {\"username\": {\"query\": \"?0\"}}}")
	Page <UserModel> findByUsername(String query, Pageable pageable);
}
