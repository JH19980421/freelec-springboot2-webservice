package jh19980421.springboot.web;

import jh19980421.springboot.service.posts.PostsService;
import jh19980421.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) { // 앞의 경로와 뒤의 파일 확장자는 자동으로 지정된다.
                            // 앞의 경로 : src/main/resources/templates , 파일 확장자 : .mustache
                            // Model를 통해 서버 템플릿 엔진에서 사용할 수 있는 객체를 지정할 수 있다.
                            // postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달한다.

        model.addAttribute("posts", postsService.findAllDesc());
        return "index";     // src/main/resources/templates/index.mustache
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
