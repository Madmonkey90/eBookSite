package eLibrary.Domain;

import java.util.Date;
import java.util.UUID;

public class Ban {
    private UUID userKey;
    private UUID adminKey;
    private Date timeOfBan;
    private String reasonForBan;
    
    public UUID getUserKey() {return userKey; }
    public void setUserKey(UUID newUserKey){ userKey = newUserKey; }
    
    public UUID getAdminKey() { return adminKey;}
    public void setAdminKey(UUID newAdminKey){ adminKey = newAdminKey; }
    
    public Date getTimeOfBan() { return timeOfBan;}
    public void setAdminKey(Date newTimeOfBan){ timeOfBan = newTimeOfBan; }   
    
    public String getReasonForBan() { return reasonForBan; }
    public void setReasonForBan(String newReasonForBan) { reasonForBan = newReasonForBan; }
}
