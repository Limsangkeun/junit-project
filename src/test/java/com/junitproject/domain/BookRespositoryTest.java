package com.junitproject.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
public class BookRespositoryTest {

    private final Logger log = LoggerFactory.getLogger(BookRespositoryTest.class);

    private final String PRE_TITLE = "사전준비 타이틀";
    private final String PRE_AUTHOR = "임상근";


    @Autowired
    private BookRepository repository;

    @BeforeEach
    public void preset_data () {

        Book newBook = Book.builder()
                        .title(PRE_TITLE)
                        .author(PRE_AUTHOR)
                        .build();

        repository.save(newBook);
    }
    
    
    // 1. 책 등록
    @Test
    public void save_test() {
        // given (데이터 준비)
        String title = "테스트 타이틀";
        String author = "테스트 작가";

        Book newBook = Book.builder()
                        .title(title)
                        .author(author)
                        .build();

        // when (테스트 진행)
        Book bookPS = repository.save(newBook);
        System.out.println("저장된 책 정보 " + bookPS.toString());
        // then (검증)
        assertEquals(title, bookPS.getTitle());
        assertEquals(author, bookPS.getAuthor());
    }

    // 2. 책 목록 보기

    @Test
    public void findBookList() {
        List<Book> bookList = repository.findAll();

        assertEquals(1, bookList.size());
    }

    // 3. 책 한권 보기
    
    @Test
    public void findBookById() {
        // given
        
        // when 
        Book book = repository.findById(1L).get();

        // then
        assertEquals(PRE_TITLE, book.getTitle());
        assertEquals(PRE_AUTHOR, book.getAuthor());
    }

    // 4. 책 수정하기
    @Sql("classpath:db/tableInit.sql")
    @Test
    public void modifyBook() {
        // given
        Long id = 1L;
        String title = "변경 제목";
        String author = "변경상근";
        
        // when
        Book targetBook = repository.findById(id).get();
        targetBook.setTitle(title);
        targetBook.setAuthor(author);
        Book bookPS = repository.save(targetBook);

        // then
        assertTrue(Optional.of(targetBook).isPresent());
        assertEquals(id, bookPS.getId());
        assertEquals(title, bookPS.getTitle());
        assertEquals(author, bookPS.getAuthor());
    }

    // 5. 책 삭제하기
    @Sql("classpath:db/tableInit.sql")
    @Test
    public void deleteBookById() {
        // given
        Long targetId = 1L;
        // when
        repository.deleteById(targetId);
        // then
        assertFalse(repository.findById(targetId).isPresent());
    }
}
