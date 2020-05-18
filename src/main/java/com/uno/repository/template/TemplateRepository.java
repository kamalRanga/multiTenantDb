package com.uno.repository.template;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.uno.model.Role;

/**
 * Created by kamal 
 */
public interface TemplateRepository extends MongoRepository<Role,String> {
	@Query("{ 'roleName' : ?0 }")
    Role findByFirstname(String roleName);
}