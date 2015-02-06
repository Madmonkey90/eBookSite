package eLibrary.Handlers;

import eLibrary.Domain.Author;
import eLibrary.Domain.CheckoutRecord;
import eLibrary.Domain.EBook;


import eLibrary.Domain.ItemHold;


import eLibrary.Domain.Item;

import eLibrary.Domain.User;

import eLibrary.Domain.WishlistRecord;

import eLibrary.Repos.ItemRepo;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import eLibrary.Repos.MetricsRepo;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ItemHandler {
    private ItemRepo itemRepo;
    private MetricsRepo metricsRepo;
    
    public ItemHandler(){}
    
    public void setItemRepo(ItemRepo repo) { itemRepo = repo; }
    public ItemRepo getItemRepo() { return itemRepo; }
    
    public void setMetricsRepo(MetricsRepo repo) { metricsRepo = repo; }
    public MetricsRepo getMetricsRepo() { return metricsRepo; }
    
    public List<EBook> search(String searchString, boolean toTime)
    {
        long startTime = System.nanoTime();
        List<EBook> items = itemRepo.search(searchString);
        long endTime = System.nanoTime();
        long searchTime = (endTime - startTime);
        long searchTimeInMilSeconds = TimeUnit.MILLISECONDS.convert(searchTime, TimeUnit.NANOSECONDS);
        if(toTime){
            try{
            metricsRepo.addSearchRecord(searchString, searchTimeInMilSeconds);
        }
            catch(Exception e) { e.printStackTrace(); }
        }
        return items;
    }

    public List<EBook> advancedSearch(String searchString, boolean toTime)
    {
        long startTime = System.nanoTime();
        List<EBook> items = itemRepo.search(searchString);
        long endTime = System.nanoTime();
        long searchTime = (endTime - startTime);
        long searchTimeInMilSeconds = TimeUnit.MILLISECONDS.convert(searchTime, TimeUnit.NANOSECONDS);
        if(toTime){
            try{
            metricsRepo.addSearchRecord(searchString, searchTimeInMilSeconds);
        }
            catch(Exception e) { e.printStackTrace(); }
        }
        return items;
    }    
    
    public List<EBook> browse(String genre)
    {
        List<EBook> items = itemRepo.search(genre);
        return items;
    }
    
    public EBook findById(String id)
    {
        EBook book = itemRepo.findById(id);
        return book;
    }
    
    public Author findAuthorById(String id)
    {
        Author author = itemRepo.findAuthorById(id);
        return author;
    }
    
    public Author findAuthorByName(String name)
    {
       Author author = itemRepo.findAuthorByName(name);
       return author; 
    }
    
    public List<EBook> getMostPopular()
    {
        List<EBook> books = itemRepo.getMostPopular();
        return books;
    }
    
    public List<EBook> getNewestAdditions()
    {
        List<EBook> books = itemRepo.getNewestAdditions();
        return books;
    }

    public List<EBook> getSimilar(String id)
    {
        List<EBook> books = itemRepo.getSimilar(id);
        return books;
    }
       
    public int checkout(String id, String userId){
        EBook ebook = itemRepo.findById(id);
        ebook.setCheckouts(ebook.getCheckouts()+1);
        return itemRepo.saveCheckoutRecord(ebook, userId);
    }
    
    public Set<CheckoutRecord> updateCheckoutList(User u) throws IOException{
        List<CheckoutRecord> list = itemRepo.getCheckoutList(u);
        Set<CheckoutRecord> list2 = new HashSet<CheckoutRecord>(0);
        for(CheckoutRecord e : list){
            Calendar cal = Calendar.getInstance();
            cal.setTime(e.getDate());
            cal.add(Calendar.DATE, 14);
            Date currDate = new Date();
            if(currDate.after(cal.getTime())&&e.getDownloaded()){
                URL url = new URL("http://69.121.220.130:8084/ePublisher/download/return?"
                    + "id="+e.getEbook().getKey()+"&format="+e.getFormat());
                url.openStream();
                returnBook(e.getEbook(), e.getUser());
            }
            else list2.add(e);
        }
        u.setCheckedOut(list2);
        return list2;
    }
    
    public List<EBook> getRecommended(String userId)
    {
        return itemRepo.getRecommended(userId);
    }
    
    public int updateEBook(EBook book){
        return itemRepo.updateEBook(book);
    }
    
    public int deleteEBook(String key){
        return itemRepo.deleteItem(key);
	}
    public int returnBook(EBook book, User currentUser) {
        return itemRepo.removeCheckoutRecord(book, currentUser);
    }
    
    public int addEBook(EBook book){
        return itemRepo.addEBook(book);
		}
    public void setFormat(EBook book, User user, String format){
        itemRepo.setFormat(book, user, format);
    }

    public void rateEbook(double rating, String bookId,String userKey) {
        itemRepo.rateEbook(rating,bookId,userKey);
    }


    public Set<ItemHold> updateHoldList(User currentUser) {
        List<ItemHold> list = itemRepo.getHoldList(currentUser);
        Set<ItemHold> list2 = new HashSet<ItemHold>(0);
        for(ItemHold e : list){
            Date currDate = new Date();
            if(currDate.after(e.getTimeLeft())){
                checkout(e.getEbook().getKey(), e.getUser().getKey());
            }
            else list2.add(e);
        }
        currentUser.setHeldItems(list2);
        return list2;
    }

    

    public void holdItem(EBook book, User u, Date date) throws ParseException {
        ItemHold hold = new ItemHold();
        hold.setUser(u);
        hold.setEbook(book);
        hold.setTimeLeft(date);
        if(itemRepo.holdEbook(hold)){
            Set<CheckoutRecord> cr = u.getCheckedOut();
            for(CheckoutRecord c : cr){
                if(c.getEbook().getKey().equals(book.getKey())){
                    returnBook(book,u);
                }
            }
            u.getHeldItems().add(hold);
        }
    }

    public void removeHold(User u, EBook book) {
        itemRepo.removeHold(u, book);
    }

    public List getAllGenres()
    {
        return itemRepo.getAllGenres();

    }    
 public double getEBookRating(String id)
    {
        return itemRepo.getRating(id);
    }
    
    public int addBookToWishList(String bookId, String userId)
    {
        return itemRepo.addBookToWishList(bookId, userId);
    }
    
    public boolean isOnUsersWishList(String bookId, String userId)
    {
        return itemRepo.isOnUsersWishlist(bookId, userId);
    }
    
    public List<WishlistRecord> getUserWishlist(String id)
    {
        return itemRepo.getUserWishlist(id);
    }
    
    public int removeFromWishlist(String bookId, String userId)
    {
        return itemRepo.removeFromWishlist(bookId, userId);
    }
    
    public List<EBook> sortResults(List<EBook> results, String sortCritera)
    {
        switch(sortCritera)
        {
            case "title":
                Collections.sort(results, new Comparator<EBook>() {
        @Override
        public int compare(EBook item1, EBook item2){
            String itemTitle1 = item1.getTitle();
            String itemTitle2 = item2.getTitle();
            
            return itemTitle1.compareTo(itemTitle2);
            
        }
    }
                );
                return results;
            case "author":
                Collections.sort(results, new Comparator<EBook>() {
        @Override
        public int compare(EBook item1, EBook item2){
            String itemAuthor1 = item1.getAuthor().getName();
            String itemAuthor2 = item2.getAuthor().getName();
            
            return itemAuthor1.compareTo(itemAuthor2);
            
        }
    });
                return results;
            case "publishDate":
                Collections.sort(results, new Comparator<EBook>() {
        @Override
        public int compare(EBook item1, EBook item2){
            Date date1 = item1.getPublishDate();
            Date date2 = item2.getPublishDate();
            
            return date1.compareTo(date2);
            
        }
    }
                );
                return results;  
            case "newest":
                Collections.sort(results, new Comparator<EBook>() {
        @Override
        public int compare(EBook item1,EBook item2){
            Date date1 = item1.getAddedDate();
            Date date2 = item2.getAddedDate();
            
            return date1.compareTo(date2);
            
        }
    });
                return results;
            case "mostPopular":
                Collections.sort(results, new Comparator<EBook>() {
        @Override
        public int compare(EBook item1, EBook item2){
            int checkouts1 = item1.getCheckouts();
            int checkouts2 = item2.getCheckouts();
            
            return checkouts1 - checkouts2;
            
        }
    });
                return results;
            default: return results;
        }
    }
            

}
