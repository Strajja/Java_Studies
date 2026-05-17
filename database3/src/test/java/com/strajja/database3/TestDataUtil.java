package com.strajja.database3;

import com.strajja.database3.domain.Author;
import com.strajja.database3.domain.Book;

public final class TestDataUtil {
    private TestDataUtil(){}


    public static Author createTestAuthor() {
        return Author.builder()
                .id(5l)
                .name("Strajja")
                .age(22)
                .build();
    }

    public static Book createTestBook() {
        return Book.builder()
                .isbn("1235-1235-1235")
                .title("Nice")
                .authorId(5l)
                .build();
    }
}
