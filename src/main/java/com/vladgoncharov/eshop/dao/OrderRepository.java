package com.vladgoncharov.eshop.dao;

import com.vladgoncharov.eshop.entity.Order;
import com.vladgoncharov.eshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUser(User user) throws NullPointerException;
}