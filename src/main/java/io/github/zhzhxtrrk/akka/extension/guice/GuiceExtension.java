package io.github.zhzhxtrrk.akka.extension.guice;

import akka.actor.*;

import com.google.inject.Injector;

/**
 * GuiceExtension
 *
 * @author Zhe ZHANG
 * @version GuiceExtension, v1.0 2017/7/27 9:17
 */
public class GuiceExtension extends AbstractExtensionId<GuiceExtensionImpl> implements
                                                                           ExtensionIdProvider {

    private static final GuiceExtension INSTANCE = new GuiceExtension();
    private static Injector             injector;

    public static void initialize(Injector injector) {
        GuiceExtension.injector = injector;
    }

    public static ActorRef actorOf(AbstractActor.ActorContext context,
                                   Class<? extends Actor> actorClazz) {
        return context.actorOf(INSTANCE.get(context.getSystem()).props(actorClazz));
    }

    public static ActorRef actorOf(ActorSystem system, Class<? extends Actor> actorClazz) {
        return system.actorOf(INSTANCE.get(system).props(actorClazz));
    }

    @Override
    public GuiceExtensionImpl createExtension(ExtendedActorSystem system) {
        return new GuiceExtensionImpl(injector);
    }

    @Override
    public ExtensionId<? extends Extension> lookup() {
        return INSTANCE;
    }
}
