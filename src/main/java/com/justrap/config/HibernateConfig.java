package com.justrap.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.justrap.model.Role;

 
@Configuration
@EnableTransactionManagement
public class HibernateConfig {
 
    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setPackagesToScan("com.justrap.model");
        sessionFactory.setAnnotatedClasses(Role.class);
        sessionFactory.setHibernateProperties(getHibernateProperties());
        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }
    
    private Properties getHibernateProperties() {
        Properties hibernateProperties = new Properties();
        
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        hibernateProperties.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        hibernateProperties.setProperty("hibernate.connection.username", "postgres");
        hibernateProperties.setProperty("hibernate.connection.password", "root");
        hibernateProperties.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost/raplay");
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
        hibernateProperties.setProperty("hibernate.archive.autodetection", "class,hbm");
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        
        hibernateProperties.setProperty("hibernate.c3p0.min_size", "5");
        hibernateProperties.setProperty("hibernate.c3p0.max_size", "20");
        hibernateProperties.setProperty("hibernate.c3p0.acquire_increment", "2");
        hibernateProperties.setProperty("hibernate.c3p0.max_statements", "150");
 
        return hibernateProperties;
    }
    
}
