package com.svalero.taesmotors.repository;

import com.svalero.taesmotors.domain.Car;
import com.svalero.taesmotors.domain.Extra;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExtraRepository extends JpaRepository<Extra, Long> {

    List<Extra> findAll();
    List<Extra> findByName(String name);
}
