package org.melusky.bookbash.service.util.impl;

import lombok.extern.slf4j.Slf4j;
import org.melusky.bookbash.service.util.DatabaseCreationProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by mikem on 7/9/2017.
 */
@Service
@Slf4j
public class DatabaseCreationProcessImpl implements DatabaseCreationProcess {

    private static final String SCHEMA = "/../database/schema/book_bash.sql";
    private static final String REFERENCE_DATA = "/../database/reference/reference_data.sql";

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public void createDatabase() {
        createBaseImage();
        log("** ALL DONE! **");
    }

    private void createBaseImage() {

        //
        //  Drop the existing schemas
        dropExistingSchemas();

        //
        //  Recreate the schema
        recreateDatabaseSchema();

        //
        //  Load the reference data
        loadReferenceData();
    }

    private void loadReferenceData() {
        try {
            ScriptUtils.executeSqlScript(dataSource.getConnection(), resourceLoader.getResource(String.format("file:%s%s", getCwd(), REFERENCE_DATA)));
            log("** REFERENCE DATA LOADED");
        } catch (SQLException e) {
            error("Unable to load reference data: ", e);
        }
    }

    private void recreateDatabaseSchema() {
        try {
            ScriptUtils.executeSqlScript(dataSource.getConnection(), resourceLoader.getResource(String.format("file:%s%s", getCwd(), SCHEMA)));
            log("** BOOK BASH CREATED");
        } catch (SQLException e) {
            error("Unable to create CALIPHR SCHEMA: ", e);
        }
    }

    private void dropExistingSchemas() {
        Statement statement = null;

        // Obtain database connection
        try {
            statement = dataSource.getConnection().createStatement();
        } catch (SQLException e) {
            error("Unable to obtain database connection", e);
        }

        // Drop schema
        try {
            statement.execute("DROP SCHEMA book_bash CASCADE");
            log("** BOOK BASH schema dropped");
        } catch (SQLException e) {
            error("Unable to drop schema", e);
        }

    }

    private void log(String msg) {
        log.info(msg);
        System.out.println(msg);
    }

    private void error(String msg, Exception e) {
        log.error(msg, e);
        System.err.println(msg + " - " + e.getMessage());
    }

    private String getCwd() {
        return System.getProperty("user.dir");
    }

}
