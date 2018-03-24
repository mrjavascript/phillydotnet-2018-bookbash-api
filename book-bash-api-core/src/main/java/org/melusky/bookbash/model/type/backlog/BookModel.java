package org.melusky.bookbash.model.type.backlog;

import lombok.Data;

import java.util.Date;

@Data
public class BookModel {
    private Long bookId;
    private String isbn;
    private String bookTitle;
    private String authorName;
    private Date datePublished;
    private Long numberOfPages;
}
