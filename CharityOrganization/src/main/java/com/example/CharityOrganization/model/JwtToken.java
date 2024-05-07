package com.example.CharityOrganization.model;
import jakarta.persistence.*;

@Entity
public class JwtToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String token;

    private String type;

    private String user_role;

    // @OneToOne
    // @JoinColumn(name = "user_id")
    private Integer user_id;


    public JwtToken() {
    }

    public JwtToken(String username, String token) {
        this.username = username;
        this.token = token;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUserRole(String role){
        this.user_role =role ;
    }

    public String getUserRole(){
        return user_role ;
    }

    public void setUser_id(Integer user_id){

        this.user_id = user_id ;
    }

    public Integer getUser_id(){
        return user_id ;
    }
}
