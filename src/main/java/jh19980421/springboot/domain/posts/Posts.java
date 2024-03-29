package jh19980421.springboot.domain.posts;

import jh19980421.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // 클래스 내 모든 필드의 Getter 메소드를 자동생성한다.
@NoArgsConstructor // 기본 생성자 자동 추가 ex) public Posts() {};
@Entity // 테이블과 링크될 클래스임을 나타낸다. ex) SalesManager.java > sales_manager table
public class Posts extends BaseTimeEntity { // 실제 DB의 테이블과 매칭될 클래스. (Entity 클래스)
                     // Entity 클래스에서는 setter 메소드를 만들지 않는다.
                     // why? 해당 클래스의 인스턴스 값들이 언제 어디서 변해야 하는지 코드상으로
                     // 명확히 구분할 수 없기 때문이다.


    @Id // 해당 테이블의 PK 필드를 나타낸다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK의 생성규칙을 나타낸다.
                            // auto_increment가 된다.
    private Long id;

    @Column(length = 500, nullable = false) // 헤당 클래스의 필드는 모두 칼럼이 된다. (디폴트값을 정할 수 있다.)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

                // 해당 클래스의 빌더 패턴 클래스를 생성한다. 생성자 상단에 선언 시, 생성자에 포함된 필드만 빌더에 포함한다.
     @Builder   // 생성자의 역할을 한다.
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
         this.title = title;
         this.content = content;
    }
}

