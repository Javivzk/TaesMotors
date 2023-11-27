package com.svalero.taesmotors.repository;

import com.svalero.taesmotors.domain.Car;
import com.svalero.taesmotors.domain.Customer;
import com.svalero.taesmotors.domain.Extra;
import com.svalero.taesmotors.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findAll();

    List<Order> findByCustomer(Customer customer);

    List<Order> findByCar(Car car);

    List<Order> findByExtra(Extra extra);}
