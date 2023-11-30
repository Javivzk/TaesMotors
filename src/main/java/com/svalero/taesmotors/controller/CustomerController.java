package com.svalero.taesmotors.controller;

import com.svalero.taesmotors.domain.Customer;
import com.svalero.taesmotors.exception.CarNotFoundException;
import com.svalero.taesmotors.exception.CustomerNotFoundException;
import com.svalero.taesmotors.exception.ErrorMessage;
import com.svalero.taesmotors.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
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
    public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer) {
        logger.info("ADD Customer");
        Customer newCustomer = customerService.addCustomer(customer);
        logger.info("END ADD Customer");
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
    public ResponseEntity<?> deleteCustomer(@PathVariable long customerId) throws CustomerNotFoundException {
        logger.info("DELETE Customer");
        customerService.deleteCustomer(customerId);
        logger.info("END DELETE Customer");
        return ResponseEntity.noContent().build();
    }
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<?> handleCustomerNotFoundException(CustomerNotFoundException cnfe){
        logger.error("Customer not found");
        ErrorMessage errorMessage = new ErrorMessage(404, cnfe.getMessage());
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
