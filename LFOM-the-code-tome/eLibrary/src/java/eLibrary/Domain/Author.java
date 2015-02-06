package eLibrary.Domain;

import java.util.Set;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.DocumentId;

@Analyzer(definition="customanalyzer")
public class Author {
    @DocumentId
    private String key;
    @Field
    private String name;
    @ContainedIn
    private Set<EBook> ebooks;
    
    public Author(){}
    
    public String getKey() { return key; }
    public void setKey(String newKey) { key = newKey; }
    
    public String getName() { return name;}
    public void setName( String newName) { name = newName;}
    
    public Set<EBook> getEbooks() { return ebooks; }
    public void setEbooks(Set<EBook> b) { ebooks = b; }
    
    @Override
    public String toString() { return this.name; }
}