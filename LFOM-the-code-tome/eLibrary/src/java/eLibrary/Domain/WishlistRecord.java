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
public class WishlistRecord implements Serializable{
    
    private String key;
    private EBook bookId;
    private User userId;
    
    public String getKey() { return key; }
    public void setKey(String k) { key = k; }
    
    public EBook getBookId() { return bookId; }
    public void setBookId(EBook id) { bookId = id; }
    
    public User getUserId() { return userId; }
    public void setUserId(User id) { userId = id; }
    
}
