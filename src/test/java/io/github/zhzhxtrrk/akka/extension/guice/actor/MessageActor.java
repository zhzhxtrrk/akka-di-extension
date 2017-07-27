package io.github.zhzhxtrrk.akka.extension.guice.actor;

import javax.inject.Inject;

import akka.actor.AbstractLoggingActor;
import akka.japi.pf.ReceiveBuilder;
import io.github.zhzhxtrrk.akka.extension.service.MessageService;

/**
 * MessageActor
 *
 * @author Zhe ZHANG
 * @version MessageActor, v1.0 2017/7/27 8:56
 */
public class MessageActor extends AbstractLoggingActor {
    private MessageService messageService;

    @Inject
    public MessageActor(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create().match(String.class, name -> {
            getSender().tell(messageService.message(name), getSelf());
        }).build();
    }
}
