package com.strajja.database3;

import com.strajja.database3.domain.Author;
import com.strajja.database3.domain.Book;

public final class TestDataUtil {
    private TestDataUtil(){}


    public static Author createTestAuthor() {
        return Author.builder()
                .id(5L)
                .name("Strajja")
                .age(22)
                .build();
    }

    public static Author createTestAuthorA() {
        return Author.builder()
                .id(5l)
                .name("Strajja")
                .age(22)
                .build();
    }

    public static Author createTestAuthorB() {
        return Author.builder()
                .id(6l)
                .name("Mica")
                .age(26)
                .build();
    }

    public static Author createTestAuthorC() {
        return Author.builder()
                .id(7l)
                .name("Tesla")
                .age(79)
                .build();
    }

    public static Book createTestBook() {
        return Book.builder()
                .isbn("1235-1235-1235")
                .title("Nice")
                .authorId(5l)
                .build();
    }
    public static Book createTestBookA() {
        return Book.builder()
                .isbn("1235-1235-1235")
                .title("Nice")
                .authorId(5l)
                .build();
    }
    public static Book createTestBookB() {
        return Book.builder()
                .isbn("345-345-345")
                .title("Cool")
                .authorId(6l)
                .build();
    }
    public static Book createTestBookC() {
        return Book.builder()
                .isbn("678-678-678")
                .title("Good")
                .authorId(7l)
                .build();
    }
}
