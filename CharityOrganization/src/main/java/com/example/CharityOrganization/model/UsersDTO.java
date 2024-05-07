package com.example.CharityOrganization.model;



public class UsersDTO {

    private int id;
    private String decryptedUsername;
    private String decryptedEmail;
    private String decryptedRole ;



    public void setRole(String decryptedRole){
        this.decryptedRole = decryptedRole;
    }
    public String getRole(){
        return decryptedRole ;
    }
    public int getId(){
        return  id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return decryptedUsername;
    }
    public void setUsername(String username) {
        this.decryptedUsername = username;
    }

    public void setEmail(String email ){
        this.decryptedEmail=email;
    }

    public String getEmail(){
        return decryptedEmail;
    }

}
