package com.svalero.taesmotors.service;

import com.svalero.taesmotors.domain.Client;
import com.svalero.taesmotors.domain.Order;
import com.svalero.taesmotors.exception.ClientNotFoundException;
import com.svalero.taesmotors.exception.OrderNotFoundException;
import com.svalero.taesmotors.repository.ClientRepository;
import com.svalero.taesmotors.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    private final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(String orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public Order findById(String orderId) throws OrderNotFoundException {
        logger.info("Order id: " + orderId);
        return orderRepository.findById(orderId)
                .orElseThrow(OrderNotFoundException::new);
    }

    @Override
    public Order modifyOrder(String orderId, Order newOrder) throws OrderNotFoundException {
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(OrderNotFoundException::new);
        logger.info("Order to modify: " + existingOrder);
        existingOrder.setClient(newOrder.getClient());
        existingOrder.setCar(newOrder.getCar());
        existingOrder.setEmployee(newOrder.getEmployee());
        existingOrder.setPrice(newOrder.getPrice());
        existingOrder.setPaid(newOrder.isPaid());


        logger.info("Order modified: " + newOrder);
        return orderRepository.save(existingOrder);
    }
}
