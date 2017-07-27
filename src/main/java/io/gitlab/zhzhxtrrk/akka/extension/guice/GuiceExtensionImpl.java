package io.gitlab.zhzhxtrrk.akka.extension.guice;

import akka.actor.Actor;
import akka.actor.Extension;
import akka.actor.Props;

import com.google.inject.Injector;

/**
 * GuiceExtensionImpl
 *
 * @author Zhe ZHANG
 * @version GuiceExtensionImpl, v1.0 2017/7/27 9:12
 */
class GuiceExtensionImpl implements Extension {

    private final Injector injector;

    GuiceExtensionImpl(Injector injector) {
        this.injector = injector;
    }

    <T extends Actor> Props props(Class<T> actorClazz) {
        return Props.create(actorClazz, () -> injector.getInstance(actorClazz));
    }
}
