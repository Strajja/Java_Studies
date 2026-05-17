package com.strajja.training2.impl;

import com.strajja.training2.animals.Parrot;
import org.springframework.stereotype.Component;

public class ParrotImpl implements Parrot {

    @Override
    public String caw(){
        return "caw";
    }
}
