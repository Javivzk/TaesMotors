package com.svalero.taesmotors.service;

import com.svalero.taesmotors.domain.Car;
import com.svalero.taesmotors.domain.Order;
import com.svalero.taesmotors.exception.CarNotFoundException;
import com.svalero.taesmotors.exception.OrderNotFoundException;

import java.util.List;

public interface OrderService {

    List<Order> findAll();

    Order addOrder(Order order);
    void deleteOrder(String orderId);
    Order findById(String orderId) throws OrderNotFoundException;

    Order modifyOrder(String orderId, Order newOrder) throws OrderNotFoundException;
}
