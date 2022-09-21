package com.junitproject.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class BookRespositoryTest {

    @Autowired
    private BookRepository repository;
    
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

    // 3. 책 한권 보기

    // 4. 책 수정하기

    // 5. 책 삭제하기
}
