package com.uno.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uno.model.Role;
import com.uno.repository.template.TemplateRepository;

@Service
public class TemplateService {
	@Autowired
	TemplateRepository templateRepository;

	public void commonData() {
		try {
			templateRepository.save(new Role("SUPERADMIN", "Role set for super administrater"));
			templateRepository.save(new Role("ADMIN", "Role set for administrater"));
		} catch (org.springframework.dao.DuplicateKeyException e) {
			// TODO - Do want you want
		}

	}

	public Role getRole(String roleName) {
		return templateRepository.findByFirstname(roleName);
	}

	public List<Role> getAll() {
		return templateRepository.findAll();
	}

}
