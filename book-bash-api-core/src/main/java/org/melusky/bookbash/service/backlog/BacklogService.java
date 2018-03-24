package org.melusky.bookbash.service.backlog;

import org.melusky.bookbash.persistence.model.obj.bookBash.BacklogStatus;
import org.melusky.bookbash.persistence.model.obj.bookBash.Book;
import org.melusky.bookbash.persistence.model.obj.bookBash.BookBacklog;

import java.util.List;

public interface BacklogService {
    Book findBookByIsbn(String isbn, Long currentUserId);

    List<BookBacklog> getUserBacklog(Long currentUserId);

    BookBacklog addUserBacklog(Long bookId, Double rating, Long statusId, Long currentUserId);

    BookBacklog editUserBacklog(Long recordID, Long bookId, Double rating, Long statusId, Long currentUserId);

    BookBacklog deleteUserBacklogItem(Long recordId, Long currentUserId);

    List<BacklogStatus> getBacklogStatusList();
}
