package com.strajja.training2.impl;

import com.strajja.training2.animals.Dog;
import org.springframework.stereotype.Component;

@Component
public class DogImplLouder implements Dog {

    @Override
    public String bark() {
        return "Bark!";
    }
}
