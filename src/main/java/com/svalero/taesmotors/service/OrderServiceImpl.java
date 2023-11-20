package com.svalero.taesmotors.service;

import com.svalero.taesmotors.domain.Client;
import com.svalero.taesmotors.domain.Employee;
import com.svalero.taesmotors.domain.Order;
import com.svalero.taesmotors.exception.ClientNotFoundException;
import com.svalero.taesmotors.exception.EmployeeNotFoundException;
import com.svalero.taesmotors.exception.OrderNotFoundException;
import com.svalero.taesmotors.repository.ClientRepository;
import com.svalero.taesmotors.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    public void deleteOrder(long orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public Order findById(long orderId) throws OrderNotFoundException {
        logger.info("Order id: " + orderId);
        return orderRepository.findById(orderId)
                .orElseThrow(OrderNotFoundException::new);
    }

    @Override
    public Order modifyOrder(long orderId, Order newOrder) throws OrderNotFoundException {
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(OrderNotFoundException::new);
        logger.info("Order to modify: " + existingOrder);
        existingOrder.setClientId(newOrder.getClientId());
        existingOrder.setCarId(newOrder.getCarId());
        existingOrder.setEmployeeId(newOrder.getEmployeeId());
        existingOrder.setPrice(newOrder.getPrice());
        existingOrder.setPaid(newOrder.isPaid());


        logger.info("Order modified: " + newOrder);
        return orderRepository.save(existingOrder);
    }

    public Order patchOrder(long orderId, Map<String, Object> updates) throws OrderNotFoundException {
        Order existingOrder = findById(orderId);

        if (updates.containsKey("clientId")) {
            existingOrder.setClientId((long) updates.get("clientId"));
        }
        if (updates.containsKey("carId")) {
            existingOrder.setCarId((long) updates.get("carId"));
        }
        if (updates.containsKey("employeeId")) {
            existingOrder.setEmployeeId((long) updates.get("employeeId"));
        }
        if (updates.containsKey("price")) {
            existingOrder.setPrice((double) updates.get("price"));
        }
        if (updates.containsKey("paid")) {
            existingOrder.setPaid((boolean) updates.get("paid"));
        }
        return modifyOrder(orderId, existingOrder);
    }
}
