package com.strajja.database3.repositories;

import com.strajja.database3.domain.Author;
import com.strajja.database3.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book,String> {
}
