/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eLibrary.Domain;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Resolution;

/**
 *
 * @author thecarlson
 */

public abstract class Item implements Serializable {
    @DocumentId
    private String key;
    @Field
    private String title;
    @Field(analyze=Analyze.NO)
    @DateBridge(resolution = Resolution.DAY)
    private Date publishDate;
    @IndexedEmbedded( includePaths = {"name"} )
    private Author author;
    @Field
    private String publisher;
    private String imgSrc;
    private String sampleSrc; 
    private String description;
    private int checkouts;
    private double rating = 5.0;
    private Date addedDate;
    @Field
    private String genre;
    
    public Item() {}
    
    public String getKey() { return key; }
    public void setKey(String newKey) { key = newKey; }
    
    public String getTitle() { return title; }
    public void setTitle(String newTitle) { title = newTitle; }
    
    public String getPublisher() { return publisher; }
    public void setPublisher(String newPublisher) { publisher = newPublisher; }
    
    public Date getPublishDate() { return publishDate; }
    public void setPublishDate(Date newDate){ publishDate = newDate; }
    
    public Author getAuthor() { return author; }
    public void setAuthor(Author a) { author = a; }
    
    public String getImgSrc() { return imgSrc; }
    public void setImgSrc(String s) { imgSrc = s; }
    
    public double getRating() { return rating; }
    public void setRating(double r) { rating = r; }
    
    public int getCheckouts() { return checkouts; }
    public void setCheckouts(int x) { checkouts = x; }
    
    public Date getAddedDate() { return addedDate; } 
    public void setAddedDate(Date d) { addedDate = d; }

    public String getSampleSrc() { return sampleSrc; } 
    public void setSampleSrc(String newSampleSrc) { sampleSrc = newSampleSrc; }    

    public String getDescription() { return description; } 
    public void setDescription(String newdescription) { description = newdescription; }     
    
    public String getGenre() { return genre; }
    public void setGenre(String newGenre) { genre = newGenre; }    
    
    @Override
    public String toString() { return title; }


}
