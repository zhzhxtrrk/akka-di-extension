package io.github.zhzhxtrrk.akka.extension.guice;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.javadsl.TestKit;

import com.google.inject.Guice;
import com.google.inject.Injector;

import io.github.zhzhxtrrk.akka.extension.guice.actor.HelloActor;

/**
 * GuiceTest
 *
 * @author Zhe ZHANG
 * @version GuiceTest, v1.0 2017/7/27 11:13
 */
public class GuiceTest {

    private static ActorSystem system;

    @BeforeClass
    public static void beforeClass() {
        system = ActorSystem.create();
    }

    @AfterClass
    public static void afterClass() {
        TestKit.shutdownActorSystem(system);
    }

    @Test
    public void test() {
        Injector injector = Guice.createInjector(new Module());
        GuiceExtension.initialize(injector);

        new TestKit(system) {
            {
                this.childActorOf(Props.create(HelloActor.class));
                expectMsgEquals("Guys, please join me welcoming Steven");
            }
        };
    }
}
