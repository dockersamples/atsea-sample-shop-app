package com.docker.atsea.configuration;

import java.util.Properties;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.docker.atsea.repositories",
		entityManagerFactoryRef = "entityManagerFactory",
		transactionManagerRef = "transactionManager")
@EnableTransactionManagement
public class JpaConfiguration {

	@Autowired
	private Environment environment;


	/*
	 * Populate SpringBoot DataSourceProperties from application.yml 
	 */
	@Bean
	@Primary
	@ConfigurationProperties(prefix = "datasource.atsea")
	public DataSourceProperties dataSourceProperties() {
		DataSourceProperties dataSourceProperties = new DataSourceProperties();

		// Set password to connect to postgres using Docker secrets.
		try(BufferedReader br = new BufferedReader(new FileReader("/run/secrets/postgres_password"))) {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			dataSourceProperties.setDataPassword(sb.toString());
		} catch (IOException e) {
			System.err.println("Could not successfully load DB password file");
		}
		
		return dataSourceProperties;
	}

	/*
	 * Configure HikariCP pooled DataSource.
	 */
	@Bean
	public DataSource dataSource() {
		DataSourceProperties dataSourceProperties = dataSourceProperties();
			HikariDataSource dataSource = (HikariDataSource) DataSourceBuilder
					.create(dataSourceProperties.getClassLoader())
					.driverClassName(dataSourceProperties.getDriverClassName())
					.url(dataSourceProperties.getUrl())
					.username(dataSourceProperties.getUsername())
					.password(dataSourceProperties.getPassword())
					.type(HikariDataSource.class)
					.build();
			return dataSource;
	}

	/*
	 * Entity Manager Factory setup.
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean.setPackagesToScan(new String[] { "com.docker.atsea.model" });
		factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
		factoryBean.setJpaProperties(jpaProperties());
		return factoryBean;
	}

	/*
	 * Provider specific adapter.
	 */
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		return hibernateJpaVendorAdapter;
	}

	/*
	 * Provider specific properties.
	 */
	private Properties jpaProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getRequiredProperty("datasource.atsea.hibernate.dialect"));
		properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("datasource.atsea.hibernate.hbm2ddl.method"));
		properties.put("hibernate.show_sql", environment.getRequiredProperty("datasource.atsea.hibernate.show_sql"));
		properties.put("hibernate.format_sql", environment.getRequiredProperty("datasource.atsea.hibernate.format_sql"));
		if(StringUtils.isNotEmpty(environment.getRequiredProperty("datasource.atsea.defaultSchema"))){
			properties.put("hibernate.default_schema", environment.getRequiredProperty("datasource.atsea.defaultSchema"));
		}
		return properties;
	}

	@Bean
	@Autowired
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(emf);
		return txManager;
	}

}
