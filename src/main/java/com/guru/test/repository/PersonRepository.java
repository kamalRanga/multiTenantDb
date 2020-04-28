package com.guru.test.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.guru.test.model.Person;

/**
 * Created by kamal 
 */
public interface PersonRepository extends MongoRepository<Person,String> {

}