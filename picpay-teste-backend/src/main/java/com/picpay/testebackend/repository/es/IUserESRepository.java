package com.picpay.testebackend.repository.es;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.picpay.testebackend.model.UserModel;

public interface IUserESRepository extends ElasticsearchRepository<UserModel, Long> {
}
