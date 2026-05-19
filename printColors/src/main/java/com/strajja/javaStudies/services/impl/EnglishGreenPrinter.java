package com.strajja.javaStudies.services.impl;

import com.strajja.javaStudies.services.GreenPrinter;
import org.springframework.stereotype.Component;


public class EnglishGreenPrinter implements GreenPrinter {

    @Override
    public String print() {
        return "green";
    }
}
