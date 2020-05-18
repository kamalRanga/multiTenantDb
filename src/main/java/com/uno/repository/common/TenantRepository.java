package com.uno.repository.common;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.uno.model.Tenant;

/**
 * Created by kamal 
 */
public interface TenantRepository extends MongoRepository<Tenant,String> {

}