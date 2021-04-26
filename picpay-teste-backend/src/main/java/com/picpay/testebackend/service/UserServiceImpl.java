package com.picpay.testebackend.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.picpay.testebackend.model.User;
import com.picpay.testebackend.repository.es.UserElasticSearchRepository;
import com.picpay.testebackend.repository.rdbms.UserRdbmsRepository;


	@Service
	public class UserServiceImpl implements UserService {

	    @Autowired
	    UserElasticSearchRepository userElasticsearchRepository;
	    
	    @Autowired
	    UserRdbmsRepository userRDBMSRepository;
	    
	    @Override
	    public long getUserCount() {
	        return userElasticsearchRepository.count();
	    }

	    @Override
	    public List<User> getAllUsers(int page, int size) {
	        PageRequest pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "_id"));
	        return userElasticsearchRepository.findAll(pageable).getContent();
	    }

		@Override
		public User getUserById(String id) {
			return userElasticsearchRepository.findById(id).orElse(null);
		}

	    @Override
	    public List<User> getUsersByPattern(String query) {
	        List<User> firstNamesList = userElasticsearchRepository.getUsersByNomeContaining(query);
	        List<User> lastNamesList = userElasticsearchRepository.getUsersByUsernameContaining(query);
	        return Stream.concat(firstNamesList.stream(), lastNamesList.stream()).distinct().collect(Collectors.toList());
	    }

	 //   @Override
	  //  public Page<User> getUsersByGivenName(String name) {
	    //    return userElasticsearchRepository.getUsersByNomeOrUsernameContaining(name);
	   // }

	    @Override
	    public void saveUser() {
	        final com.picpay.testebackend.model.sql.User user = new com.picpay.testebackend.model.sql.User();
	        user.setNome("Flavio T Coutinho");
	        user.setUsername("flavio.tinoco");
	        user.setUuid("dshajdksajhdskajhdsak");
	        userRDBMSRepository.save(user);
	    }
	}
