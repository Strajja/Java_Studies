package com.strajja.javaStudies.services.impl;

import com.strajja.javaStudies.services.RedPrinter;
import org.springframework.format.Printer;
import org.springframework.stereotype.Component;

@Component
public class SpanishRedPrinter implements RedPrinter {

    @Override
    public String print() {
        return "roja";
    }
}
