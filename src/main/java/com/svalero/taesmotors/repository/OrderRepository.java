package com.svalero.taesmotors.repository;

import com.svalero.taesmotors.domain.Car;
import com.svalero.taesmotors.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    List<Order> findAll();
}
