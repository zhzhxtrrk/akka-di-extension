package io.github.zhzhxtrrk.akka.extension.guice.actor;

import com.google.inject.AbstractModule;

import io.github.zhzhxtrrk.akka.extension.service.MessageService;
import io.github.zhzhxtrrk.akka.extension.service.MessageServiceImpl;

/**
 * Module
 *
 * @author Zhe ZHANG
 * @version Module, v1.0 2017/7/27 8:50
 */
public class Module extends AbstractModule {
    @Override
    protected void configure() {
        bind(MessageService.class).to(MessageServiceImpl.class);
    }
}
