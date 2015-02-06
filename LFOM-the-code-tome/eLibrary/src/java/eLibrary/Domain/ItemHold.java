package eLibrary.Domain;

import java.util.Date;


public class ItemHold {
    private int key;
    private User user;
    private EBook ebook;
    private Date timeLeft;

    public ItemHold(){}
    
    public int getKey() { return key; }
    public void setKey(int i) { this.key = i; }
    public User getUser() { return user; }
    public void setUser(User u) { this.user = u;}
    public EBook getEbook() { return ebook; }
    public void setEbook(EBook e) { this.ebook = e; }
    public Date getTimeLeft() { return timeLeft; }
    public void setTimeLeft(Date d) { this.timeLeft = d; }
}
