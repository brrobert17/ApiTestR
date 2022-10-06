package com.example.apitestr.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;

    @Column(nullable = false, unique = true, length =255, name = "username")
    private String username;

    @Column(nullable = false, length = 255, name = "password")
    private String password;

    @Column(nullable = false, unique = true, length = 255, name = "email")
    private String email;

    public User (String username, String password, String email) {
      this.username = username;
      this.password = password;
      this.email = email;
    }


}
