package com.demobai11.config;

import com.demobai11.service.ISmartPhoneSV;
import com.demobai11.service.SmartPhoneSv;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@EnableWebMvc
@Configuration
@EnableTransactionManagement
@ComponentScan("com.demobai11.controller")
@EnableJpaRepositories("com.demobai11.repository")
@EnableAspectJAutoProxy
@EnableSpringDataWebSupport
public class Appconfig implements WebMvcConfigurer, ApplicationContextAware {
    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        System.out.println("6");
        SpringResourceTemplateResolver tem = new SpringResourceTemplateResolver();
        tem.setApplicationContext(applicationContext);
        tem.setPrefix("/WEB-INF/views/");
        tem.setSuffix(".html");
        tem.setTemplateMode(TemplateMode.HTML);
        tem.setCharacterEncoding("UTF-8");
        return tem;
    }
    @Bean
    public SpringTemplateEngine templateEngine() {
        System.out.println("7");
        SpringTemplateEngine temp = new SpringTemplateEngine();
        temp.setTemplateResolver(templateResolver());
        return temp;
    }
    @Bean
    public ThymeleafViewResolver viewResolver() {
        System.out.println("8");
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding("UTF-8");
        resolver.setContentType("UTF-8");
        return resolver;
    }
    //JPA
    @Bean
    @Qualifier(value = "entityManager")
    public EntityManager entityManager(EntityManagerFactory entityManagerFactory){
        System.out.println("9");
        return entityManagerFactory.createEntityManager();
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        System.out.println("10");
        LocalContainerEntityManagerFactoryBean eMFB = new LocalContainerEntityManagerFactoryBean();
        eMFB.setDataSource(dataSource());
        eMFB.setPackagesToScan("com.demobai11.model");

        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        eMFB.setJpaVendorAdapter(jpaVendorAdapter);
        eMFB.setJpaProperties(additionalProperties());
        return eMFB;
    }
    @Bean
    public DataSource dataSource(){
        System.out.println("11");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/demosmartphone");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        return dataSource;
    }
    public Properties additionalProperties() {
        System.out.println("12");
        Properties pr = new Properties();
        pr.setProperty("hibernate.hbm2ddl.auto","update");
        pr.setProperty("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");
        pr.setProperty("show_sql", "true");
        return pr;
    }

    //    Hỗ trợ transaction
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory eMF){
        System.out.println("13");
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(eMF);
        return transactionManager;
    }

    @Bean
    ISmartPhoneSV iSmartPhoneSV(){
        return new SmartPhoneSv();
    }
}
