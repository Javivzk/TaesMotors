package com.svalero.taesmotors.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("clients")
public class Client {

    @Id
    private String clientId;
    private String name;
    private String lastName;
    private String phone;
    private String address;
    private String postalCode;
    private String city;
}
