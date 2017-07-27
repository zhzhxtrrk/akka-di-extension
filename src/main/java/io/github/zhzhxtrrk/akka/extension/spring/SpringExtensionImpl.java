package io.github.zhzhxtrrk.akka.extension.spring;

import org.springframework.beans.factory.BeanFactory;

import akka.actor.Actor;
import akka.actor.Extension;
import akka.actor.Props;

/**
 * SpringExtensionImpl
 *
 * @author Zhe ZHANG
 * @version SpringExtensionImpl, v1.0 2017/7/27 9:12
 */
class SpringExtensionImpl implements Extension {

    private final BeanFactory beanFactory;

    SpringExtensionImpl(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    <T extends Actor> Props props(Class<T> actorClazz) {
        return Props.create(actorClazz, () -> beanFactory.getBean(actorClazz));
    }

    <T extends Actor> Props props(String name, Class<T> actorClazz) {
        return Props.create(actorClazz, () -> beanFactory.getBean(name, actorClazz));
    }
}
