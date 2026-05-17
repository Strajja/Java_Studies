package com.strajja.javaStudies.services.impl;

import com.strajja.javaStudies.services.BluePrinter;
import org.springframework.stereotype.Component;


public class EnglishBluePrinter implements BluePrinter {

    @Override
    public String print() {
        return "blue";
    }
}
