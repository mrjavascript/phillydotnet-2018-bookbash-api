package org.melusky.bookbash.service.security.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.melusky.bookbash.Application;
import org.melusky.bookbash.config.H2JpaConfig;
import org.melusky.bookbash.model.request.security.UserCreateRequest;
import org.melusky.bookbash.model.request.security.UserLoginRequest;
import org.melusky.bookbash.persistence.model.obj.bookBash.ApplicationUser;
import org.melusky.bookbash.service.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by mikem on 7/10/2017.
 */
@SpringBootTest(classes = {Application.class, H2JpaConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@RunWith(SpringRunner.class)
public class SecurityServiceImplTest {

    @Autowired
    private SecurityService securityService;

    @Before
    public void setUp() throws Exception {

        //
        //  Create initial user
        UserCreateRequest newUser = new UserCreateRequest();
        newUser.setUserName("mike");
        newUser.setPassword("abcd1234");
        newUser.setPasswordConfirm("abcd1234");
        newUser.setEmailAddress("mike@foo.org");
        securityService.createUserAccount(newUser);
    }

    @Test
    public void test_validate_account_creation() throws Exception {

        //
        //  Non matching password
        UserCreateRequest nonMatchingPassword = new UserCreateRequest();
        nonMatchingPassword.setUserName("bad");
        nonMatchingPassword.setPassword("abc");
        nonMatchingPassword.setPasswordConfirm("ABC");
        nonMatchingPassword.setEmailAddress("mike@foo.org");
        assertEquals(1, securityService.validateUserAccountCreation(nonMatchingPassword).size());

        //
        //  Existing user?
        UserCreateRequest existingUser = new UserCreateRequest();
        existingUser.setUserName("mike");
        existingUser.setPassword("abcd1234");
        existingUser.setPasswordConfirm("abcd1234");
        existingUser.setEmailAddress("mike@foo.org");
        assertEquals(1, securityService.validateUserAccountCreation(existingUser).size());

        //
        //  Lousy password?
        UserCreateRequest badPassword = new UserCreateRequest();
        badPassword.setUserName("blarg");
        badPassword.setPassword("ab12");
        badPassword.setPasswordConfirm("ab12");
        badPassword.setEmailAddress("mike@foo.org");
        assertEquals(1, securityService.validateUserAccountCreation(badPassword).size());

        //
        //  Good!
        UserCreateRequest goodUser = new UserCreateRequest();
        goodUser.setUserName("good");
        goodUser.setPassword("abcde12345");
        goodUser.setPasswordConfirm("abcde12345");
        goodUser.setEmailAddress("mike@foo.org");
        assertEquals(0, securityService.validateUserAccountCreation(goodUser).size());
    }

    @Test
    public void test_create_account() throws Exception {
        UserCreateRequest newUser = new UserCreateRequest();
        newUser.setUserName("dave");
        newUser.setPassword("abcd1234");
        newUser.setPasswordConfirm("abcd1234");
        newUser.setEmailAddress("mike@foo.org");
        ApplicationUser user = securityService.createUserAccount(newUser);
        assertNotNull(user.getId());
        assertEquals(user.getUsername(), newUser.getUserName());
    }

    @Test
    public void test_login_account() throws Exception {
        UserLoginRequest goodAccount = new UserLoginRequest();
        goodAccount.setUserName("mike");
        goodAccount.setPassword("abcd1234");
        ApplicationUser user = securityService.findUserByLoginDetails(goodAccount);
        assertNotNull(user.getId());
        assertEquals(user.getUsername(), goodAccount.getUserName());
    }

    @Test(expected = Exception.class)
    public void test_bad_login_account() throws Exception {
        UserLoginRequest badAccount = new UserLoginRequest();
        badAccount.setUserName("mike");
        badAccount.setPassword("abcd12345");
        securityService.findUserByLoginDetails(badAccount);
    }

    @Test
    public void test_find_user_by_id_good() throws Exception {
        ApplicationUser applicationUser = securityService.findUserByUserId(1L);
        assertNotNull(applicationUser);
    }

    @Test(expected = IllegalStateException.class)
    public void test_find_user_by_id_bad() throws Exception {
        securityService.findUserByUserId(-1L);
    }

}