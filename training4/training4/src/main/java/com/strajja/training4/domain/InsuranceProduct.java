package com.strajja.training4.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InsuranceProduct {
    private Long id;
    private String name;
    private String type;
    private String description;
}
