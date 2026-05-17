package com.strajja.training2.impl;

import com.strajja.training2.animals.Cat;
import org.springframework.stereotype.Component;

public class CatImpl implements Cat {

    @Override
    public String meow(){
        return "meow";
    }
}
