package com.vladgoncharov.eshop.service.bucketAndOrdersService;

import com.vladgoncharov.eshop.Entity.Order;
import com.vladgoncharov.eshop.Entity.OrderStatus;
import com.vladgoncharov.eshop.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> findOrdersByUser(String username);

    List<OrderDTO> getAll();

    void save(String username, String address);

    void save(Order order);

    Order findOrder(Long id);

    void setStatusOrder(Long id, OrderStatus status);


}
