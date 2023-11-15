package com.svalero.taesmotors.controller;

import com.svalero.taesmotors.domain.Car;
import com.svalero.taesmotors.domain.Order;
import com.svalero.taesmotors.exception.CarNotFoundException;
import com.svalero.taesmotors.exception.OrderNotFoundException;
import com.svalero.taesmotors.service.CarService;
import com.svalero.taesmotors.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class OrderController {

    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

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
    public ResponseEntity<Order> addOrder(@RequestBody @Valid Order order) {
        Order newOrder = orderService.addOrder(order);
        return new ResponseEntity<>(newOrder,HttpStatus.CREATED);
    }

    @PutMapping("/orders/{orderId}")
    public ResponseEntity<Order> modifyOrder(@PathVariable long orderId, @Valid @RequestBody Order order) throws OrderNotFoundException {
        logger.error("PUT ORDER");
        Order newOrder = orderService.modifyOrder(orderId,order);
        logger.error("END PUT ORDER");
        return ResponseEntity.status(HttpStatus.OK).body(newOrder);
    }

    @DeleteMapping(value = "/orders/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}
