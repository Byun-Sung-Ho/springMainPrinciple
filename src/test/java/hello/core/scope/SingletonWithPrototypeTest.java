package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

public class SingletonWithPrototypeTest {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean1.class);
        PrototypeBean1 prototypeBean1 = ac.getBean(PrototypeBean1.class);
        prototypeBean1.addCount();
        Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean1 prototypeBean2 = ac.getBean(PrototypeBean1.class);
        prototypeBean2.addCount();
        Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(1);

    }

    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean1.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();

        Assertions.assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        Assertions.assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    static class ClientBean{
//        private final PrototypeBean1 prototypeBean1;
//
//        @Autowired
//        private ObjectProvider<PrototypeBean1> prototypeBeanProvider;


        @Autowired
        private Provider<PrototypeBean1> prototypeBeanProvider;

        public int logic() {
//            PrototypeBean1 prototypeBean1 = prototypeBeanProvider.getObject();
            PrototypeBean1 prototypeBean1 = prototypeBeanProvider.get();
            prototypeBean1.addCount();
            return prototypeBean1.getCount();
        }
//        @Autowired
//        public ClientBean(PrototypeBean1 prototypeBean1) {
//            this.prototypeBean1 = prototypeBean1;
//        }
//
//        public int logic() {
//            prototypeBean1.addCount();
//            return prototypeBean1.getCount();
//        }
    }

    @Scope("prototype")
    static class PrototypeBean1 {
        private int count = 0;
        @PostConstruct
        public void init() {
            System.out.println("Prototype init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("Prototype Destroy");
        }

        public void addCount(){
            count++;
        }

        public int getCount() {
            return count;
        }
    }
}
