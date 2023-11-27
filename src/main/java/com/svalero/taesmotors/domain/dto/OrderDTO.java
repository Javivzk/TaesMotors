package com.svalero.taesmotors.domain.dto;

import com.svalero.taesmotors.domain.Car;
import com.svalero.taesmotors.domain.Customer;
import com.svalero.taesmotors.domain.Extra;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {


    private Long orderId;
    private Car car;
    private Customer customer;
    private List<Extra> extras;
    private double totalPrice;

}
