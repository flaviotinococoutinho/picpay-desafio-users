package com.picpay.testebackend.repository.es;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.picpay.testebackend.model.User;

@Repository
public interface UserElasticSearchRepository extends ElasticsearchRepository<User, Integer> {
	//Page<User> findByNomeLikeOrUsernameLike(String name, String username, Pageable pageable);
}

