package com.log.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by docker on 12/12/16.
 * This class will setup the configuation for interacting with database.
 */

@Configuration
@EnableJpaRepositories(basePackages = {"com.log.exception"})
@EnableTransactionManagement
public class PersistenceContext {

    @Bean(destroyMethod = "close")
    DataSource dataSource(Environment env) {
        HikariConfig dataSourceConfig = new HikariConfig();
        dataSourceConfig.setDriverClassName(env.getRequiredProperty("mysql.db.driver"));
        dataSourceConfig.setJdbcUrl(env.getRequiredProperty("mysql.db.url"));
        dataSourceConfig.setUsername(env.getRequiredProperty("mysql.db.username"));
        dataSourceConfig.setPassword(env.getRequiredProperty("mysql.db.password"));
        return new HikariDataSource(dataSourceConfig);
    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Environment env) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("com.log.exception");

        Properties jpaProperties = new Properties();
        /* Configures the used database dialect. This allows Hibernate to create SQL that is optimized for the used database. */
        jpaProperties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));

        /* Specifies the action that is invoked to the database when the Hibernate SessionFactory is created or closed. */
        jpaProperties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));

        /* Configures the naming strategy that is used when Hibernate creates new database objects and schema elements */
        jpaProperties.put("hibernate.ejb.naming_strategy", env.getRequiredProperty("hibernate.ejb.naming_strategy"));

        /* If the value of this property is true, Hibernate writes all SQL statements to the console. */
        jpaProperties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));

        /* If the value of this property is true, Hibernate will format the SQL that is written to the console. */
        jpaProperties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));

        entityManagerFactoryBean.setJpaProperties(jpaProperties);
        return entityManagerFactoryBean;
    }

    @Bean
    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}
