package com.study.SpringSecurity.domain.entity;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User {
    @Id // 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto_increment 기능
    private Long id;
    @Column(unique = true, nullable = false) // unique 설정, nullable: notnull이라 생각하면됨
    private String username;
    @Column(nullable = true)
    private String password;
    @Column(nullable = true)
    private String name;

    // fetch: 엔터티를 조인했을 때 연관된 데이터를 언제 가져올지 결정(EAGER - 당장, LAZY - 나중에 사용할 때)
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) // Role 엔티티랑 다대다 관계
    @JoinTable(
            name = "user_roles", // 테이블 이름
            joinColumns = @JoinColumn(name = "user_id"), // 조인할 키
            inverseJoinColumns = @JoinColumn(name = "role_id") // 외래키
    )
    private Set<Role> roles;
}
