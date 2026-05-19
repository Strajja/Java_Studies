package com.strajja.training3.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "burger")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Burger {
    private String meat;
    private String sauce;
    private String salad;
}
