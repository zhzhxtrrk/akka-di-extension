Provide AKKA extensions to integrate AKKA application with DI containers(Guice, Spring).

#### Usage:

initialize the extension
```java
ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
            "io.github.zhzhxtrrk.akka.extension.spring",
            "io.github.zhzhxtrrk.akka.extension.service");
SpringExtension.initialize(applicationContext);
```

code your actors, declare them as a Spring managed bean
```java
@Named
@Scope("prototype")
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
```

create actors from Spring container
```java
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
```

For Guice example, please go to [https://github.com/zhzhxtrrk/akka-di-extension/tree/master/src/test/java/io/github/zhzhxtrrk/akka/extension/guice](https://github.com/zhzhxtrrk/akka-di-extension/tree/master/src/test/java/io/github/zhzhxtrrk/akka/extension/guice)

That is it, simple & powerful.