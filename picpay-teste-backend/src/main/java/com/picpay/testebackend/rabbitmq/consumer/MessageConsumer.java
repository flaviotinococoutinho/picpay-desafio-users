package com.picpay.testebackend.rabbitmq.consumer;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.picpay.testebackend.config.RabbitMQConfig;
import com.picpay.testebackend.dto.UserQuerySearchDTO;
import com.picpay.testebackend.service.IUserQuerySearchService;

@Service
public class MessageConsumer {
    
    private static final Logger LOG = LoggerFactory.getLogger(MessageConsumer.class);
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private IUserQuerySearchService iUserQuerySearchService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME, containerFactory = "listenerContainerFactory")
    public void getMessages(String message) {
    	UserQuerySearchDTO userQuerySearchDTO =new UserQuerySearchDTO();
    	try {
			userQuerySearchDTO = objectMapper.readValue(message, UserQuerySearchDTO.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
			 LOG.error("Erro em converter a msg");
		} catch (JsonMappingException e) {
			e.printStackTrace();
			 LOG.error("Erro em mapear a msg");
		} catch (IOException e) {
			e.printStackTrace();
			 LOG.error("Erro em ler formato msg");
		}
    	iUserQuerySearchService.save(userQuerySearchDTO);
        LOG.info("Busca de pesquisa de usuário salva: " + userQuerySearchDTO.getQuery());
    }
}
