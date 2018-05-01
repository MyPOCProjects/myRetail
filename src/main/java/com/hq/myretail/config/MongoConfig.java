
package com.hq.myretail.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;

public class MongoConfig {

    @Value("mongo.db.url")
    private String mongoDbUrl;

    @Value("mongo.db.name")
    private String mongoDbName;

    @SuppressWarnings("resource")
    @Bean
    public MongoTemplate mongoTemplate() throws IOException {
        final EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();
        mongo.setBindIp(mongoDbUrl);

        final MongoClient mongoClient = mongo.getObject();
        final MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, mongoDbName);
        return mongoTemplate;
    }

}
