package com.uno.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uno.model.Role;
import com.uno.model.TenantLogin;
import com.uno.repository.common.LoginRepository;
import com.uno.repository.common.RoleRepository;

/**
 * Created by kamal
 */
@Service
public class LoginService {

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private RoleRepository roleRepository;

	public TenantLogin add(TenantLogin obj) {
		return loginRepository.save(obj);
	}

	public TenantLogin getLoginDetails(String id) {
		return loginRepository.findOne(id);
	}

	public TenantLogin login(TenantLogin tenantLogin) {
		tenantLogin = loginRepository.login(tenantLogin.getUsername(), tenantLogin.getPassword());
		return tenantLogin;
	}

	public void addRole(List<Role> templateRole) {
		roleRepository.save(templateRole);
	}

}
