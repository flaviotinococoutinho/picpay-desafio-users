package com.picpay.testebackend.rabbitmq.producer;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.picpay.testebackend.config.RabbitMQConfig;
import com.picpay.testebackend.dto.UserQuerySearchDTO;

@Service
public  class MessageProducer {

    @Autowired
    public RabbitTemplate rabbitTemplate;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    //private static final Logger LOG = LoggerFactory.getLogger(MessageProducer.class);

    @Async
	public void sendStoreQuerySearchUser(Object obj) throws JsonProcessingException {
		//Evitar problema de serialização e evitar criacao do toString específico para a carga json 
		String data = objectMapper.writeValueAsString(obj);
		rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE_NAME,"querysearchusers.new", data);
	}

}
