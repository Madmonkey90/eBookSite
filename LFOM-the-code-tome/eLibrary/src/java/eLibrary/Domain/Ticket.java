/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eLibrary.Domain;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author Brian
 */
public class Ticket implements Serializable{
    
    private int key;
    private Timestamp timeStamp;
    private String userEmail;
    private String userName;
    private String description;
    private boolean resolved;
    
    public int getKey() { return key; }
    public void setKey(int newKey) { key = newKey; }
    
    public Timestamp getTimeStamp() { return timeStamp; }
    public void setTimeStamp(Timestamp newStamp) { timeStamp = newStamp; }
    
    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String newEmail) { userEmail = newEmail; }
    
    public String getUserName() { return userName; }
    public void setUserName(String newName) { userName = newName; }
    
    public String getDescription() { return description; }
    public void setDescription(String newDesc) { description = newDesc; }
    
    public boolean isResolved() { return resolved; }
    public void setResolved(boolean b) { resolved = b; }
    
    
    
}
