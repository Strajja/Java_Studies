package com.strajja.database4.repositories;

import com.strajja.database4.domain.entities.AuthorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {
    Iterable<AuthorEntity> ageLessThan(int age);

    @Query("SELECT a from AuthorEntity a where a.age >?1")
    Iterable<AuthorEntity> findAuthorsWithAgeGreaterThan(int age);
}
