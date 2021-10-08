package com.beardtrust.webapp.accountservice.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;

import com.beardtrust.webapp.accountservice.dtos.UserDTO;
import com.beardtrust.webapp.accountservice.entities.UserEntity;
import com.beardtrust.webapp.accountservice.repos.AuthorizationRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthorizationServiceImpl implements AuthorizationService {
	private final AuthorizationRepository authorizationRepository;

	/**
	 * Instantiates a new Authorization service.
	 *
	 * @param authorizationRepository the authorization repository
	 */
	@Autowired
	public AuthorizationServiceImpl(AuthorizationRepository authorizationRepository) {
            log.trace("Creating Authorization Service...");
		this.authorizationRepository = authorizationRepository;
	}


	@Override
	public UserDTO getUserByUserId(String id) {
            log.trace("Authorization searching for user by Id...");
            log.debug("Authorization searching by Id: " + id);
		Optional<UserEntity> user = authorizationRepository.findById(id);

		UserDTO userDTO = null;

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		if (user.isPresent()) {
                    log.trace("User found, creating model...");
			userDTO = modelMapper.map(user.get(), UserDTO.class);
                        log.debug("Model created: " + userDTO);
		}

                log.trace("Authorization finished. Returning DTO...");
		return userDTO;
	}
}
