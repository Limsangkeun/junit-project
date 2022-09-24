package com.junitproject.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.junitproject.domain.Book;
import com.junitproject.domain.BookRepository;
import com.junitproject.util.MailSender;
import com.junitproject.web.dto.BookRespDto;
import com.junitproject.web.dto.BookSaveReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookService {

    @Autowired
    private final BookRepository bookRepository;

    @Autowired
    private final MailSender mailSender;
    
    // 1. 책 등록
    @Transactional(rollbackFor = RuntimeException.class)
    public BookRespDto saveBook(BookSaveReqDto dto) {
        Book bookPS = bookRepository.save(dto.toEntity());
        if(Optional.of(bookPS).isPresent()) {
            if(!mailSender.send()) {
                throw new RuntimeException("메일이 전송되지 않았습니다.");
            }
        }
        return BookRespDto.toDto(bookPS);
    }

    // 2. 책 목록 보기
    public List<BookRespDto> findBookList () {
        return bookRepository.findAll().stream().map(bookPS -> {
            return BookRespDto.toDto(bookPS);
        }).collect(Collectors.toList());
    }

    // 3. 책 한권 보기
    public BookRespDto findBookById(Long id) {
        Optional<Book> optBook = bookRepository.findById(id);

        if(optBook.isPresent()) {
            return BookRespDto.toDto(optBook.get());
        } else {
            throw new RuntimeException("해당 아이디의 책이 없습니다.");
        }
    }

    // 4. 책 삭제 하기
    @Transactional(rollbackFor = RuntimeException.class)
    public void removeBook (Long id) {
        bookRepository.deleteById(id);
    }
    
    // 5. 책 수정 하기
    @Transactional(rollbackFor = RuntimeException.class)
    public void modifyBook(Long id, BookSaveReqDto dto) {
        Optional<Book> optBook = bookRepository.findById(id);
        if(optBook.isPresent()) {
            Book targetBook = optBook.get();
            targetBook.setTitle(dto.getTitle());
            targetBook.setAuthor(dto.getAuthor());
        } else {
            throw new RuntimeException("해당 아이디의 책이 없습니다.");
        }
    }
}
