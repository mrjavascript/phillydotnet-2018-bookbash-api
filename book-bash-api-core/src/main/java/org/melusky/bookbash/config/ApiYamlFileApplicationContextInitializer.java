package org.melusky.bookbash.config;

import org.melusky.bookbash.utility.yaml.YamlApplicationContextInitializerUtil;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

/**
 * Created by mikem on 6/26/2017.
 */
public class ApiYamlFileApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        try {
            for (String profile : applicationContext.getEnvironment().getActiveProfiles()) {
                YamlApplicationContextInitializerUtil.loadYamlFileIfExists("classpath:config/api-" + profile + ".yaml", applicationContext);
            }
            YamlApplicationContextInitializerUtil.loadYamlFileIfExists("classpath:config/api.yaml", applicationContext);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
