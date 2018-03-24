package org.melusky.bookbash.service.util.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.melusky.bookbash.Application;
import org.melusky.bookbash.service.util.DatabaseCreationProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by mikem on 7/9/2017.
 */
@SpringBootTest(classes = {Application.class})
@RunWith(SpringRunner.class)
public class DatabaseCreationProcessImplTest {

    @Autowired
    private DatabaseCreationProcess databaseCreationProcess;

    @Test
    public void test_create_database() throws Exception {
        databaseCreationProcess.createDatabase();
    }

}