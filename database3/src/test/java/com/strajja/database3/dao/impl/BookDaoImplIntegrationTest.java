package com.strajja.database3.dao.impl;


import com.strajja.database3.TestDataUtil;
import com.strajja.database3.dao.BookDao;
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
public class BookDaoImplIntegrationTest {

    private BookDaoImpl underTest;

    private AuthorDaoImpl authorDao;

    @Autowired
    public BookDaoImplIntegrationTest(BookDaoImpl underTest, AuthorDaoImpl authorDao) {
        this.underTest = underTest;
        this.authorDao = authorDao;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.createTestAuthor();
        authorDao.create(author);

        Book book = TestDataUtil.createTestBook();
        book.setAuthorId(author.getId());
        underTest.create(book);
        Optional<Book> result=underTest.findOne(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);

    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.createTestAuthor();
        authorDao.create(author);

        Book bookA = TestDataUtil.createTestBookA();
        underTest.create(bookA);
        bookA.setAuthorId(author.getId());
        Book bookB = TestDataUtil.createTestBookB();
        underTest.create(bookB);
        bookB.setAuthorId(author.getId());
        Book bookC = TestDataUtil.createTestBookC();
        underTest.create(bookC);
        bookC.setAuthorId(author.getId());
        List<Book> result=underTest.find();
        assertThat(result)
                .hasSize(3)
                .containsExactly(bookA, bookB, bookC);
    }

}
