package com.svalero.taesmotors.service;

import com.svalero.taesmotors.domain.Customer;
import com.svalero.taesmotors.exception.CustomerNotFoundException;
import com.svalero.taesmotors.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    private final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(long customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public Customer findById(long customerId) throws CustomerNotFoundException {
        logger.info("Customer id: " + customerId);
        return customerRepository.findById(customerId)
                .orElseThrow(CustomerNotFoundException::new);
    }

    @Override
    public Customer modifyCustomer(long customerId, Customer newCustomer) throws CustomerNotFoundException {
        Customer existingCustomer = customerRepository.findById(customerId)
                .orElseThrow(CustomerNotFoundException::new);
        logger.info("Customer to modify: " + existingCustomer);
        existingCustomer.setName(newCustomer.getName());
        existingCustomer.setLastName(newCustomer.getLastName());
        existingCustomer.setEmail(newCustomer.getEmail());
        existingCustomer.setPhone(newCustomer.getPhone());
        existingCustomer.setAddress(newCustomer.getAddress());
        existingCustomer.setPostalCode(newCustomer.getPostalCode());
        existingCustomer.setCity(newCustomer.getCity());
        existingCustomer.setClubMember(newCustomer.getClubMember());


        logger.info("Customer modified: " + newCustomer);
        return customerRepository.save(existingCustomer);
    }

    @Override
    public List<Customer> findByName(String name) {
        return customerRepository.findByName(name);
    }

    @Override
    public List<Customer> findByLastName(String lastName) {
        return customerRepository.findByLastName(lastName);
    }

    public Customer patchCustomer(long customerId, Map<String, Object> updates) throws CustomerNotFoundException {
        Customer existingCustomer = findById(customerId);

        if (updates.containsKey("name")) {
            existingCustomer.setName((String) updates.get("name"));
        }
        if (updates.containsKey("lastName")) {
            existingCustomer.setLastName((String) updates.get("lastName"));
        }
        if (updates.containsKey("email")) {
            existingCustomer.setLastName((String) updates.get("email"));
        }
        if (updates.containsKey("phone")) {
            existingCustomer.setLastName((String) updates.get("phone"));
        }
        if (updates.containsKey("address")) {
            existingCustomer.setLastName((String) updates.get("address"));
        }
        if (updates.containsKey("postalCode")) {
            existingCustomer.setLastName((String) updates.get("postalCode"));
        }
        if (updates.containsKey("city")) {
            existingCustomer.setLastName((String) updates.get("city"));
        }
        if (updates.containsKey("clubMember")) {
            existingCustomer.setClubMember((Boolean) updates.get("clubMember"));
        }
        return modifyCustomer(customerId, existingCustomer);
    }
}
