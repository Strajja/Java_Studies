package com.strajja.training2.impl;

import com.strajja.training2.animals.Dog;
import com.strajja.training2.animals.Parrot;
import org.springframework.stereotype.Component;

@Component
public class ParrotImplLouder implements Parrot {

    @Override
    public String caw(){
        return "Caw!";
    }
}
