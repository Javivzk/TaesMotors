package com.svalero.taesmotors.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExtraDTO {
    private Long extraId;
    private String name;
    private String description;
    private Double price;
}
