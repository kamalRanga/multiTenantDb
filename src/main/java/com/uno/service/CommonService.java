package com.uno.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uno.model.Tenant;
import com.uno.model.TenantDb;
import com.uno.repository.common.CredsRepository;
import com.uno.repository.common.TenantRepository;

@Service
public class CommonService {
	@Autowired
	TenantRepository tenantRepository;
	
	@Autowired
	private CredsRepository credsRepository;

	public Tenant addTenant(Tenant tenant) {
		return tenantRepository.save(tenant);
	}

	public Tenant getTenant(String id) {
		return tenantRepository.findOne(id);
	}

	public boolean findByDomain(String domain) {
		return tenantRepository.exists(domain);
	}
	

	public TenantDb addCreds(TenantDb obj) {
		return credsRepository.save(obj);
	}
	
	public TenantDb getOrg(String id) {
		return credsRepository.findOne(id);
	}
	
	public TenantDb getTenanatDbDetails(String orgName) {
		return credsRepository.findByOrgName(orgName);
	}
}
