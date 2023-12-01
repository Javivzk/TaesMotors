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

    List<Order> findByEmployee(String name);

    List<Order> findByCustomer(String name);

    List<Order> findByCar(String brand);

    List<Order> findByExtra(String name);
}
