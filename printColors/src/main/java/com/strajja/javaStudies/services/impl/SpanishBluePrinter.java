package com.strajja.javaStudies.services.impl;

import com.strajja.javaStudies.services.BluePrinter;
import org.springframework.stereotype.Component;

@Component
public class SpanishBluePrinter implements BluePrinter {
    @Override
    public String print() {
        return "azul";
    }
}
