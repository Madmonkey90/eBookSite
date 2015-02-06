/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eLibrary.Domain;

import java.util.Set;
import org.apache.solr.analysis.LowerCaseFilterFactory;
import org.apache.solr.analysis.SnowballPorterFilterFactory;
import org.apache.solr.analysis.StandardTokenizerFactory;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;


/**
 *
 * @author thecarlson
 */
@Indexed
@AnalyzerDef(name = "customanalyzer",
  tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
  filters = {
    @TokenFilterDef(factory = LowerCaseFilterFactory.class),
    @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
      @Parameter(name = "language", value = "English")
    })
  })
@Analyzer(definition="customanalyzer")
public class EBook extends Item {
    @Field(analyze=Analyze.NO)
    private String ISBN;
    private int length;
    private Set<WishlistRecord> wishlistRecords;
    
    public EBook() {}
    
    public String getISBN() { return ISBN; }
    public void setISBN(String newISBN) { ISBN = newISBN; }
    
    public int getLength() { return length; }
    public void setLength(int newLength) { length = newLength; }
    
    public Set getWishlistRecords() { return wishlistRecords; }
    public void setWishlistRecords(Set<WishlistRecord> set) { wishlistRecords = set; }
    
    @Override
    public boolean equals(Object o)
    {
        EBook book = (EBook)o;
        if(book.getKey().equals(this.getKey())) { return true; }
        else{ return false; }
    }
    
    
}
