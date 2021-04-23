package com.picpay.testebackend.service;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.picpay.testebackend.dto.UserDTO;
import com.picpay.testebackend.model.User;
import com.picpay.testebackend.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	

		@Autowired
		private UserRepository userRepository;
		
		public User getOne(Integer id) {
        return userRepository.getOne(id);
		}

		public Page<User> findByNomeLikeOrUsernameLike(String query, Integer pagina, Integer tamanho) {
			PageRequest pageRequest = PageRequest.of(pagina, tamanho);
	        return userRepository.buscaNomeOuUsername(query,pageRequest );
			}
		
		@Transactional
		public User create(@NotNull UserDTO userDTO) {
			User user = new User();
			user.setNome(userDTO.getNome());
			user.setUsername(userDTO.getUsername());
			userRepository.save(user);
			return user;
		}
	}
