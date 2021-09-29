package com.beardtrust.webapp.accountservice.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;

import com.beardtrust.webapp.accountservice.dtos.UserDTO;
import com.beardtrust.webapp.accountservice.entities.UserEntity;
import com.beardtrust.webapp.accountservice.repos.AuthorizationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthorizationServiceImpl implements AuthorizationService {
	
	AuthorizationRepository authorizationRepository;
	
	@Autowired
	public AuthorizationServiceImpl(AuthorizationRepository authorizationRepository) {
		this.authorizationRepository = authorizationRepository;
	}


	@Override
	public UserDTO getUserByUserId(String id) {
		Optional<UserEntity> user = authorizationRepository.findById(id);

		UserDTO userDTO = null;

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		if (user.isPresent()) {
			userDTO = modelMapper.map(user.get(), UserDTO.class);
		}

		return userDTO;
	}
}
