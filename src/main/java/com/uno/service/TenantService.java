package com.uno.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uno.model.TenantLogin;
import com.uno.repository.common.LoginRepository;
import com.uno.repository.common.TenantRepository;

/**
 * Created by kamal
 */
@Service
public class TenantService {

	
	@Autowired
	private LoginRepository loginRepository;

	

}
