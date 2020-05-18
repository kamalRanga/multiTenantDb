package com.uno.repository.common;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.uno.model.TenantLogin;

/**
 * Created by kamal
 */
public interface LoginRepository extends MongoRepository<TenantLogin, String> {
	@Query("{ 'username' : ?0 , 'password' : ?1 }")
	TenantLogin login(String userName,String password);
}