package com.junitproject.domain;

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
        System.out.println("책 등록 테스트 실행!");
    }

    // 2. 책 목록 보기

    // 3. 책 한권 보기

    // 4. 책 수정하기

    // 5. 책 삭제하기
}
