package com.strajja.database4.mappers;

import org.springframework.stereotype.Component;

@Component
public interface Mapper<A,B> {

    B mapTo(A a);

    A mapFrom(B b);
}
