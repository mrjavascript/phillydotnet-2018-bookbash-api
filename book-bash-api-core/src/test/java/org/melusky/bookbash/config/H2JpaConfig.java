package org.melusky.bookbash.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Properties;

/**
 * Created by mikem on 7/9/2017.
 */

@Configuration
public class H2JpaConfig extends JpaConfig {

    @Primary
    @Override
    @Bean(name = "book_bashDataSource", destroyMethod = "shutdown")
    public HikariDataSource mainDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:h2:mem:book_bash;MODE=PostgreSQL;DB_CLOSE_ON_EXIT=-1;MULTI_THREADED=true;INIT=CREATE SCHEMA if not exists book_bash");
        dataSource.setMaximumPoolSize(1000);
        dataSource.setAutoCommit(true);
        return dataSource;
    }

    @Override
    protected Properties additionalProperties() {
        Properties properties = super.additionalProperties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.setProperty("hibernate.hbm2ddl.auto", "create");
        return properties;
    }

}
