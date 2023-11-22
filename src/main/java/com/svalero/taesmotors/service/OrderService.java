package com.svalero.taesmotors.service;

import com.svalero.taesmotors.domain.Car;
import com.svalero.taesmotors.domain.Client;
import com.svalero.taesmotors.domain.Employee;
import com.svalero.taesmotors.domain.Order;
import com.svalero.taesmotors.exception.CarNotFoundException;
import com.svalero.taesmotors.exception.EmployeeNotFoundException;
import com.svalero.taesmotors.exception.OrderNotFoundException;

import java.util.List;
import java.util.Map;

public interface OrderService {


    List<Order> findAll();
    Order addOrder(Order order);
    void deleteOrder(long orderId);
    Order findById(long orderId) throws OrderNotFoundException;
    Order patchOrder(long orderId, Map<String, Object> updates) throws OrderNotFoundException;
    Order modifyOrder(long orderId, Order newOrder) throws OrderNotFoundException;
}
