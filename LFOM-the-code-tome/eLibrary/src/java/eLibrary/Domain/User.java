/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eLibrary.Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.context.annotation.Scope;


/**
 *
 * @author Quack
 */
public class User implements Serializable{
    private String lastName;
    private String firstName;
    private String email;
    private String password;
    private String key;
    private boolean enabled;
    private Set<UserRole> userRole;
    private List<EBook> cart = new ArrayList<EBook>(0);
    private Set<CheckoutRecord> checkedOut = new HashSet<CheckoutRecord>(0);

    private Set<ItemHold> heldItems = new HashSet<ItemHold>(0);

    private Set<WishlistRecord> wishlistRecords;

    //private List<Item> readingList;
    //private List<Item> ratedList;
    
    public User(){}
    
    public String getLastName(){return lastName;}
    public String getFirstName(){return firstName;}
    public String getEmail(){return email;}
    public String getPassword(){return password;}
    public String getKey(){return key;}
    public Set getUserRole() { return userRole; }
    public boolean isEnabled() { return enabled; }
    public List<EBook> getCart(){ return cart; }
    public Set<CheckoutRecord> getCheckedOut(){ return checkedOut; }

    public Set<ItemHold> getHeldItems(){ return heldItems; }

    public Set getWishlistRecords() { return wishlistRecords; }

    //public List<Item> getReadingList(){return readingList;}
    //public List<Item> getRatedList(){return ratedList;}
    
    public void setLastName(String s){lastName=s;}
    public void setFirstName(String s){firstName=s;}
    public void setEmail(String s){email=s;}
    public void setPassword(String s){password=s;}
    public void setKey(String key) { this.key = key; }
    public void setUserRole(Set s) { userRole = s; }
    public void setEnabled(boolean b) { enabled = b; }
    public void setCart(List<EBook> c){ cart = c; }
    public void setCheckedOut(Set<CheckoutRecord> c){ checkedOut = c; }

    public void setHeldItems(Set<ItemHold> h){ heldItems = h; }

    public void setWishlistRecords(Set<WishlistRecord> set) { wishlistRecords = set; }

    //public void addToReadingList(Item i){readingList.add(i);}
    //public void addToRatedList(Item i){ratedList.add(i);}

    
}
