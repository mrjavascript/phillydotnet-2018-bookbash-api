package org.melusky.bookbash.service.thirdparty;

import org.melusky.bookbash.persistence.model.obj.bookBash.Book;

public interface ThirdPartyService {
    Book findAmazonBookByIsbn(String isbn);
}
