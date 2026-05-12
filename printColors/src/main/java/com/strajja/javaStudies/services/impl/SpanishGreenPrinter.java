package com.strajja.javaStudies.services.impl;

import com.strajja.javaStudies.services.GreenPrinter;
import org.springframework.format.Printer;

public class SpanishGreenPrinter implements GreenPrinter {
    @Override
    public String print() {
        return "verde";
    }
}
