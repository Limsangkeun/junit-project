package com.junitproject.web.dto;

import com.junitproject.domain.Book;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookRespDto {
    Long id;
    String title;
    String author;

    private BookRespDto (Long id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public static BookRespDto toDto (Book bookPS) {
        return new BookRespDto(bookPS.getId(), bookPS.getTitle(), bookPS.getAuthor());
    }
}
