package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//implements InitializingBean, DisposableBean
public class NetworkClient {

    private String url;

    public NetworkClient(){
        System.out.println("생성자 호출 url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + "message = " + message);
    }

    public void disConnect() {
        System.out.println("close: " + url);
    }

//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        connect();
//        call("초기화 연결 메세지");
//
//    }
//
//    @Override
//    public void destroy() throws Exception {
//        disConnect();
//    }

    @PostConstruct
    public void init() {
        connect();
        call("초기화 연결 메세지");

    }

    @PreDestroy
    public void close() {
        disConnect();
    }
}
