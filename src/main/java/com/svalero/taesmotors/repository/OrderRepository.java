package com.svalero.taesmotors.repository;

import com.svalero.taesmotors.domain.Car;
import com.svalero.taesmotors.domain.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findAll();
}
