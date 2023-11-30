package com.svalero.taesmotors.controller;

import com.svalero.taesmotors.domain.Customer;
import com.svalero.taesmotors.exception.CustomerNotFoundException;
import com.svalero.taesmotors.service.CustomerService;
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
public class CustomerController {

    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getCustomers(@RequestParam Map<String, String> data) {
        logger.info("GET Customers");

        if (data.isEmpty()) {
            logger.info("END GET Customers");
            return ResponseEntity.ok(customerService.findAll());
        } else if (data.containsKey("name")) {
            List<Customer> customers = customerService.findByName(data.get("name"));
            logger.info("END GET Customers");
            return ResponseEntity.ok(customers);
        } else if (data.containsKey("lastName")) {
            List<Customer> customers = customerService.findByLastName(data.get("lastName"));
            logger.info("END GET Customers");
            return ResponseEntity.ok(customers);
        } else {
            logger.error("BAD REQUEST");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/customers/{customerId}")
    public ResponseEntity<Customer> getCustomer(@PathVariable long customerId) throws CustomerNotFoundException, NumberFormatException{
        logger.info("GET Customer");
        Customer customer = customerService.findById(customerId);
        logger.info("END GET Customer");
        return ResponseEntity.ok(customer);
    }

    @PostMapping("/customers")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        Customer newCustomer = customerService.addCustomer(customer);
        return new ResponseEntity<>(newCustomer,HttpStatus.CREATED);
    }

    @PutMapping("/customers/{customerId}")
    public ResponseEntity<Customer> modifyCustomer(@PathVariable long customerId, @Valid @RequestBody Customer customer) throws CustomerNotFoundException {
        logger.error("PUT Customer");
        Customer newCustomer = customerService.modifyCustomer(customerId, customer);
        logger.error("END PUT Customer");
        return ResponseEntity.status(HttpStatus.OK).body(newCustomer);
    }

    @PatchMapping("/customers/{customerId}")
    public ResponseEntity<Customer> patchCustomer(@PathVariable long customerId, @RequestBody Map<String, Object> updates) throws CustomerNotFoundException {
        logger.error("PATCH Customer");

        Customer updatedCustomer = customerService.patchCustomer(customerId, updates);

        logger.error("END PATCH Customer");
        return ResponseEntity.status(HttpStatus.OK).body(updatedCustomer);
    }

    @DeleteMapping(value = "/customers/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable long customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.noContent().build();
    }
}
