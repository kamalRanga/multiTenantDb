package com.uno.repository.common;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.uno.model.TenantDb;

/**
 * Created by kamal 
 */
public interface CredsRepository extends MongoRepository<TenantDb,String> {
	 @Query("{ 'orgName' : ?0 }")
     TenantDb findByOrgName(String orgName);
}