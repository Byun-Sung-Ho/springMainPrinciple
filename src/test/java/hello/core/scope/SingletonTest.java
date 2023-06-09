package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonTest {

    @Test
    void singletonTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);

        SingletonBean s1 = ac.getBean(SingletonBean.class);
        SingletonBean s2 = ac.getBean(SingletonBean.class);
        System.out.println("s1 = " + s1);
        System.out.println("s2 = " + s2);
        Assertions.assertThat(s1).isSameAs(s2);

        ac.close();
    }


    @Scope("singleton")
    static class SingletonBean {
        @PostConstruct
        public void init() {
            System.out.println("Singleton init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("Singleton Destroy");
        }

    }
}
