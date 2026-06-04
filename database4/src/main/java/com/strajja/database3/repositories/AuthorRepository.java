package com.strajja.database3.repositories;

import com.strajja.database3.domain.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    Iterable<Author> ageLessThan(int age);

    @Query("SELECT a from Author a where a.age >?1")
    Iterable<Author> findAuthorsWithAgeGreaterThan(int age);
}
