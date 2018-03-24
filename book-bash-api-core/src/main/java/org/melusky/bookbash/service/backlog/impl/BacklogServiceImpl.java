package org.melusky.bookbash.service.backlog.impl;

import org.melusky.bookbash.dao.backlog.BacklogDao;
import org.melusky.bookbash.persistence.model.obj.bookBash.BacklogStatus;
import org.melusky.bookbash.persistence.model.obj.bookBash.Book;
import org.melusky.bookbash.persistence.model.obj.bookBash.BookBacklog;
import org.melusky.bookbash.service.backlog.BacklogService;
import org.melusky.bookbash.service.thirdparty.ThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BacklogServiceImpl implements BacklogService {

    @Autowired
    private ThirdPartyService thirdPartyService;

    @Autowired
    private BacklogDao backlogDao;


    @Override
    public Book findBookByIsbn(String isbn, Long currentUserId) {
        if (isbn == null || isbn.isEmpty()) {
            throw new IllegalArgumentException("Isbn is required");
        }

        // Look in local DB first
        Book book = backlogDao.findBookByIsbn(isbn);
        if (book != null && book.getId() > 0) {
            return book;
        }

        // Use amazon API instead
        book = thirdPartyService.findAmazonBookByIsbn(isbn);
        if (book != null) {
            book.setUserCreated(currentUserId);
            book.setUserUpdated(currentUserId);
            return backlogDao.createBookRecord(book);
        }

        throw new IllegalStateException("Not found by ISBN");
    }

    @Override
    public List<BookBacklog> getUserBacklog(Long currentUserId) {
        return backlogDao.getUserBacklog(currentUserId);
    }

    @Override
    public BookBacklog addUserBacklog(Long bookId, Double rating, Long statusId, Long currentUserId) {
        validateBacklogItem(bookId, rating, statusId);
        return backlogDao.addUserBacklog(bookId, rating, statusId, currentUserId);
    }

    @Override
    public BookBacklog editUserBacklog(Long recordID, Long bookId, Double rating, Long statusId, Long currentUserId) {
        if (recordID == null || recordID <= 0) {
            throw new IllegalArgumentException("record ID is required");
        } else {
            validateBacklogItem(bookId, rating, statusId);
        }

        return backlogDao.editUserBacklog(recordID, bookId, rating, statusId, currentUserId);
    }

    private void validateBacklogItem(Long bookId, Double rating, Long statusId) {
        if (bookId == null || bookId <= 0) {
            throw new IllegalArgumentException("book ID is required");
        } else if (rating == null || rating <= 0) {
            throw new IllegalArgumentException("rating is required");
        } else if (statusId == null || statusId <= 0) {
            throw new IllegalArgumentException("status id is required");
        }
    }

    @Override
    public BookBacklog deleteUserBacklogItem(Long recordId, Long currentUserId) {
        if (recordId == null || recordId <= 0) {
            throw new IllegalArgumentException("record ID is required");
        }

        return backlogDao.deleteUserBacklogItem(recordId, currentUserId);
    }

    @Override
    public List<BacklogStatus> getBacklogStatusList() {
        return backlogDao.getBacklogStatusList();
    }
}
