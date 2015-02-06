/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eLibrary.Domain;

import java.io.Serializable;

/**
 *
 * @author Brian
 */
public class UserRole implements Serializable{
    private String key;
    private User user;
    private String role;
    
    public void setKey(String x){key=x;}
    public String getKey() { return key;}
    
    public void setUser(User u) {user = u; }
    public User getUser() { return user; }
    
    public void setRole(String s){role=s;}
    public String getRole() { return role;}
}
