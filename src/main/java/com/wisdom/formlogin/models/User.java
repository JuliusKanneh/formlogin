package com.wisdom.formlogin.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "users")
public class User {

    private static final long serialVersionUID = 1L;

    @Id
    private String username;
    private String password;
    @Column(name = "account_non_locked")
    private boolean accountNonLocked;
    private boolean active;
    private String roles;

//    Zero param constructor
    public User(){

    }

//    Parameterized constructor
    public User(String username, String password, boolean accountNonLocked, boolean active, String roles) {
        this.username = username;
        this.password = password;
        this.accountNonLocked = accountNonLocked;
        this.active = active;
        this.roles = roles;
    }

//    Getters and Setters (NB: No setters for read-only fields [static])
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }




    ////    GETTERS AND SETTERS OF ATTRIBUTES (Some are overridden from the UserDetails interface
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(() -> "USER");
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public boolean getAccountNonLocked() {
//        return accountNonLocked;
//    }
//
//    public void setAccountNonLocked(boolean accountNonLocked) {
//        this.accountNonLocked = accountNonLocked;
//    }
//
//    @Override
//    public boolean isAccountNonExpired(){
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked(){
//        return accountNonLocked;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired(){
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled(){
//        return true;
//    }

}
