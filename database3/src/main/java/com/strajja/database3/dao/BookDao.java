package com.strajja.database3.dao;


import com.strajja.database3.domain.Book;

import java.util.Optional;

public interface BookDao {

    Optional<Book> find(String isbn);
}
