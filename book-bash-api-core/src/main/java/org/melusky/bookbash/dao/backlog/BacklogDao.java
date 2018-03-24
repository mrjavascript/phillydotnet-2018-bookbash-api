package org.melusky.bookbash.dao.backlog;

import org.melusky.bookbash.persistence.model.obj.bookBash.BacklogStatus;
import org.melusky.bookbash.persistence.model.obj.bookBash.Book;
import org.melusky.bookbash.persistence.model.obj.bookBash.BookBacklog;

import java.util.List;

public interface BacklogDao {
    List<BacklogStatus> getBacklogStatusList();

    BookBacklog deleteUserBacklogItem(Long recordId, Long currentUserId);

    BookBacklog editUserBacklog(Long recordID, Long bookId, Double rating, Long statusId, Long currentUserId);

    BookBacklog addUserBacklog(Long bookId, Double rating, Long statusId, Long currentUserId);

    List<BookBacklog> getUserBacklog(Long currentUserId);

    Book createBookRecord(Book book);

    Book findBookByIsbn(String isbn);
}
