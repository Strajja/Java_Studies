package com.strajja.database4.repositories;

import com.strajja.database4.TestDataUtil;
import com.strajja.database4.domain.entities.AuthorEntity;
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
public class AuthorRepositoryIntegrationTest {

    private AuthorRepository underTest;

    @Autowired
    public AuthorRepositoryIntegrationTest(AuthorRepository underTest) {

        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled(){

        AuthorEntity author = TestDataUtil.createTestAuthor();
        underTest.save(author);
        Optional<AuthorEntity> result=underTest.findById(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);


    }

    @Test
    public void testMultipleAuthorsCanBeCreatedAndRecalled(){

        AuthorEntity authorA = TestDataUtil.createTestAuthorA();
        AuthorEntity authorB = TestDataUtil.createTestAuthorB();
        AuthorEntity authorC = TestDataUtil.createTestAuthorC();

        underTest.save(authorA);
        underTest.save(authorB);
        underTest.save(authorC);

        Iterable<AuthorEntity> result = underTest.findAll();
        assertThat(result)
                .hasSize(3)
                .containsExactly(authorA, authorB, authorC);
    }
    @Test
    public void testThatAuthorCanBeUpdated(){
        AuthorEntity author = TestDataUtil.createTestAuthor();
        underTest.save(author);
        author.setName("Updated Author");
        underTest.save(author);

        Optional<AuthorEntity> result=underTest.findById(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatAuthorCanBeDeleted(){
        AuthorEntity author = TestDataUtil.createTestAuthor();
        underTest.save(author);
        underTest.deleteById(author.getId());
        Optional<AuthorEntity> result=underTest.findById(author.getId());

        assertThat(result).isEmpty();
    }

    @Test
    public void testThatGetAuthorWithAgeLessThan(){
        AuthorEntity authorA = TestDataUtil.createTestAuthorA();
        AuthorEntity authorB = TestDataUtil.createTestAuthorB();
        AuthorEntity authorC = TestDataUtil.createTestAuthorC();

        underTest.save(authorA);
        underTest.save(authorB);
        underTest.save(authorC);

        Iterable<AuthorEntity> results=underTest.ageLessThan(50);

        assertThat(results).containsExactly(authorA, authorB);
    }

    @Test
    public void testThatGetAuthorWithAgeGreaterThan(){

        AuthorEntity authorA = TestDataUtil.createTestAuthorA();
        AuthorEntity authorB = TestDataUtil.createTestAuthorB();
        AuthorEntity authorC = TestDataUtil.createTestAuthorC();

        underTest.save(authorA);
        underTest.save(authorB);
        underTest.save(authorC);

        Iterable<AuthorEntity> results=underTest.findAuthorsWithAgeGreaterThan(50);

        assertThat(results).containsExactly(authorC);

    }
}
