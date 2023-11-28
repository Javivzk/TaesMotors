package com.svalero.taesmotors.controller;

import com.svalero.taesmotors.domain.*;
import com.svalero.taesmotors.domain.dto.CarDTO;
import com.svalero.taesmotors.domain.dto.CustomerDTO;
import com.svalero.taesmotors.domain.dto.OrderDTO;
import com.svalero.taesmotors.exception.*;
import com.svalero.taesmotors.repository.CarRepository;
import com.svalero.taesmotors.repository.CustomerRepository;
import com.svalero.taesmotors.repository.ExtraRepository;
import com.svalero.taesmotors.service.CarService;
import com.svalero.taesmotors.service.CustomerService;
import com.svalero.taesmotors.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class OrderController {


    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> orders = orderService.findAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable long orderId) throws OrderNotFoundException, NumberFormatException{
        logger.info("GET ORDER");
        Order order = orderService.findById(orderId);
        logger.info("END GET ORDER");
        return ResponseEntity.ok(order);
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> addOrder(@Valid @RequestBody Order order) throws CarNotFoundException, CustomerNotFoundException, ExtraNotFoundException {
        logger.info("POST ORDER");
        Order newOrder = orderService.addOrder(order);
        logger.info("END POST ORDER");
        return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
    }
    @PutMapping("/orders/{orderId}")
    public ResponseEntity<Order> modifyOrder(@PathVariable long orderId, @Valid @RequestBody Order order) throws OrderNotFoundException {
        logger.error("PUT ORDER");
        Order newOrder = orderService.modifyOrder(orderId,order);
        logger.error("END PUT ORDER");
        return ResponseEntity.status(HttpStatus.OK).body(newOrder);
    }

    @PatchMapping("/orders/{orderId}")
    public ResponseEntity<Order> patchOrder(@PathVariable long orderId, @RequestBody Map<String, Object> updates) throws OrderNotFoundException {
        logger.error("PATCH ORDER");

        Order updatedOrder = orderService.patchOrder(orderId, updates);

        logger.error("END PATCH ORDER");
        return ResponseEntity.status(HttpStatus.OK).body(updatedOrder);
    }

    @DeleteMapping(value = "/orders/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable long orderId) throws OrderNotFoundException {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}
