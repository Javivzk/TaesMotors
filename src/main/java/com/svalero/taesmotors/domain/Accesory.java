package com.svalero.taesmotors.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accesories")
public class Accesory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accesoryId;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private String price;

    @OneToMany(mappedBy = "accesory")
    private List<Option> options;
}
