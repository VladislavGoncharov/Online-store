package com.vladgoncharov.eshop.controller;

import com.vladgoncharov.eshop.configuration.TestConfiguration;
import com.vladgoncharov.eshop.dto.ProductDTO;
import com.vladgoncharov.eshop.dto.UserDTO;
import com.vladgoncharov.eshop.entity.User;
import com.vladgoncharov.eshop.service.userService.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfiguration.class,
        loader = AnnotationConfigContextLoader.class)
class MainControllerTest {

    @Autowired
    private UserDTO userDTO;
    @Autowired
    private User user;
    @Autowired
    private UserService userService;

    @Test
    void index() {
        List<ProductDTO> productDTOS =
                List.of(new ProductDTO(), new ProductDTO(), new ProductDTO(), new ProductDTO(), new ProductDTO());
        List<ProductDTO> productRandom = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            productRandom.add(productDTOS.get(new Random().nextInt(productDTOS.size())));
        }

        assertEquals(4, productRandom.size());
    }

    @Test
    void saveUser() {
        when(userDTO.getPassword()).thenReturn("password");
        when(userDTO.getMatchingPassword()).thenReturn("password123");

        assertTrue(!Objects.equals(userDTO.getPassword(), userDTO.getMatchingPassword()));

        when(userDTO.getMatchingPassword()).thenReturn("password");

        assertFalse(!Objects.equals(userDTO.getPassword(), userDTO.getMatchingPassword()));
    }

    @Test
    void saveUser_checkingForExistingUser_whenSavingUser() {
        String username = "Nikolay";
        when(userService.findFirstByUsername(username)).thenReturn(user);

        assertTrue(userService.findFirstByUsername(username) != null);

        when(userService.findFirstByUsername(username)).thenReturn(null);

        assertFalse(userService.findFirstByUsername(username) != null);

    }

}