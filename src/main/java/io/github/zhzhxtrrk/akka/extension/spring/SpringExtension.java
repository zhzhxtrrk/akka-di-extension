package io.github.zhzhxtrrk.akka.extension.spring;

import org.springframework.beans.factory.BeanFactory;

import akka.actor.*;

/**
 * GuiceExtension
 *
 * @author Zhe ZHANG
 * @version GuiceExtension, v1.0 2017/7/27 9:17
 */
public class SpringExtension extends AbstractExtensionId<SpringExtensionImpl> implements
                                                                             ExtensionIdProvider {

    private static final SpringExtension INSTANCE = new SpringExtension();
    private static BeanFactory           beanFactory;

    public static void initialize(BeanFactory beanFactory) {
        SpringExtension.beanFactory = beanFactory;
    }

    public static ActorRef actorOf(AbstractActor.ActorContext context,
                                   Class<? extends Actor> actorClazz) {
        return context.actorOf(INSTANCE.get(context.getSystem()).props(actorClazz));
    }

    public static ActorRef actorOf(AbstractActor.ActorContext context, String beanName,
                                   Class<? extends Actor> actorClazz) {
        return context.actorOf(INSTANCE.get(context.getSystem()).props(beanName, actorClazz));
    }

    @Override
    public SpringExtensionImpl createExtension(ExtendedActorSystem system) {
        return new SpringExtensionImpl(beanFactory);
    }

    @Override
    public ExtensionId<? extends Extension> lookup() {
        return INSTANCE;
    }
}
