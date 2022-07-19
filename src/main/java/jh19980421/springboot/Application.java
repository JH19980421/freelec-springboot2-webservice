package jh19980421.springboot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

                         // 스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정한다.
                         // @SpringBootApplication이 있는 위치부터 설정을 읽어가기 때문에
 @SpringBootApplication  // 이 클래스는 항상 프로젝트의 최상단에 위치해야한다.
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args); // 내장 WAS 실행
                                                // 항상 서버에 톰캣을 설치할 필요가 없고 스프링 부트로 만들어진 Jar파일 실행
    }
}
