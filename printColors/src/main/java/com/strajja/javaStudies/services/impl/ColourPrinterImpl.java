package com.strajja.javaStudies.services.impl;

import com.strajja.javaStudies.services.ColourPrinter;
import com.strajja.javaStudies.services.impl.ColourPrinterImpl;
import com.strajja.javaStudies.services.BluePrinter;
import com.strajja.javaStudies.services.GreenPrinter;
import com.strajja.javaStudies.services.RedPrinter;
import org.springframework.stereotype.Component;

@Component
public class ColourPrinterImpl implements ColourPrinter {

    private RedPrinter redPrinter;

    private BluePrinter bluePrinter;

    private GreenPrinter greenPrinter;

    public ColourPrinterImpl(RedPrinter redPrinter, BluePrinter bluePrinter, GreenPrinter greenPrinter) {
        this.redPrinter = redPrinter;
        this.bluePrinter = bluePrinter;
        this.greenPrinter = greenPrinter;
    }

    @Override
    public String print() {
        return String.join(",", redPrinter.print(),greenPrinter.print(),bluePrinter.print());
    }
}
