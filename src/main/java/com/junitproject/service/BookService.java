package com.junitproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.junitproject.domain.Book;
import com.junitproject.domain.BookRepository;
import com.junitproject.web.dto.BookRespDto;
import com.junitproject.web.dto.BookSaveReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookService {

    @Autowired
    private final BookRepository bookRepository;
    
    // 1. 책 등록
    @Transactional(rollbackFor = RuntimeException.class)
    public BookRespDto saveBook(BookSaveReqDto dto) {
        Book bookPS = bookRepository.save(dto.toEntity());
        return BookRespDto.toDto(bookPS);
    }

    // 2. 책 목록 보기

    // 3. 책 한권 보기

    // 4. 책 삭제 하기
    
    // 5. 책 수정 하기
}
