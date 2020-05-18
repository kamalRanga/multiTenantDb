package com.uno.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.DB;
import com.mongodb.MongoClient;

//@Configuration
//@ConditionalOnProperty(name = "spring.boot.multitenant.mongodb.enabled", havingValue = "true", matchIfMissing = true)
public class MongoDbConfiguration {

	/*@Primary
	@Bean(name = "primary")
	@ConfigurationProperties(prefix = "spring.data.mongodb")
	public MongoProperties getPrimary() {
		return new MongoProperties();
	}

	@Bean(name = "secondary")
	@ConfigurationProperties(prefix = "mongodb")
	public MongoProperties getSecondary() {
		return new MongoProperties();
	}

	@Primary
	@Bean(name = "primaryMongoTemplate")
	public MongoTemplate primaryMongoTemplate() throws Exception {
		return new MongoTemplate(primaryFactory(getPrimary()));
	}

	@Bean(name = "secondaryMongoTemplate")
	public MongoTemplate secondaryMongoTemplate() throws Exception {
		return new MongoTemplate(secondaryFactory(getSecondary()));
	}

	@Bean
	@Primary
	public MongoDbFactory primaryFactory(final MongoProperties mongo) throws Exception {
		return new MultiTenantMongoDbFactory(mongo);
	}

	@Bean
	public MongoDbFactory secondaryFactory(final MongoProperties mongo) throws Exception {
		return  new SimpleMongoDbFactory(new MongoClient(mongo.getHost(), mongo.getPort()), mongo.getDatabase());
	}*/

	/*public MongoTemplate tenantDbTemplate() throws Exception {
		return new MongoTemplate(secondaryFactory(getSecondary()));
	}

	public MongoDbFactory tenantDbFactory(final MongoProperties mongo) throws Exception {
		return new SimpleMongoDbFactory(new MongoClient(mongo.getHost(), mongo.getPort()), mongo.getDatabase());
	}
*/
}