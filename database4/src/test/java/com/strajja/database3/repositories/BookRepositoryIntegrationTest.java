package com.strajja.database3.repositories;


import com.strajja.database3.TestDataUtil;
import com.strajja.database3.domain.Author;
import com.strajja.database3.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
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

        Author author = TestDataUtil.createTestAuthor();
        Book book = TestDataUtil.createTestBook(author);
        underTest.save(book);
        Optional<Book> result=underTest.findById(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get())
                .usingRecursiveComparison()
                .ignoringFields("author.id")
                .isEqualTo(book);

    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.createTestAuthor();

        Book bookA = TestDataUtil.createTestBookA(author);
        bookA=underTest.save(bookA);

        Book bookB = TestDataUtil.createTestBookB(bookA.getAuthor());
        bookB= underTest.save(bookB);

        Book bookC = TestDataUtil.createTestBookC(bookA.getAuthor());
        bookC=underTest.save(bookC);

        Iterable<Book> result=underTest.findAll();
        assertThat(result)
                .hasSize(3)
                .containsExactly(bookA, bookB, bookC);
    }

    @Test
    public void testThatBookCanBeUpdated(){
        Author author = TestDataUtil.createTestAuthor();

        Book bookA = TestDataUtil.createTestBookA(author);
        underTest.save(bookA);

        bookA.setTitle("Updated Book");
        bookA=underTest.save(bookA);

        Optional<Book> result=underTest.findById(bookA.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookA);


    }

    @Test
    public void testThatBookCanBeDeleted(){
        Author author = TestDataUtil.createTestAuthor();
        Book bookA = TestDataUtil.createTestBookA(author);
        underTest.save(bookA);
        underTest.deleteById(bookA.getIsbn());

        Optional<Book> result=underTest.findById(bookA.getIsbn());
        assertThat(result).isEmpty();
    }
}
