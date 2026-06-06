package com.strajja.database3.services;

import com.strajja.database3.domain.entities.AuthorEntity;
import org.springframework.stereotype.Component;

@Component
public interface AuthorService {

    AuthorEntity createAuthor(AuthorEntity author);

}
