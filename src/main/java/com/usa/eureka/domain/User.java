package com.usa.eureka.domain;


import lombok.*;

//@Entity
//@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    // 추후 implements UserDetails 추가
    // TODO Setter DB 구축 후 삭제 예정
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String email;

    private String nickname;

    private long deposit;

    @Builder
    public User(String email, String nickname, long deposit) {
        this.email = email;
        this.nickname = nickname;
        this.deposit = deposit;
    }


    //TODO Kafka 먼저 구현 후 테스트 하고, Repository 나 Entity(Domain) 구현은 DB 구현할때 같이해도 늦지 않는다.
    //TODO 그래서 일단 대충 하고, service 함수 하나 구현하여 kafka 테스트 하고, 그때 기능에 대해서 고민하자.


}
