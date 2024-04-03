package me.shijunjiee.camundastudy.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingConfiguration.class);

    public LoggingConfiguration() {
        LOGGER.info("Setting log level to DEBUG for com.example package");
        ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("com.example");
        logger.setLevel(ch.qos.logback.classic.Level.DEBUG);
    }
}
