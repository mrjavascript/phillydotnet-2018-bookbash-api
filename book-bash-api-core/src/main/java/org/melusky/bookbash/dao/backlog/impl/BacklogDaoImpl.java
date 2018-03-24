package org.melusky.bookbash.dao.backlog.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.melusky.bookbash.dao.backlog.BacklogDao;
import org.melusky.bookbash.persistence.model.obj.bookBash.BacklogStatus;
import org.melusky.bookbash.persistence.model.obj.bookBash.Book;
import org.melusky.bookbash.persistence.model.obj.bookBash.BookBacklog;
import org.melusky.bookbash.persistence.model.obj.bookBash.repository.ApplicationUserRepository;
import org.melusky.bookbash.persistence.model.obj.bookBash.repository.BacklogStatusRepository;
import org.melusky.bookbash.persistence.model.obj.bookBash.repository.BookBacklogRepository;
import org.melusky.bookbash.persistence.model.obj.bookBash.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.melusky.bookbash.persistence.model.obj.bookBash.QBacklogStatus.backlogStatus;
import static org.melusky.bookbash.persistence.model.obj.bookBash.QBook.book;
import static org.melusky.bookbash.persistence.model.obj.bookBash.QBookBacklog.bookBacklog;

@Repository
public class BacklogDaoImpl implements BacklogDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Autowired
    private BookBacklogRepository bookBacklogRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BacklogStatusRepository backlogStatusRepository;

    @Override
    public List<BacklogStatus> getBacklogStatusList() {
        return new JPAQueryFactory(entityManager)
                .selectFrom(backlogStatus)
                .where(backlogStatus.dateDisabled.isNull())
                .fetch();
    }

    @Override
    public BookBacklog deleteUserBacklogItem(Long recordId, Long currentUserId) {
        BookBacklog bookBacklog = bookBacklogRepository.findOne(recordId);
        if (bookBacklog == null) {
            throw new IllegalStateException("Backlog record not found!");
        }

        bookBacklog.setDateUpdated(new Date());
        bookBacklog.setUserUpdated(currentUserId);
        bookBacklog.setDateDisabled(new Date());
        return bookBacklogRepository.save(bookBacklog);
    }

    @Override
    public BookBacklog editUserBacklog(Long recordID, Long bookId, Double rating, Long statusId, Long currentUserId) {
        BookBacklog bookBacklog = bookBacklogRepository.findOne(recordID);
        if (bookBacklog == null) {
            throw new IllegalStateException("Backlog record not found!");
        }
        bookBacklog.setBook(bookRepository.findOne(bookId));
        bookBacklog.setRating(BigDecimal.valueOf(rating));
        bookBacklog.setStatus(backlogStatusRepository.findOne(statusId));
        bookBacklog.setDateUpdated(new Date());
        bookBacklog.setUserUpdated(currentUserId);
        return bookBacklogRepository.save(bookBacklog);
    }

    @Override
    public BookBacklog addUserBacklog(Long bookId, Double rating, Long statusId, Long currentUserId) {
        BookBacklog bookBacklog = new BookBacklog();
        bookBacklog.setUser(applicationUserRepository.findOne(currentUserId));
        bookBacklog.setBook(bookRepository.findOne(bookId));
        bookBacklog.setRating(BigDecimal.valueOf(rating));
        bookBacklog.setStatus(backlogStatusRepository.findOne(statusId));
        bookBacklog.setDateCreated(new Date());
        bookBacklog.setUserCreated(currentUserId);
        bookBacklog.setDateUpdated(new Date());
        bookBacklog.setUserUpdated(currentUserId);
        return bookBacklogRepository.save(bookBacklog);
    }

    @Override
    public List<BookBacklog> getUserBacklog(Long currentUserId) {
        return new JPAQueryFactory(entityManager)
                .selectFrom(bookBacklog)
                .innerJoin(bookBacklog.book, book).fetchJoin()
                .innerJoin(bookBacklog.status, backlogStatus).fetchJoin()
                .where(bookBacklog.dateDisabled.isNull(), bookBacklog.user.id.eq(currentUserId))
                .fetch();
    }

    @Override
    public Book createBookRecord(Book book) {
        book.setDateCreated(new Date());
        book.setDateUpdated(new Date());
        return bookRepository.save(book);
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return new JPAQueryFactory(entityManager)
                .selectFrom(book)
                .where(book.isbn.equalsIgnoreCase(isbn))
                .fetchFirst();
    }
}
