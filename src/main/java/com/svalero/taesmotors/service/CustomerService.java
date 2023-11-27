package com.svalero.taesmotors.service;

import com.svalero.taesmotors.domain.Customer;
import com.svalero.taesmotors.exception.CustomerNotFoundException;


import java.util.List;
import java.util.Map;

public interface CustomerService {

    List<Customer> findAll();

    Customer addCustomer(Customer customer);
    void deleteCustomer(long customerId);
    Customer findById(long customerId) throws CustomerNotFoundException;
    Customer patchCustomer(long customerId, Map<String, Object> updates) throws CustomerNotFoundException;
    Customer modifyCustomer(long customerId, Customer newCustomer) throws CustomerNotFoundException;
}
