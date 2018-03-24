package org.melusky.bookbash.utility.yaml;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * Created by mikem on 6/26/2017.
 */
public class YamlApplicationContextInitializerUtil {

    private final static Logger logger = (Logger) LoggerFactory.getLogger(YamlApplicationContextInitializerUtil.class);

    public static void loadYamlFileIfExists(String resourceName, ConfigurableApplicationContext applicationContext) throws IOException {

        Resource resource = applicationContext.getResource(resourceName);
        if (resource.exists()) {
            for (String profile : applicationContext.getEnvironment().getActiveProfiles()) {
                loadYamlResource(applicationContext, resource, profile);
            }
            loadYamlResource(applicationContext, resource, null);

        }
    }

    public static void loadYamlResource(ConfigurableApplicationContext applicationContext, Resource resource, String profile)
            throws IOException {

        String profileMsg = profile != null ? " (for profile " + profile + ")" : "";

        YamlPropertySourceLoader sourceLoader = new YamlPropertySourceLoader();
        PropertySource<?> yamlTestProperties = sourceLoader.load(resource.getDescription() + profileMsg, resource, profile);
        if (yamlTestProperties != null) {
            applicationContext.getEnvironment().getPropertySources().addLast(yamlTestProperties);
            logger.debug("loaded " + resource + profileMsg);
        }
    }

}