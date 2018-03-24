package org.melusky.bookbash.service.thirdparty.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.melusky.bookbash.Application;
import org.melusky.bookbash.config.H2JpaConfig;
import org.melusky.bookbash.persistence.model.obj.bookBash.ApplicationUser;
import org.melusky.bookbash.persistence.model.obj.bookBash.Book;
import org.melusky.bookbash.persistence.model.obj.bookBash.repository.ApplicationUserRepository;
import org.melusky.bookbash.service.thirdparty.ThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest(classes = {Application.class, H2JpaConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@RunWith(SpringRunner.class)
public class ThirdPartyServiceImplTest {

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Autowired
    private ThirdPartyService thirdPartyService;

    private static Boolean setUpIsDone = false;
    private static Long userId = null;

    @Before
    public void setUp() throws Exception {

        //
        //  Create initial user
        if (setUpIsDone) {
            return;
        }

        //  add a test user
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setUsername("thirdparty_test");
        applicationUserRepository.save(applicationUser);
        userId = applicationUser.getId();
    }

    @Test
    public void findAmazonBookByIsbn() throws Exception {
        Book book = thirdPartyService.findAmazonBookByIsbn("0132350882");
        assertNotNull(book);
        assertEquals("Clean Code: A Handbook of Agile Software Craftsmanship", book.getBookTitle());

        book = thirdPartyService.findAmazonBookByIsbn("1548344176");
        assertNotNull(book);
        assertEquals("Wuthering Heights", book.getBookTitle());
    }

}