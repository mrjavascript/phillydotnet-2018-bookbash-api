package org.melusky.bookbash.controller.backlog;

import io.swagger.annotations.Api;
import org.melusky.bookbash.model.type.backlog.BacklogItemModel;
import org.melusky.bookbash.model.type.backlog.BacklogStatusModel;
import org.melusky.bookbash.model.type.backlog.BookModel;
import org.melusky.bookbash.persistence.model.obj.bookBash.BacklogStatus;
import org.melusky.bookbash.persistence.model.obj.bookBash.Book;
import org.melusky.bookbash.persistence.model.obj.bookBash.BookBacklog;
import org.melusky.bookbash.service.backlog.BacklogService;
import org.melusky.bookbash.utility.security.SecurityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(value = "bookbash_api", description = "Request methods used to modify and retrieve backlog details.")
public class BacklogRestController {

    @Autowired
    private BacklogService backlogService;

    @GetMapping("/api/backlog/search")
    public BookModel findBookByIsbn(@RequestParam String isbn) {
        Book book = backlogService.findBookByIsbn(isbn, SecurityHelper.getCurrentUserId());
        return toBookModel(book);
    }

    private BookModel toBookModel(Book record) {
        BookModel model = new BookModel();
        model.setBookId(record.getId());
        model.setAuthorName(record.getAuthorName());
        model.setBookTitle(record.getBookTitle());
        model.setDatePublished(record.getDatePublished());
        model.setNumberOfPages(record.getNumberOfPages());
        model.setIsbn(record.getIsbn());
        return model;
    }

    @GetMapping("/api/backlog/list")
    public List<BacklogItemModel> getUserBacklog() {
        return backlogService.getUserBacklog(SecurityHelper.getCurrentUserId())
                .stream()
                .map(o -> toBacklogItemModel(o))
                .collect(Collectors.toList());
    }

    private BacklogItemModel toBacklogItemModel(BookBacklog record) {
        BacklogItemModel model = new BacklogItemModel();
        model.setBook(toBookModel(record.getBook()));
        model.setRating(record.getRating().doubleValue());
        model.setRecordId(record.getId());
        model.setStatus(toBacklogStatusModel(record.getStatus()));
        return model;
    }

    @PostMapping("/api/backlog/add")
    public String addUserBacklog(@RequestParam Long bookId, @RequestParam Double rating, @RequestParam Long statusId) {
        BookBacklog bookBacklog = backlogService.addUserBacklog(bookId, rating, statusId, SecurityHelper.getCurrentUserId());
        if (bookBacklog != null && bookBacklog.getId() > 0) {
            return "OK";
        }

        throw new IllegalStateException("Unable to add backlog item");
    }

    @PostMapping("/api/backlog/edit")
    public String editUserBacklog(@RequestParam Long recordID, @RequestParam Long bookId, @RequestParam Double rating, @RequestParam Long statusId) {
        BookBacklog bookBacklog = backlogService.editUserBacklog(recordID, bookId, rating, statusId, SecurityHelper.getCurrentUserId());
        if (bookBacklog != null && bookBacklog.getId() > 0) {
            return "OK";
        }

        throw new IllegalStateException("Unable to edit backlog item");
    }

    @PostMapping("/api/backlog/delete")
    public String deleteUserBacklogItem(@RequestParam Long recordId) {
        BookBacklog bookBacklog = backlogService.deleteUserBacklogItem(recordId, SecurityHelper.getCurrentUserId());
        if (bookBacklog != null && bookBacklog.getId() > 0 && bookBacklog.getDateDisabled() != null) {
            return "OK";
        }

        throw new IllegalStateException("Unable to delete backlog item");
    }

    @GetMapping("/api/backlog/statuses")
    public List<BacklogStatusModel> getBacklogStatusList() {
        return backlogService.getBacklogStatusList().stream()
                .map(o -> toBacklogStatusModel(o)).collect(Collectors.toList());
    }

    private BacklogStatusModel toBacklogStatusModel(BacklogStatus record) {
        BacklogStatusModel model = new BacklogStatusModel();
        model.setTypeId(record.getId());
        model.setTypeName(record.getTypeName());
        model.setTypeDescription(record.getTypeDescription());
        return model;
    }

}
