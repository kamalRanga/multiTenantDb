package com.guru.test.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guru.test.model.Organisation;
import com.guru.test.repository.OrgRepository;

/**
 * Created by kamal
 */
@Service
public class OrgService {

	@Autowired
	private OrgRepository orgRepository;

	public void addOrg(Organisation org) {
		orgRepository.save(org);
	}

	public Optional<Organisation> getOrg(String id) {
		return orgRepository.findById(id);
	}

}
