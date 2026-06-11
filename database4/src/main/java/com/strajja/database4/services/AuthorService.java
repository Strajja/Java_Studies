package com.strajja.database4.services;

import com.strajja.database4.domain.dto.AuthorDto;
import com.strajja.database4.domain.entities.AuthorEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface AuthorService {

    AuthorEntity createAuthor(AuthorEntity author);

    List<AuthorEntity> findAll();

    Optional<AuthorEntity> findOne(Long id);

    boolean isExists(Long id);

    AuthorEntity partialUpdate(Long id, AuthorEntity authorEntity);

    void delete(Long id);
}
