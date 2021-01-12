package com.example.ISA2020;

import java.util.stream.Stream;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.DatabaseStartupValidator;

import com.example.ISA2020.files.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class Isa2020Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Isa2020Application.class, args);

	}
	
	@Bean
    public DatabaseStartupValidator databaseStartupValidator(DataSource dataSource) {
		DatabaseStartupValidator dsv = new DatabaseStartupValidator();
        dsv.setDataSource(dataSource);
        dsv.setValidationQuery(DatabaseDriver.MYSQL.getValidationQuery());
        return dsv;
    }
	
	@Bean
	public static BeanFactoryPostProcessor dependsOnPostProcessor() {
	    return bf -> {
	        // Let beans that need the database depend on the DatabaseStartupValidator
	        // like the JPA EntityManagerFactory or Flyway
	        String[] flyway = bf.getBeanNamesForType(JdbcTemplate .class);
	        Stream.of(flyway)
	                .map(bf::getBeanDefinition)
	                .forEach(it -> it.setDependsOn("databaseStartupValidator"));

	        String[] jpa = bf.getBeanNamesForType(EntityManagerFactory.class);
	        Stream.of(jpa)
	                .map(bf::getBeanDefinition)
	                .forEach(it -> it.setDependsOn("databaseStartupValidator"));
	    };
	}

}
