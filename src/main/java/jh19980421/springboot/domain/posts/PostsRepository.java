package jh19980421.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> { // JpaRepository == Dao DB layer 접근자.
                                                                      // Entity 클래스, PK 타입을 상속하면
                                                                      // 자동적으로 CRUD 메소드가 생성된다.
                                                                      // Entity 클래스와 Repository는 항상 같은 위치에 있어야한다. (도메인 패키지에서 같이 관리한다.)


}
