package com.strajja.database3;

import com.strajja.database3.domain.dto.AuthorDto;
import com.strajja.database3.domain.dto.BookDto;
import com.strajja.database3.domain.entities.AuthorEntity;
import com.strajja.database3.domain.entities.BookEntity;

public final class TestDataUtil {
    private TestDataUtil(){}


    public static AuthorEntity createTestAuthor() {
        return AuthorEntity.builder()
                .name("Strajja")
                .age(22)
                .build();
    }

    public static AuthorEntity createTestAuthorA() {
        return AuthorEntity.builder()
                .name("Strajja")
                .age(22)
                .build();
    }

    public static AuthorEntity createTestAuthorB() {
        return AuthorEntity.builder()
                .name("Mica")
                .age(26)
                .build();
    }

    public static AuthorEntity createTestAuthorC() {
        return AuthorEntity.builder()
                .name("Tesla")
                .age(79)
                .build();
    }

    public static BookEntity createTestBook(final AuthorEntity author) {
        return BookEntity.builder()
                .isbn("1235-1235-1235")
                .title("Nice")
                .author(author)
                .build();
    }
    public static BookEntity createTestBookA(final AuthorEntity author) {
        return BookEntity.builder()
                .isbn("1235-1235-1235")
                .title("Nice")
                .author(author)
                .build();
    }
    public static BookEntity createTestBookB(final AuthorEntity author) {
        return BookEntity.builder()
                .isbn("345-345-345")
                .title("Cool")
                .author(author)
                .build();
    }
    public static BookEntity createTestBookC(final AuthorEntity author) {
        return BookEntity.builder()
                .isbn("678-678-678")
                .title("Good")
                .author(author)
                .build();
    }

    public static BookDto createTestBookDto(final AuthorDto author) {
        return BookDto.builder()
                .isbn("678-678-678")
                .title("Good")
                .author(author)
                .build();
    }

    public static BookEntity createTestBookEntity(final AuthorEntity author) {
        return BookEntity.builder()
                .isbn("890-890-890")
                .title("Bad")
                .author(author)
                .build();
    }
}
