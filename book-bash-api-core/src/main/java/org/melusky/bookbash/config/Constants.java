package org.melusky.bookbash.config;

/**
 * Created by mmelusky on 8/24/2015.
 */
public class Constants {

    public class Inventory {
        public final static String EXPIRATION_DATE_FORMAT = "yyyy-MM-dd";
    }

    public class ApplicationUser {
        public final static long ADMINISTRATIVE_USER_ID = 1;
    }

    public class Environment {
        public final static String LOCAL = "local";
        public final static String DEVELOPMENT = "development";
        public final static String STAGING = "staging";
        public final static String PRODUCTION = "production";
    }

    public class PropertyKey {
        public final static String ENVIRONMENT_NAME = "ENVIRONMENT_NAME";
        public final static String DEV_SUPPORT_ERROR_EMAIL = "DEV_SUPPORT_ERROR_EMAIL";
        public final static String DATA_FILESYSTEM_ROOT = "DATA.FILESYSTEM.ROOT";
        public final static String EMAIL_SUBJECT_PREFIX = "EMAIL.SUBJECT.PREFIX";
        public final static String EMAIL_LOGO_IMG = "EMAIL.LOGO.IMG";
        public final static String EMAIL_COMPANY_NAME = "EMAIL.COMPANY.NAME";
        public final static String EMAIL_COMPANY_DESCRIPTION = "EMAIL.COMPANY.DESCRIPTION";
        public final static String EMAIL_COPYRIGHT_TXT = "EMAIL.COPYRIGHT.TXT";
        public final static String PASSWORD_RESET__HOURS = "PASSWORD_RESET.HOURS";
    }

    public class WebsitePropertyKey {
        public final static String WEBSITE_EMAIL_CONFIRM = "WEBSITE.EMAIL_CONFIRM";
        public final static String WEBSITE_PASSWORD_RESET_REQUEST = "WEBSITE.PASSWORD_RESET_REQUEST";
    }

    public class ThirdPartyApi {
        public final static String WALMART_PRODUCT_API_KEY = "THIRD_PARTY.WALMART.PRODUCT_API_KEY";
        public final static String AMAZON_PRODUCT_API_WSDL_URL = "THIRD_PARTY.AMAZON.WSDL_URL";
        public final static String AMAZON_PRODUCT_API_ASSOCIATE_TAG = "THIRD_PARTY.AMAZON.ASSOCIATE_TAG";
        public final static String AMAZON_PRODUCT_API_ACCESS_KEY_ID = "THIRD_PARTY.AMAZON.ACCESS_KEY_ID";
        public final static String AMAZON_PRODUCT_API_SECRET_KEY = "THIRD_PARTY.AMAZON.SECRET_KEY";
    }

    public class JpaProperty {
        public final static String MAIN_DATA_SOURCE__DRIVER_CLASSNAME = "MAIN_DATA_SOURCE.DRIVER_CLASSNAME";
        public final static String MAIN_DATA_SOURCE__JDBC_URL = "MAIN_DATA_SOURCE.JDBC_URL";
        public final static String MAIN_DATA_SOURCE__USERNAME = "MAIN_DATA_SOURCE.USERNAME";
        public final static String MAIN_DATA_SOURCE__PASSWORD = "MAIN_DATA_SOURCE.PASSWORD";
    }

    public class SmtpProperty {
        public final static String SMTP_FROM = "SMTP.FROM";
        public final static String SMTP_PORT = "SMTP.PORT";
        public final static String SMTP_AUTH = "SMTP.AUTH";
        public final static String SMTP_STARTTLS = "SMTP.STARTTLS";
        public final static String SMTP_PROTOCOL = "SMTP.PROTOCOL";
        public final static String SMTP_DEBUG = "SMTP.DEBUG";
        public final static String SMTP_HOST = "SMTP.HOST";
        public final static String SMTP_USER = "SMTP.USER";
        public final static String SMTP_PASS = "SMTP.PASS";
    }

    public class SwaggerProperty {
        public final static String SWAGGER_HOST = "SWAGGER.HOST";
        public final static String SWAGGER_BASE = "SWAGGER.BASE";
    }

}
