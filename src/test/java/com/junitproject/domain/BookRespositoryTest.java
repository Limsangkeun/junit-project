package com.junitproject.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class BookRespositoryTest {

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

    // 5. 책 삭제하기
}
