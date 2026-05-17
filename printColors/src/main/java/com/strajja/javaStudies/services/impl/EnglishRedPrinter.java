package com.strajja.javaStudies.services.impl;

import com.strajja.javaStudies.services.RedPrinter;
import org.springframework.stereotype.Component;

public class EnglishRedPrinter implements RedPrinter {

    @Override
    public String print() {
        return "red";
    }
}
