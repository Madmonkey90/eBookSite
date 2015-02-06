package eLibrary.Domain;

public class EbookRating {
    private String userID;
    private String bookID;
    private float rating;
    
    public EbookRating(){}

    public String getUserID() { return userID;}
    public void setUserID(String userID) {this.userID = userID;}
    public String getBookID() { return bookID;}
    public void setBookID(String bookID) { this.bookID = bookID;}
    public float getRating() { return rating;}
    public void setRating(float rating) {this.rating = rating;}
    
    
}
