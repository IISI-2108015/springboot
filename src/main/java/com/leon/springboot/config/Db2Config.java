package com.leon.springboot.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.leon.springboot.db2.dao.ProductRepository;
import com.leon.springboot.db2.model.Product;

@Configuration
@EnableJpaRepositories( //
		basePackageClasses = ProductRepository.class, //
		entityManagerFactoryRef = "db2EntityManagerFactory", //
		transactionManagerRef = "db2TransactionManager")
@EnableTransactionManagement
public class Db2Config {

	@Bean("db2DataSource")
	@ConfigurationProperties("spring.datasource.db2")
	public DataSource db2DataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean("db2EntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean db2EntityManagerFactory(
			@Qualifier("db2DataSource") DataSource db2DataSource, EntityManagerFactoryBuilder builder) {
		return builder.dataSource(db2DataSource).packages(Product.class).persistenceUnit("db2").build();
	}

	@Bean("db2TransactionManager")
	public PlatformTransactionManager db2TransactionManager(
			@Qualifier("db2EntityManagerFactory") LocalContainerEntityManagerFactoryBean db2EntityManagerFactory) {
		return new JpaTransactionManager(db2EntityManagerFactory.getObject());
	}

}
