package com.neosoft.webflux.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
 
@Configuration
@EnableReactiveMongoRepositories
@PropertySource("classpath:application.properties")
public abstract class MongoConfig  extends AbstractReactiveMongoConfiguration{

	 @Value("${spring.data.mongodb.port}")
	 private String port;
	    @Value("${spring.data.mongodb.database}")
	    private String  database;

	   	  @Override
	  protected String getDatabaseName() {
	    return database;
	  }
	   	 @Bean 
	   	 public ReactiveMongoTemplate reactiveMongoTemplate() {
	   		 return new ReactiveMongoTemplate(reactiveMongoClient(),getDatabaseName());
	   	 }	   	  
}
