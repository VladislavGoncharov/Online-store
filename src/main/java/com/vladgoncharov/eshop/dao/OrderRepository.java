package com.vladgoncharov.eshop.dao;

import com.vladgoncharov.eshop.Entity.Order;
import com.vladgoncharov.eshop.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUser(User user) throws NullPointerException;
}