package jh19980421.springboot.domain.posts;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest // 별다른 설정없이 사용한다면 자동으로 H2 데이터베이스를 실행한다.
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

                  // 배포 전 전체 테스트를 수행할 때 데이터 침범을 막기 위해 사용한다.
     @AfterEach  // 여러 테스트가 동시에 수행되면 h2에 데이터가 그대로 남아 다음 테스트가 실패할 수 있다.
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        // 테이블 posts에 insert/update 쿼리를 실행한다. id값이 있다면 update, 없다면 insert
        postsRepository.save(Posts.builder().title(title).content(content).author("choijongho2@gmail.com").build());

        //when
        // 테이블 posts에 있는 모든 데이터를 조회한다.
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

    }

    @Test
    public void BaseTimeEntity_등록() {
         //given
        LocalDateTime now = LocalDateTime.of(2022,7,25,0,0,0);
        postsRepository.save(Posts.builder().title("title").content("content").author("author").build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>> createDate="+posts.getCreatedDate()+", modifiedDate="+posts.getModifiedDate());
        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
