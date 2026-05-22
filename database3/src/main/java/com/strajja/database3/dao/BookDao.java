package com.strajja.database3.dao;


import com.strajja.database3.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    Optional<Book> findOne(String isbn);


    List<Book> find();
}
