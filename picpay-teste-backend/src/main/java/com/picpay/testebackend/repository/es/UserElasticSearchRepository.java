package com.picpay.testebackend.repository.es;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.picpay.testebackend.model.User;

@Repository
public interface UserElasticSearchRepository extends ElasticsearchRepository<User, String> {

	Page<User> findAll(Pageable pageable);

	List<User> getUsersByNomeContaining(String query);

	//@Query("{\"multi_match\": {\"query\": \"?0\", \"fields\": [\"nome\", \"username\"]}}")
	//Page<User> getUsersByNomeOrUsernameContaining(String name);

	List<User> getUsersByUsernameContaining(String query);
}
