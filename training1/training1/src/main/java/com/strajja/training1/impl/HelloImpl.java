package com.strajja.training1.impl;

import com.strajja.training1.interfaces.Hello;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Component
public class HelloImpl implements Hello {

    @Override
    public String sayHello() {
        return "Hello World!";
    }
}
