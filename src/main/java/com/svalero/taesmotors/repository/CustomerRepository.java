package com.svalero.taesmotors.repository;

import com.svalero.taesmotors.domain.Car;
import com.svalero.taesmotors.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    List<Customer> findAll();

    List<Customer> findByName(String name);
    List<Customer> findByLastName(String lastName);



}
