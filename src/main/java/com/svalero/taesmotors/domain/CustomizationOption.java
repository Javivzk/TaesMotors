package com.svalero.taesmotors.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customization_option")
public class CustomizationOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customizationOptionId;

    @ManyToOne
    @JoinColumn(name = "customization_id")
    private Customization customization;

    @OneToOne
    @JoinColumn(name = "accesory_id")
    private Accesory accesory;

    @OneToOne
    @JoinColumn(name = "option_id")
    private Option option;
}
