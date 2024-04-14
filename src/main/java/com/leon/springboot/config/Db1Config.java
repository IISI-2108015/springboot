package com.leon.springboot.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.leon.springboot.db1.dao.UserDao;
import com.leon.springboot.db1.model.User;

@Configuration
@EnableJpaRepositories( //
		basePackageClasses = UserDao.class, //
		entityManagerFactoryRef = "db1EntityManagerFactory", //
		transactionManagerRef = "db1TransactionManager")
@EnableTransactionManagement
public class Db1Config {

	@Bean("db1DataSource")
	@Primary
	@ConfigurationProperties("spring.datasource.db1")
	public DataSource db1DataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean("db1EntityManagerFactory")
	@Primary
	public LocalContainerEntityManagerFactoryBean db1EntityManagerFactory(
			@Qualifier("db1DataSource") DataSource db1DataSource, EntityManagerFactoryBuilder builder) {
		return builder.dataSource(db1DataSource).packages(User.class).persistenceUnit("db1").build();
	}

	@Bean("db1TransactionManager")
	@Primary
	public PlatformTransactionManager db1TransactionManager(
			@Qualifier("db1EntityManagerFactory") LocalContainerEntityManagerFactoryBean db1EntityManagerFactory) {
		return new JpaTransactionManager(db1EntityManagerFactory.getObject());
	}

}
