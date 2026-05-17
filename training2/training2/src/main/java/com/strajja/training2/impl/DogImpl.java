package com.strajja.training2.impl;

import com.strajja.training2.animals.Dog;
import org.springframework.stereotype.Component;


public class DogImpl implements Dog {

    @Override
    public String bark(){
        return "bark";
    }
}
