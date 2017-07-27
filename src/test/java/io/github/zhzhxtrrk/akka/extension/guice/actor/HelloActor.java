package io.github.zhzhxtrrk.akka.extension.guice.actor;

import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.japi.pf.ReceiveBuilder;
import io.github.zhzhxtrrk.akka.extension.guice.GuiceExtension;

/**
 * HelloActor
 *
 * @author Zhe ZHANG
 * @version HelloActor, v1.0 2017/7/27 0:04
 */
public class HelloActor extends AbstractLoggingActor {

    @Override
    public void preStart() throws Exception {
        ActorRef actorRef = GuiceExtension.actorOf(getContext(), MessageActor.class);
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
