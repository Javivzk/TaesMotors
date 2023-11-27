package com.svalero.taesmotors.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private Long customerId;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String postalCode;
    private String city;
}
