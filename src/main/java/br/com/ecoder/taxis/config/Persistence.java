package br.com.ecoder.taxis.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.jolbox.bonecp.BoneCPDataSource;

@Configuration
@EnableJpaRepositories("br.com.ecoder.taxis.repository")
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class Persistence {

    private static final String PROPERTY_NAME_DATABASE_DRIVER = "javax.persistence.jdbc.driver";
    private static final String PROPERTY_NAME_DATABASE_URL = "javax.persistence.jdbc.url";
    private static final String PROPERTY_NAME_DATABASE_USERNAME = "javax.persistence.jdbc.user";
    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "javax.persistence.jdbc.password";

    @Resource
    private Environment environment;

    @Bean
    public BoneCPDataSource dataSource() {

        BoneCPDataSource dataSource = new BoneCPDataSource();

        dataSource.setDriverClass(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
        dataSource.setJdbcUrl(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
        dataSource.setUsername(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
        dataSource.setPassword(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws ClassNotFoundException {

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory());
        transactionManager.setDataSource(dataSource());

        return transactionManager;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {

        Properties jpaProterties = new Properties();
        jpaProterties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        jpaProterties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        jpaProterties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        jpaProterties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("br.com.ecoder.taxis.model");
        factory.setDataSource(dataSource());
        factory.setJpaProperties(jpaProterties);
        factory.afterPropertiesSet();

        return factory.getObject();
    }

}
