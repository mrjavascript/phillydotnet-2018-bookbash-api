package org.melusky.bookbash.service.thirdparty.impl;

import lombok.extern.slf4j.Slf4j;
import org.melusky.bookbash.config.Constants;
import org.melusky.bookbash.persistence.model.obj.bookBash.Book;
import org.melusky.bookbash.service.aws.*;
import org.melusky.bookbash.service.thirdparty.ThirdPartyService;
import org.melusky.bookbash.utility.thirdparty.AwsHandlerResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.text.SimpleDateFormat;

@Service
@Slf4j
public class ThirdPartyServiceImpl implements ThirdPartyService {

    @Autowired
    private Environment environment;

    @Override
    public Book findAmazonBookByIsbn(String isbn) {

        /*
            Make call using Amazon AWS SOAP service.
         */

        // Get a handle to the web service
        try {
            URL url = new URL(environment.getProperty(Constants.ThirdPartyApi.AMAZON_PRODUCT_API_WSDL_URL));

            //
            //  Web service instantiate
            AWSECommerceService service = new AWSECommerceService(url);
            service.setHandlerResolver(new AwsHandlerResolver(environment.getProperty(Constants.ThirdPartyApi.AMAZON_PRODUCT_API_SECRET_KEY)));
            AWSECommerceServicePortType port = service.getAWSECommerceServicePort();

            //
            //  Set barcode
            ItemLookupRequest itemLookupRequest = new ItemLookupRequest();
            itemLookupRequest.setIdType("ISBN");
            itemLookupRequest.getItemId().add(isbn);
            itemLookupRequest.setSearchIndex("All");

            //
            //  Set amazon affiliate ID
            ItemLookup itemLookup = new ItemLookup();
            itemLookup.setAWSAccessKeyId(environment.getProperty(Constants.ThirdPartyApi.AMAZON_PRODUCT_API_ACCESS_KEY_ID));
            itemLookup.setAssociateTag(environment.getProperty(Constants.ThirdPartyApi.AMAZON_PRODUCT_API_ASSOCIATE_TAG));
            itemLookup.getRequest().add(itemLookupRequest);
            ItemLookupResponse response = port.itemLookup(itemLookup);

            for (Items itemList : response.getItems()) {
                for (Item item : itemList.getItem()) {
                    ItemAttributes itemAttributes = item.getItemAttributes();
                    Book book = new Book();
                    if (itemAttributes.getAuthor() != null) {
                        book.setAuthorName(itemAttributes.getAuthor().get(0));
                    }
                    if (itemAttributes.getNumberOfPages() != null) {
                        book.setNumberOfPages(itemAttributes.getNumberOfPages().longValue());
                    }
                    if (itemAttributes.getPublicationDate() != null) {
                        book.setDatePublished(new SimpleDateFormat("yyyy-dd-MM").parse(itemAttributes.getPublicationDate()));
                    }
                    book.setBookTitle(itemAttributes.getTitle());
                    if (itemAttributes.getISBN() != null && !itemAttributes.getISBN().isEmpty()) {
                        book.setIsbn(itemAttributes.getISBN());
                    } else {
                        book.setIsbn(isbn);
                    }
                    return book;
                }
            }
        } catch (Exception e) {
            log.error("Amazon API exceptions: {}", e);
        }

        return null;
    }
}
