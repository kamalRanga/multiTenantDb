package com.guru.test.config;

import java.net.UnknownHostException;

import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

/**
 * Created by kamal 
 */
public class MultiTenantMongoDbFactory extends SimpleMongoDbFactory {

    public String DEFAULT_DB = "demo";

    public MultiTenantMongoDbFactory(MongoClientURI uri) throws UnknownHostException {
        super(uri);
    }

    @Override
    public MongoDatabase getDb() throws DataAccessException {

        // Check the RequestContext
        Object tenant = RequestContextHolder.getRequestAttributes().getAttribute("tenantId", RequestAttributes.SCOPE_REQUEST);

        if (tenant instanceof String)
        {
            return getDb(tenant.toString());
        }
        // Return a default DB
        return super.getDb(DEFAULT_DB);
    }

}
