package com.stackroute.config;

import com.stackroute.domain.User;
import com.stackroute.repository.UserRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
@Configuration
@PropertySource("classpath:seed.properties")
@Component
public class DatabaseLoader implements ApplicationListener<ApplicationReadyEvent> {

    @Value("${userId1}")
    private int userId1;
    @Value("${userName1}")
    private String userName1;
    @Value("${password1}")
    private String password1;
    @Value("${age1}")
    private int age1;
    @Value("${gender1}")
    private String gender1;

    @Value("${userId2}")
    private int userId2;
    @Value("${userName2}")
    private String userName2;
    @Value("${password2}")
    private String password2;
    @Value("${age2}")
    private int age2;
    @Value("${gender2}")
    private String gender2;


    @Autowired
    private UserRepository userRepository;

    //Method to be over-ridden for applicationListener
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent applicationReadyEvent) {
        seedData();
    }

    private void seedData() {
        userRepository.save(new User(userId1,userName1,password1,age1,gender1));
        userRepository.save(new User(userId2,userName2,password2,age2,gender2));
    }

}


