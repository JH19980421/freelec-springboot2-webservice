package jh19980421.springboot.web;

import jh19980421.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController // 컨트롤러를 JSON을 반환하는 컨트롤러로 만든다. @ResponseBody를 한번에 사용할 수 있게 해준다.
public class HelloController {

    @GetMapping("/hello") // HTTP 메소드인 Get의 요청을 받을 수 있는 API를 만든다.
    public String hello(){
        return "hello"; // /hello로 요청이 오면 문자열 hello를 반환한다.
    }

    @GetMapping("/hello/dto") // @RequestParam은 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션이다.
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount){
        return new HelloResponseDto(name, amount); // 외부에서 name (@RequestParam("name")) 이란 이름으로 넘긴 파라미터를
                                                   // 메소드 파라미터 name(String name)에 저장한다.
    }
}
