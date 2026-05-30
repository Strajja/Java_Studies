package com.strajja.training6.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

    private Long id;
    private String brand;
    private String model;

}
