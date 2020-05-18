package com.uno.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.uno.repository.common", mongoTemplateRef = "primaryMongoTemplate")
public class PrimaryMongoConnection {

}