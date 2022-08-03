package com.vladgoncharov.eshop.configuration;

import com.vladgoncharov.eshop.dto.UserDTO;
import com.vladgoncharov.eshop.entity.User;
import com.vladgoncharov.eshop.service.userService.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class TestConfiguration {

    @Bean
    public UserDTO userDTO(){
        return mock(UserDTO.class);
    }

    @Bean
    public User user(){
        return mock(User.class);
    }

    @Bean
    public UserService userService(){
        return mock(UserService.class);
    }


}
