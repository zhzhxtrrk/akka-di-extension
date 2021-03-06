package io.github.zhzhxtrrk.akka.extension.service;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * MessageServiceImpl
 *
 * @author Zhe ZHANG
 * @version MessageServiceImpl, v1.0 2017/7/27 8:50
 */
@Named
@Singleton
public class MessageServiceImpl implements MessageService {
    @Override
    public String message(String name) {
        return "Guys, please join me welcoming " + name;
    }
}
