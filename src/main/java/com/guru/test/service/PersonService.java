package com.guru.test.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guru.test.model.Person;
import com.guru.test.repository.PersonRepository;

/**
 * Created by kamal 
 */
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public void addPerson(Person person)
    {
        personRepository.save(person);
    }

    public Optional<Person> getPerson(String id)
    {
        return personRepository.findById(id);
    }


}
