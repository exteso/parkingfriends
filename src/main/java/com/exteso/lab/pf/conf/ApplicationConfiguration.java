package com.exteso.lab.pf.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.IOException;

@Configuration
@PropertySource({"classpath:/META-INF/ParkingFriends/ParkingFriends.properties"})
@ComponentScan(basePackages = {
        "com.exteso.lab.pf.service",
        "com.exteso.lab.pf.security"})
@Import(value = {
        AsyncConfiguration.class,
        CacheConfiguration.class,
        DatabaseConfiguration.class,
        MailConfiguration.class})
@ImportResource("classpath:META-INF/spring/applicationContext-*.xml")
public class ApplicationConfiguration {

    private static final Logger log = LoggerFactory.getLogger(ApplicationConfiguration.class);

    @Inject
    private Environment env;

    /**
     * Initializes ParkingFriends.
     * <p/>
     * Spring profiles can be configured with a system property -Dspring.profiles.active=your-active-profile
     * <p/>
     */
    @PostConstruct
    public void initApplication() throws IOException {
        log.debug("Looking for Spring profiles...");
        if (env.getActiveProfiles().length == 0) {
            log.debug("No Spring profile configured, running with default configuration");
        } else {
            for (String profile : env.getActiveProfiles()) {
                log.debug("Detected Spring profile : {}", profile);
            }
        }
    }
}
