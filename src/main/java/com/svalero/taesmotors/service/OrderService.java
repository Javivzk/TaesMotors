package com.svalero.taesmotors.service;

import com.svalero.taesmotors.domain.*;
import com.svalero.taesmotors.domain.dto.OrderDTO;
import com.svalero.taesmotors.exception.*;

import java.util.List;
import java.util.Map;

public interface OrderService {

    List<Order> findAll();

    void deleteOrder(Long orderId) throws OrderNotFoundException;
    Order patchOrder(Long orderId, Map<String, Object> updates) throws OrderNotFoundException;
    Order modifyOrder(Long orderId, Order newOrder) throws OrderNotFoundException;

    Order findById(Long orderId) throws OrderNotFoundException;
    Order addOrder(Order order) throws CarNotFoundException, CustomerNotFoundException, ExtraNotFoundException;
    List<Order> findByCustomer(String name);

    List<Order> findByCar(String brand);

    List<Order> findByExtra(String name);



}
