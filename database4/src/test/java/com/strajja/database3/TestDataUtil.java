package com.strajja.database3;

import com.strajja.database3.domain.Author;
import com.strajja.database3.domain.Book;

public final class TestDataUtil {
    private TestDataUtil(){}


    public static Author createTestAuthor() {
        return Author.builder()
                .name("Strajja")
                .age(22)
                .build();
    }

    public static Author createTestAuthorA() {
        return Author.builder()
                .name("Strajja")
                .age(22)
                .build();
    }

    public static Author createTestAuthorB() {
        return Author.builder()
                .name("Mica")
                .age(26)
                .build();
    }

    public static Author createTestAuthorC() {
        return Author.builder()
                .name("Tesla")
                .age(79)
                .build();
    }

    public static Book createTestBook(final Author author) {
        return Book.builder()
                .isbn("1235-1235-1235")
                .title("Nice")
                .author(author)
                .build();
    }
    public static Book createTestBookA(final Author author) {
        return Book.builder()
                .isbn("1235-1235-1235")
                .title("Nice")
                .author(author)
                .build();
    }
    public static Book createTestBookB(final Author author) {
        return Book.builder()
                .isbn("345-345-345")
                .title("Cool")
                .author(author)
                .build();
    }
    public static Book createTestBookC(final Author author) {
        return Book.builder()
                .isbn("678-678-678")
                .title("Good")
                .author(author)
                .build();
    }
}
