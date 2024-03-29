package jh19980421.springboot;
import jh19980421.springboot.web.HelloController;
import org.junit.jupiter.api.Test; // 버전 업그레이드에 따라 org.junit.Test에서 변경됨
import org.junit.jupiter.api.extension.ExtendWith; // 버전 업그레이드에 따라 org.junit.runner.RunWith에서 변경됨
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension; // 버전 업그레이드에 따라 org.springframework.test.context.junit4
// .SpringRunner에서 변경됨
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

//@RunWith > ExtenWith로 변경 ,, SpringRunner > SpringExtension으로 변경
@ExtendWith(SpringExtension.class) // 테스트를 진행할때 JUnit에 내장된 실행자 외에 다른 실행자를 실행한다(연결자). (SpringExtension 실행)
@WebMvcTest(controllers = HelloController.class) // Web에 집중할 수 있는 어노테이션이다. @Controller, @ControllerAdvice 사용가능.
public class HelloControllerTest {

    @Autowired // 스프링이 관리하는 빈(Bean)을 주입받는다.
    private MockMvc mvc; // 웹 API를 테스트할 때 사용한다.  스프링 MVC 테스트의 시작점이다. MockMvc 클래스를 통해 HTTP GET,POST 등에
                         // 대한 API 테스트를 시작할 수 있다.

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")).andExpect(status().isOk()).andExpect(content().string(hello));
        // perform(get("/hello"))를 통해 /hello 주소로 HTTP GET 요청을 한다.
        // 체이닝이 지원되어 여러 검증 기능을 이어서 선언할 수 있다.
        // .andExpect(status().isOk())를 통해 perform()의 결과를 검증한다.
        // HTTP Header의 Status를 검증한다.
        // 흔히 알고 있는 200,404,500 등의 상태를 검증한다.
        // 이 코드에서는 OK 즉, 200을 검증한다.
        // .andExpect(content().string(hello))를 통해 perform()의 결과를 검증한다.
        // 응답 본문의 내용을 검증한다.
        // Controller에서 "hello"를 리턴하기 때문에 이 값이 맞는지 검증한다.
    }

    @Test
    public void helloDto가_리턴된다() throws  Exception {

        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto").param("name", name).param("amount", String.valueOf(amount)))
                .andExpect(status().isOk()).andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));

        // param은 API 테스트할 때 사용될 요청 파라미터를 설정한다. String 값만 허용한다. (다른 기본 타입이면 형변환 필요)
        // jsonPath는 JSON 응답값을 필드별로 검증할 수 있는 메소드이다.
        // $를 기준으로 필드명을 명시한다. name과 amount를 검증하므로 $.name, $.amount로 검증한다.
    }
}
