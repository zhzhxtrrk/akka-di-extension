package io.github.zhzhxtrrk.akka.extension.spring;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.javadsl.TestKit;
import io.github.zhzhxtrrk.akka.extension.spring.actor.HelloActor;

/**
 * GuiceTest
 *
 * @author Zhe ZHANG
 * @version GuiceTest, v1.0 2017/7/27 11:13
 */
public class SpringTest {

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
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
            "io.github.zhzhxtrrk.akka.extension.spring",
            "io.github.zhzhxtrrk.akka.extension.service");
        SpringExtension.initialize(applicationContext);

        new TestKit(system) {
            {
                this.childActorOf(Props.create(HelloActor.class));
                expectMsgEquals("Guys, please join me welcoming Steven");
            }
        };
    }
}
