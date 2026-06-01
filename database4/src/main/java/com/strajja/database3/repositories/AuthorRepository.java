package com.strajja.database3.repositories;

import com.strajja.database3.domain.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
