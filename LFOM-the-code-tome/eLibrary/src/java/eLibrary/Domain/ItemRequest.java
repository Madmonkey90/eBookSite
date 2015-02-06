package eLibrary.Domain;

import java.util.Date;
import java.util.UUID;

public class ItemRequest {
    private UUID requestKey;
    private UUID userKey;
    private String title;
    private String authorName;
    private String itemFormat;    
    private Date timeRequested;
    
    public ItemRequest() {}
    
    public UUID getRequestKey() { return requestKey;}
    public void setRequestKey(UUID requestKey) { this.requestKey = requestKey;}
    public UUID getUserKey() { return userKey;}
    public void setUserKey(UUID userKey) { this.userKey = userKey; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthorName() { return authorName; }
    public void setAuthorName(String authorName) { this.authorName = authorName;}
    public String getItemFormat() { return itemFormat; }
    public void setItemFormat(String itemFormat) { this.itemFormat = itemFormat;}
    public Date getTimeRequested() { return timeRequested; }
    public void setTimeRequested(Date timeRequested) { this.timeRequested = timeRequested; }
}
