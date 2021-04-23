package com.picpay.testebackend.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.picpay.testebackend.dto.UserDTO;

public class LogProcess implements ItemProcessor<UserDTO, UserDTO> {

	private static final Logger LOGGER = LoggerFactory.getLogger(LogProcess.class);

	@Override
	public UserDTO process(UserDTO item) throws Exception {
		LOGGER.info("Processing information: {}", item);
		return item;
	}
}

