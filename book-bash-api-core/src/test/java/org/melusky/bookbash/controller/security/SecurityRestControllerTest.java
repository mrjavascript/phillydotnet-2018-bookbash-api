package org.melusky.bookbash.controller.security;

import lombok.extern.java.Log;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.melusky.bookbash.Application;
import org.melusky.bookbash.config.H2JpaConfig;
import org.melusky.bookbash.controller.base.RestControllerTest;
import org.melusky.bookbash.controller.error.GlobalControllerExceptionHandler;
import org.melusky.bookbash.model.request.security.UserCreateRequest;
import org.melusky.bookbash.model.request.security.UserLoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by mikem on 7/11/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, H2JpaConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Log
public class SecurityRestControllerTest extends RestControllerTest {

    @Autowired
    private SecurityRestController securityRestController;

    @Before
    public void setUp() throws Exception {

        //
        //  Base setup invocation for the controller
        super.setUp();
        this.mockMvc = MockMvcBuilders.standaloneSetup(securityRestController)
                .setControllerAdvice(new GlobalControllerExceptionHandler())
                .build();

        //
        //  Create initial user in the database.
        UserCreateRequest newUser = new UserCreateRequest();
        newUser.setPassword("abcd1234");
        newUser.setEmailAddress("mike@goo.org");
        newUser.setPasswordConfirm("abcd1234");
        newUser.setUserName("larry");
        securityService.createUserAccount(newUser);
    }

    @Test
    public void createUser() throws Exception {

        /*
            Valid
         */
        String newValidUserJson = json(new UserCreateRequest("tom", "test@test.com", "1xxxxxddddd", "1xxxxxddddd", Boolean.FALSE));
        this.mockMvc.perform(post("/user/create")
                .contentType(contentType)
                .content(newValidUserJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jwt").exists());
        log.info("newValidUser successfully created");

        /*
            Invalid
         */
        String newInvalidUserJson = json(new UserCreateRequest("dave", "test@test.com", "12", "12", Boolean.FALSE));
        this.mockMvc.perform(post("/user/create")
                .contentType(contentType)
                .content(newInvalidUserJson))
                .andExpect(status().is4xxClientError());
        log.info("newInvalidUserJson handled");
    }

    @Test
    public void loginUser() throws Exception {

        /*
            Valid
         */
        String goodLoginJson = json(new UserLoginRequest("larry", "abcd1234"));
        this.mockMvc.perform(post("/user/login")
                .contentType(contentType)
                .content(goodLoginJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jwt").exists());
        log.info("goodLoginJson successfully created");

        /*
            Invalid
         */
        String badLoginJson = json(new UserLoginRequest("dave", "12"));
        this.mockMvc.perform(post("/user/login")
                .contentType(contentType)
                .content(badLoginJson)).andExpect(status().is4xxClientError());
        log.info("badLoginJson handled");
    }
}