package com.example.studentregistration.conf;


import com.example.studentregistration.entity.StudentRepo;
import com.example.studentregistration.init.StudentInitializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

@Configuration
@ConditionalOnBean(StudentInitializer.class)
public class Config {

    @Bean
    public StudentInitializer studentInitializer(ResourceLoader resourceLoader, ApplicationEventPublisher publisher, StudentRepo repo) {
        return new StudentInitializer(resourceLoader, publisher, repo);
    }
}
