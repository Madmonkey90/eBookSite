/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eLibrary.Domain;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Quack
 */
public class CheckoutRecord implements Serializable{
    private int key;
    private User user;
    private EBook ebook;
    private Date date;
    private boolean downloaded;
    private String format;
    
    public void setKey(int k){key=k;}
    public int getKey() { return key;}
    
    public void setUser(User u) {user = u; }
    public User getUser() { return user; }
    
    public void setEbook(EBook e){ebook=e;}
    public EBook getEbook() { return ebook;}
    
    public Date getDate(){return date;}
    public void setDate(Date d){date=d;}
    
    public boolean getDownloaded(){ return downloaded; }
    public void setDownloaded(boolean b){ downloaded = b; }
    
    public String getFormat(){ return format; }
    public void setFormat(String s){ format = s; }
}
