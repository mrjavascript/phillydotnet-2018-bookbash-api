package org.melusky.bookbash.model.type.backlog;

import lombok.Data;

@Data
public class BacklogItemModel {
    private Long recordId;
    private BookModel book;
    private BacklogStatusModel status;
    private Double rating;
}
