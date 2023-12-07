package com.svalero.taesmotors.service;

import com.svalero.taesmotors.domain.*;
import com.svalero.taesmotors.exception.CarNotFoundException;
import com.svalero.taesmotors.exception.CustomerNotFoundException;
import com.svalero.taesmotors.exception.ExtraNotFoundException;
import com.svalero.taesmotors.exception.OrderNotFoundException;
import com.svalero.taesmotors.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ExtraRepository extraRepository;
    @Autowired
    private EmployeeRepository employeeRepository;


    private final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);


    @Override
    public Order addOrder(Order order) throws CarNotFoundException, CustomerNotFoundException, ExtraNotFoundException {
        logger.info("START POST Order: " + order);

        // Recupera las entidades usando los IDs
        Car car = carRepository.findById(order.getCar().getCarId()).orElseThrow(CarNotFoundException::new);
        Customer customer = customerRepository.findById(order.getCustomer().getCustomerId()).orElseThrow(CustomerNotFoundException::new);
        Employee employee = employeeRepository.findById(order.getEmployee().getEmployeeId()).orElseThrow(ExtraNotFoundException::new);
        Extra extra = extraRepository.findById(order.getExtra().getExtraId()).orElseThrow(ExtraNotFoundException::new);

        order.setCar(car);
        order.setCustomer(customer);
        order.setEmployee(employee);
        order.setExtra(extra);

        // Asegúrate de establecer otros campos necesarios para Order
        double totalPrice = car.getBasePrice() + extra.getPrice();
        order.setTotalPrice(totalPrice);
        order.setPaid(false);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> findByCustomer(String name) {
        return orderRepository.findByCustomer(name);
    }
    @Override
    public List<Order> findByEmployee(String name) {
        return orderRepository.findByEmployee(name);
    }

    @Override
    public List<Order> findByCar(String brand) {
        return orderRepository.findByCar(brand);
    }

    @Override
    public List<Order> findByExtra(String name) {
        return orderRepository.findByExtra(name);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public void deleteOrder(Long orderId) throws OrderNotFoundException{
        Order order = orderRepository.findById(orderId)
                .orElseThrow(OrderNotFoundException::new);
        logger.info("Order to delete: " + orderId);
        orderRepository.delete(order);
    }

    @Override
    public Order modifyOrder(Long orderId, Order newOrder) throws OrderNotFoundException {
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(OrderNotFoundException::new);
        logger.info("Order to modify: " + existingOrder);



        logger.info("Order modified: " + newOrder);
        return orderRepository.save(existingOrder);
    }

    @Override
    public Order findById(Long orderId) throws OrderNotFoundException {
        return orderRepository.findById(orderId)
                .orElseThrow(OrderNotFoundException::new);
    }

    public Order patchOrder(Long orderId, Map<String, Object> updates) throws OrderNotFoundException {
        Order existingOrder = findById(orderId);

        if (updates.containsKey("paid")) {
            existingOrder.setPaid((Boolean) updates.get("paid"));
        }
        return modifyOrder(orderId, existingOrder);
    }
}
