package org.melusky.bookbash.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "org.melusky.bookbash.persistence.model.obj.bookBash.repository")
@EnableCaching
public class JpaConfig {

    @Autowired
    private ConfigurableEnvironment environment;

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager tm = new JpaTransactionManager();
        tm.setEntityManagerFactory(entityManagerFactory().getObject());
        return tm;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor persistenceManager() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Primary
    @Bean(name = "book_bashDataSource", destroyMethod = "shutdown")
    public HikariDataSource mainDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername(environment.getProperty(Constants.JpaProperty.MAIN_DATA_SOURCE__USERNAME));
        dataSource.setPassword(environment.getProperty(Constants.JpaProperty.MAIN_DATA_SOURCE__PASSWORD));
        dataSource.setJdbcUrl(environment.getProperty(Constants.JpaProperty.MAIN_DATA_SOURCE__JDBC_URL));
        dataSource.setDriverClassName(environment.getProperty(Constants.JpaProperty.MAIN_DATA_SOURCE__DRIVER_CLASSNAME));
        dataSource.setMaximumPoolSize(20);
        return dataSource;
    }

    @Bean
    public LazyConnectionDataSourceProxy dataSource() {
        LazyConnectionDataSourceProxy dataSource = new LazyConnectionDataSourceProxy();
        dataSource.setTargetDataSource(mainDataSource());
        return dataSource;
    }

    @Bean
    public HibernateJpaVendorAdapter vendorAdapter() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabasePlatform("");
        return vendorAdapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setPersistenceUnitName("book_bash-persistence");
        em.setDataSource(dataSource());
        em.setJpaVendorAdapter(vendorAdapter());
        em.setJpaProperties(additionalProperties());
        em.setPackagesToScan("org.melusky.bookbash.persistence.model.obj.bookBash");
        return em;
    }

    @Bean
    public EhCacheManagerFactoryBean ehCacheCacheManager() {
        // use the same ehcache configuration as the hibernate second level cache set up in the JPA config additionalProperties()
        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        cacheManagerFactoryBean.setCacheManagerName("persistenceEhcacheModel");
        cacheManagerFactoryBean.setAcceptExisting(true);
        cacheManagerFactoryBean.setShared(Boolean.TRUE);
        return cacheManagerFactoryBean;
    }

    @Bean(name = "cacheManager")
    public EhCacheCacheManager ehCacheManager() {
        return new EhCacheCacheManager(ehCacheCacheManager().getObject());
    }

    protected Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
        properties.setProperty("hibernate.flush_before_completion", "true");
        properties.setProperty("hibernate.generate_statistics", "false");
//        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.current_session_context_class", "org.springframework.orm.hibernate4.SpringSessionContext");
        properties.setProperty("hibernate.id.new_generator_mappings", "true");
        properties.setProperty("hibernate.id.optimizer.pooled.prefer_lo", "true");
        properties.setProperty("hibernate.cache.use_second_level_cache", "true");
        properties.setProperty("hibernate.cache.use_query_cache", "true");
        properties.setProperty("javax.persistence.sharedCache.mode", "ENABLE_SELECTIVE");
//        properties.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
        properties.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory");
        properties.setProperty("net.sf.ehcache.configurationResourceName", "ehcache-model.xml");
        return properties;
    }
}
