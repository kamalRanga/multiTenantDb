package com.guru.test.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.guru.test.model.Organisation;

/**
 * Created by kamal 
 */
public interface OrgRepository extends MongoRepository<Organisation,String> {

}