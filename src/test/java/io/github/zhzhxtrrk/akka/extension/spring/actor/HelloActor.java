package io.github.zhzhxtrrk.akka.extension.spring.actor;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.japi.pf.ReceiveBuilder;
import io.gitlab.zhzhxtrrk.akka.extension.spring.SpringExtension;

/**
 * HelloActor
 *
 * @author Zhe ZHANG
 * @version HelloActor, v1.0 2017/7/27 0:04
 */
@Named
@Scope("prototype")
public class HelloActor extends AbstractLoggingActor {

    @Override
    public void preStart() throws Exception {
        ActorRef actorRef = SpringExtension.actorOf(getContext(), MessageActor.class);
        actorRef.tell("Steven", getSelf());
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create().match(String.class, message -> {
            getContext().getParent().tell(message, getSelf());
            getContext().stop(getSelf());
        }).build();
    }

}
