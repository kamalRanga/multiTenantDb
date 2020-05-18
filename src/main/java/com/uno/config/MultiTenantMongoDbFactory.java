package com.uno.config;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.uno.model.TenantDb;

/**
 * Created by kamal
 */
@Configuration
@ConditionalOnProperty(name = "spring.boot.multitenant.mongodb.enabled", havingValue = "true", matchIfMissing = true)
public class MultiTenantMongoDbFactory extends SimpleMongoDbFactory {

	private static final Logger logger = LogManager.getLogger(MultiTenantMongoDbFactory.class);

	private String DEFAULT_DB = "masterdb";

	public MultiTenantMongoDbFactory(final MongoProperties mongo) {
		super(new MongoClient(mongo.getHost(), mongo.getPort()), mongo.getDatabase());
	}

	@Primary
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
		MongoTemplate mongoTemplate;
		mongoTemplate = new MongoTemplate(primaryFactory(getPrimary()));
		masterDb = mongoTemplate.getDb();
		return mongoTemplate;
	}

	@Bean(name = "secondaryMongoTemplate")
	public MongoTemplate secondaryMongoTemplate() throws Exception {
		return new MongoTemplate(secondaryFactory(getSecondary()));
	}

	@Bean
	@Primary
	public MongoDbFactory primaryFactory(final MongoProperties mongo) throws Exception {
		return new SimpleMongoDbFactory(new MongoClient(mongo.getHost(), mongo.getPort()), mongo.getDatabase());
	}

	@Bean
	public MongoDbFactory secondaryFactory(final MongoProperties mongo) throws Exception {
		return new SimpleMongoDbFactory(new MongoClient(mongo.getHost(), mongo.getPort()), mongo.getDatabase());
	}

	private DB masterDb;

	@Override
	public DB getDb() throws DataAccessException {

		try {
			TenantDb tenantDbDetails = (TenantDb) RequestContextHolder.getRequestAttributes().getAttribute("tenantId",
					RequestAttributes.SCOPE_REQUEST);
			logger.debug("Creating/Getting a db with name" + tenantDbDetails.getDbName());
			if (tenantDbDetails != null && tenantDbDetails.getDbName() != null) {

				return new MultiTenantMongoDbFactory(getTenantProperties(tenantDbDetails)).getDb();
			}
		} catch (Exception e) {
			logger.fatal("Error occured during creating DB " + e);
			return masterDb;
		}
		return masterDb;
	}

	public MongoProperties getTenantProperties(TenantDb tenantDbDetails) {
		MongoProperties mongo = new MongoProperties();
		mongo.setHost(tenantDbDetails.getDbHost());
		mongo.setUsername(tenantDbDetails.getDbUsername());
		mongo.setPassword(tenantDbDetails.getDbPassword().toCharArray());
		mongo.setPort(tenantDbDetails.getDbPort());
		mongo.setDatabase(tenantDbDetails.getDbName());
		return mongo;
	}

}
