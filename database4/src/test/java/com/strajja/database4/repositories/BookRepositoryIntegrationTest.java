package com.strajja.database4.repositories;


import com.strajja.database4.TestDataUtil;
import com.strajja.database4.domain.entities.AuthorEntity;
import com.strajja.database4.domain.entities.BookEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookRepositoryIntegrationTest {

    private final BookRepository underTest;


    @Autowired
    public BookRepositoryIntegrationTest(BookRepository underTest) {

        this.underTest = underTest;
    }


    @Test
    public void testThatBookCanBeCreatedAndRecalled(){

        AuthorEntity author = TestDataUtil.createTestAuthor();
        BookEntity book = TestDataUtil.createTestBook(author);
        underTest.save(book);
        Optional<BookEntity> result=underTest.findById(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get())
                .usingRecursiveComparison()
                .ignoringFields("author.id")
                .isEqualTo(book);

    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled(){
        AuthorEntity author = TestDataUtil.createTestAuthor();

        BookEntity bookA = TestDataUtil.createTestBookA(author);
        bookA=underTest.save(bookA);

        BookEntity bookB = TestDataUtil.createTestBookB(bookA.getAuthor());
        bookB= underTest.save(bookB);

        BookEntity bookC = TestDataUtil.createTestBookC(bookA.getAuthor());
        bookC=underTest.save(bookC);

        Iterable<BookEntity> result=underTest.findAll();
        assertThat(result)
                .hasSize(3)
                .containsExactly(bookA, bookB, bookC);
    }

    @Test
    public void testThatBookCanBeUpdated(){
        AuthorEntity author = TestDataUtil.createTestAuthor();

        BookEntity bookA = TestDataUtil.createTestBookA(author);
        underTest.save(bookA);

        bookA.setTitle("Updated Book");
        bookA=underTest.save(bookA);

        Optional<BookEntity> result=underTest.findById(bookA.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookA);


    }

    @Test
    public void testThatBookCanBeDeleted(){
        AuthorEntity author = TestDataUtil.createTestAuthor();
        BookEntity bookA = TestDataUtil.createTestBookA(author);
        underTest.save(bookA);
        underTest.deleteById(bookA.getIsbn());

        Optional<BookEntity> result=underTest.findById(bookA.getIsbn());
        assertThat(result).isEmpty();
    }
}
