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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookRepositoryIntegrationTest {

    private BookRepository underTest;

//    private AuthorDaoImpl authorDao;

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
        assertThat(result.get()).isEqualTo(book);

    }

//    @Test
//    public void testThatMultipleBooksCanBeCreatedAndRecalled(){
//        Author author = TestDataUtil.createTestAuthor();
//        authorDao.create(author);
//
//        Book bookA = TestDataUtil.createTestBookA();
//        underTest.create(bookA);
//        bookA.setAuthorId(author.getId());
//        Book bookB = TestDataUtil.createTestBookB();
//        underTest.create(bookB);
//        bookB.setAuthorId(author.getId());
//        Book bookC = TestDataUtil.createTestBookC();
//        underTest.create(bookC);
//        bookC.setAuthorId(author.getId());
//        List<Book> result=underTest.find();
//        assertThat(result)
//                .hasSize(3)
//                .containsExactly(bookA, bookB, bookC);
//    }

}
