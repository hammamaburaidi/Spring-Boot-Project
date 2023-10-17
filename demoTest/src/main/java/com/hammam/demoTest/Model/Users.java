package com.hammam.demoTest.Model;

import jakarta.persistence.*;

@Entity
@Table
public class Users {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private long id;
    private String name;
    private String username;
    private String email;
    private Long TCKN;

    public Users() {
    }

    public Users(long id,
                 String name,
                 String username,
                 String email,
                 Long TCKN) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.TCKN = TCKN;
    }

    public Users(String name, String username, String email, Long TCKN) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.TCKN = TCKN;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getTCKN() {
        return TCKN;
    }

    public void setTCKN(Long TCKN) {
        this.TCKN = TCKN;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", TCKN=" + TCKN +
                '}';
    }
}
