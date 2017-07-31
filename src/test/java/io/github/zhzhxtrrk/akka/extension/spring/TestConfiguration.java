package io.github.zhzhxtrrk.akka.extension.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Jsr330ScopeMetadataResolver;

/**
 * TestConfiguration
 *
 * @author Zhe ZHANG
 * @version TestConfiguration, v1.0 2017/7/30 22:22
 */
@Configuration
@ComponentScan(scopeResolver = Jsr330ScopeMetadataResolver.class, basePackages = {
                                                                                  "io.github.zhzhxtrrk.akka.extension.service",
                                                                                  "io.github.zhzhxtrrk.akka.extension.spring" })
public class TestConfiguration {
}
