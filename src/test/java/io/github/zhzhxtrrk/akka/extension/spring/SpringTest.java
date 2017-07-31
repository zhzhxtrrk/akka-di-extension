package io.github.zhzhxtrrk.akka.extension.spring;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

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
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { TestConfiguration.class })
public class SpringTest implements ApplicationContextAware {

    private static ActorSystem system;

    @BeforeClass
    public static void beforeClass() {
        system = ActorSystem.create();
    }

    @AfterClass
    public static void afterClass() {
        TestKit.shutdownActorSystem(system);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringExtension.initialize(applicationContext);
    }

    @Test
    public void test() {
        new TestKit(system) {
            {
                this.childActorOf(Props.create(HelloActor.class));
                expectMsgEquals("Guys, please join me welcoming Steven");
            }
        };
    }

}
