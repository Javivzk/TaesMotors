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
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
    public ResponseEntity<List<Order>> getOrders(@RequestParam Map<String, String> data) {
        logger.info("GET Orders");

        if (data.isEmpty()) {
            logger.info("END GET Orders");
            return ResponseEntity.ok(orderService.findAll());
        } else if (data.containsKey("customer")) {
            List<Order> orders = orderService.findByCustomer(data.get("customer"));
            logger.info("END GET Orders");
            return ResponseEntity.ok(orders);
        } else if (data.containsKey("car")) {
            List<Order> orders = orderService.findByCar(data.get("car"));
            logger.info("END GET Orders");
            return ResponseEntity.ok(orders);
        } else if (data.containsKey("extra")) {
            List<Order> orders = orderService.findByExtra(data.get("extra"));
            logger.info("END GET Orders");
            return ResponseEntity.ok(orders);
        } else {
            logger.error("BAD REQUEST");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<?> handleExtraNotFoundException(OrderNotFoundException onfe){
        logger.error("Extra not found");
        ErrorMessage errorMessage = new ErrorMessage(404, onfe.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleBadRequestException(MethodArgumentNotValidException manve) {
        logger.error("Incorrect data");
        Map<String, String> errors = new HashMap<>();
        manve.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        ErrorMessage badRequestErrorMessage = new ErrorMessage(400, "Bad Request", errors);
        return new ResponseEntity<>(badRequestErrorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception e) {
        logger.error("Internal error " + e.getMessage());
        ErrorMessage errorMessage = new ErrorMessage(500, "Internal Server Error");
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
