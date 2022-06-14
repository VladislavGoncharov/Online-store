package com.vladgoncharov.eshop.service.bucketAndOrdersService;

import com.vladgoncharov.eshop.Entity.*;
import com.vladgoncharov.eshop.dao.OrderRepository;
import com.vladgoncharov.eshop.dto.BucketDTO;
import com.vladgoncharov.eshop.dto.BucketDetailDTO;
import com.vladgoncharov.eshop.dto.OrderDTO;
import com.vladgoncharov.eshop.mapper.OrderMapper;
import com.vladgoncharov.eshop.service.productAndCategoriesService.ProductService;
import com.vladgoncharov.eshop.service.userService.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper = OrderMapper.MAPPER;

    private final OrderRepository orderRepository;
    private final BucketService bucketService;
    private final UserService userService;
    private final ProductService productService;

    public OrderServiceImpl(OrderRepository orderRepository, BucketService bucketService, UserService userService, ProductService productService) {
        this.orderRepository = orderRepository;
        this.bucketService = bucketService;
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public List<OrderDTO> findOrdersByUser(String username) {
        User user = userService.findFirstByUsername(username);
        try {
            List<Order> orders = orderRepository.findAllByUser(user.getBucket().getUser());
            List<OrderDTO> orderDTOS = orderMapper.fromOrderList(orders);
            return OrderDTO.sortedForCreatedDate(orderDTOS);
        } catch (NullPointerException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<OrderDTO> getAll() {
        List<OrderDTO> orderDTOS = orderMapper.fromOrderList(orderRepository.findAll());
        return OrderDTO.sortedForCreatedDate(orderDTOS);
    }

    @Override
    public void save(String username,String address) {

        BucketDTO bucket = bucketService.getBucketByUser(username);
        Order order = new Order();
        List<OrderDetails> orderDetails = toOrderDetails(bucket.getBucketDetails(), order);

        order.setUser(userService.findFirstByUsername(username));
        order.setAddress(address);
        order.setDetails(orderDetails);
        order.setSum(bucket.getSum());
        order.setStatus(OrderStatus.NEW);

        orderRepository.save(order);
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public Order findOrder(Long id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public void setStatusOrder(Long id, OrderStatus status) {
        Order order = orderRepository.findById(id).get();
        order.setStatus(status);
        orderRepository.save(order);
    }


    private List<OrderDetails> toOrderDetails(List<BucketDetailDTO> bucketDetailDTOS, Order order) {
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        for (BucketDetailDTO bucketDetailDTO : bucketDetailDTOS) {
            OrderDetails orderDetails = OrderDetails.builder()
                    .order(order)
                    .price(bucketDetailDTO.getPrice())
                    .product(productService.findFirstById(bucketDetailDTO.getProductId()))
                    .amount(bucketDetailDTO.getAmount())
                    .build();

            orderDetailsList.add(orderDetails);
        }
        return orderDetailsList;
    }
}
