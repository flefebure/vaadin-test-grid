package com.test;

import com.vaadin.flow.spring.annotation.EnableVaadin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableVaadin
public class SoftbridgeTest extends SpringBootServletInitializer {
    private static final Logger log = LoggerFactory.getLogger(SoftbridgeTest.class);

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(SoftbridgeTest.class, args);
        String[] beanNames = ctx.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            log.debug(beanName);
        }
    }

}
