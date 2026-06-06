package com.strajja.database3.services;

import com.strajja.database3.domain.entities.AuthorEntity;
import com.strajja.database3.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorEntity createAuthor(AuthorEntity authorEntity) {

        return authorRepository.save(authorEntity);

    }
}
