package com.beardtrust.webapp.accountservice.services;

import com.beardtrust.webapp.accountservice.dtos.UserDTO;

public interface AuthorizationService {
	
	UserDTO getUserByUserId(String id);
}
