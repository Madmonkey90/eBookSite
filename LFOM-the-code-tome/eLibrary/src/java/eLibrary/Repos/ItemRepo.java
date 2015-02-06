package eLibrary.Repos;

import eLibrary.Domain.Author;
import eLibrary.Domain.CheckoutRecord;
import eLibrary.Domain.EBook;


import eLibrary.Domain.User;
import eLibrary.Domain.EbookRating;

import eLibrary.Domain.ItemHold;


import eLibrary.Domain.User;

import eLibrary.Domain.WishlistRecord;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.apache.lucene.search.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.SearchFactory;
import org.hibernate.search.query.dsl.QueryBuilder;

import org.springframework.transaction.annotation.Transactional;



public class ItemRepo {
    private SessionFactory sessionFactory;
    
    public ItemRepo(SessionFactory f) { sessionFactory = f;} 
    
    @Transactional
    public List<EBook> search(String searchString)
    {
   
        Session session = sessionFactory.getCurrentSession();
        FullTextSession fullTextSession = Search.getFullTextSession(session);
        SearchFactory searchFactory = fullTextSession.getSearchFactory();
        QueryBuilder qb = searchFactory.buildQueryBuilder().forEntity(EBook.class).get();
        Query q = qb.keyword().onFields("title", "author.name","ISBN","genre","publisher").matching(searchString).createQuery();
        org.hibernate.Query hq = fullTextSession.createFullTextQuery(q, EBook.class);
        List<EBook> results = (List<EBook>)hq.list();
        return results;
    }

    @Transactional
    public List<EBook> browse(String searchString)
    {
        //Complete the same as search except only the genre field is checked
        Session session = sessionFactory.getCurrentSession();
        FullTextSession fullTextSession = Search.getFullTextSession(session);
        SearchFactory searchFactory = fullTextSession.getSearchFactory();
        QueryBuilder qb = searchFactory.buildQueryBuilder().forEntity(EBook.class).get();
        Query q = qb.keyword().onFields("genre").matching(searchString).createQuery();
        org.hibernate.Query hq = fullTextSession.createFullTextQuery(q, EBook.class);
        List<EBook> results = (List<EBook>)hq.list();
        return results;
    }    

    @Transactional
    public List<EBook> advancedSearch(String searchString)
    {
   
        Session session = sessionFactory.getCurrentSession();
        FullTextSession fullTextSession = Search.getFullTextSession(session);
        SearchFactory searchFactory = fullTextSession.getSearchFactory();
        QueryBuilder qb = searchFactory.buildQueryBuilder().forEntity(EBook.class).get();
        Query q = qb.keyword().onFields("title", "author.name","ISBN","genre","publisher").matching(searchString).createQuery();
        org.hibernate.Query hq = fullTextSession.createFullTextQuery(q, EBook.class);
        List<EBook> results = (List<EBook>)hq.list();
        return results;
    }     
    
    @Transactional
    public EBook findById(String id)
    {
        Session session = sessionFactory.getCurrentSession();
        EBook book = (EBook)session.get(EBook.class, id);
        return book;
    }
    
    @Transactional 
    public Author findAuthorById(String id)
    {
        Session session = sessionFactory.getCurrentSession();
        Author author = (Author)session.get(Author.class, id);
        return author;
    }
    
    @Transactional 
    public Author findAuthorByName(String name)
    {
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Query q = session.createQuery("from Author where name = ?")
                .setString(0, name);
        List result = q.list();
        if(result.isEmpty())return null;
        Author a = (Author) result.get(0);
        return a;
    }
    
    @Transactional
    public List<EBook> getMostPopular()
    {
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Query q = session.createQuery("FROM EBook "
                                                   + "ORDER BY checkouts DESC");

        q.setMaxResults(18);

        List<EBook> books = (List<EBook>)q.list();
        return books;
    }
    
    @Transactional
    public List<EBook> getNewestAdditions()
    {
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Query q = session.createQuery("FROM EBook "
                                                   + "ORDER BY addedDate DESC");

        q.setMaxResults(18);

        List<EBook> books = (List<EBook>)q.list();
        return books;
    }

    @Transactional
    public List getAllGenres()
    {
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Query q = session.createSQLQuery("SELECT DISTINCT genre From  ebooks ");      
        List <String> categories = q.list();
        return categories;
    }    
    
    @Transactional
    public List<EBook> getSimilar(String id)
    {
        Session session = sessionFactory.getCurrentSession();
        EBook bookToBaseRecommendOn = findById(id);
        String sameGenre = bookToBaseRecommendOn.getGenre();
        Author sameAuthor = bookToBaseRecommendOn.getAuthor();
        //Currently recommend books based on genre and author 
        //In the future this should probably remove books that the user has already checked out
        org.hibernate.Query q = session.createQuery("SELECT DISTINCT ebook FROM EBook as ebook "
                                                   + "where (ebook.author.key = ? "
                                                   + "or ebook.genre = ?) "
                                                   + "and ebook.key != ?"
                                                   )
                .setString(0, sameAuthor.getKey() )
                .setString(1, sameGenre)
                .setString(2, id)
                ;
        q.setMaxResults(10);
        List<EBook> books = (List<EBook>)q.list();
        return books;
    }
    
    @Transactional
    public List<EBook> getRecommended(String userId){
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Query q = session.createSQLQuery("SELECT * FROM ebookrating where USER_ID = ? ORDER BY RATING DESC")
                                       .setParameter(0, userId);
        q.setMaxResults(20);
        List topRatedBooksForUser = q.list();
        List<String> topAuthors = new ArrayList();
        List<String> topGenres = new ArrayList();
        List<String> topBookIds = new ArrayList();
        for( Object obj : topRatedBooksForUser){
            Object[] objArr = (Object[])obj;
            String bookId = (String)objArr[0];
            EBook currentBook = (EBook)session.load(EBook.class, bookId);
            topBookIds.add(bookId);
            if(!topAuthors.contains(currentBook.getAuthor().getName()))
            {
                topAuthors.add(currentBook.getAuthor().getName());
            }
            if(!topGenres.contains(currentBook.getGenre()))
            {
                topGenres.add(currentBook.getGenre());
            }
        }
        String author1, author2, author3;
        String genre1, genre2, genre3;
        if(topAuthors.size() > 0) { author1 = topAuthors.get(0); } else { author1 = ""; }
        if((topAuthors.size() > 1)) { author2 = topAuthors.get(1); } else { author2 = ""; }
        if((topAuthors.size() > 2)) { author3 = topAuthors.get(2); } else { author3 = ""; }
        if((topGenres.size() > 0)) { genre1 = topGenres.get(0); } else { genre1 = ""; }
        if((topGenres.size() > 1)) { genre2 = topGenres.get(1); } else { genre2 = ""; }
        if((topGenres.size() > 2)) { genre3 = topGenres.get(2); } else { genre3 = ""; }
        org.hibernate.Query q1 = session.createSQLQuery("SELECT DISTINCT EBOOK_ID FROM ebooks WHERE AUTHOR_ID = ? OR AUTHOR_ID = ? OR AUTHOR_ID = ? OR GENRE = ? OR GENRE = ? OR GENRE = ?")
                                    .setParameter(0, author1)
                                    .setParameter(1, author2)
                                    .setParameter(2, author3)
                                    .setParameter(3, genre1)
                                    .setParameter(4, genre2)
                                    .setParameter(5, genre3);
        q1.setMaxResults(15);
        List<String> list = (List<String>)q1.list();
        List<String> booksToRemove = new ArrayList();
        for(String bookId: list)
        {
            for(String id : topBookIds)
            {
                if(bookId.equals(id))
                {
                    booksToRemove.add(id);
                }
            }
        }
        list.removeAll(booksToRemove);
        List<EBook> bookList = new ArrayList();
        for(String bookId: list)
        {
            EBook currentBook = (EBook)session.load(EBook.class, bookId);
            bookList.add(currentBook);
        }
        return bookList;
    }
    
    @Transactional
    public int saveCheckoutRecord(EBook ebook, String userId) {
        Session session = sessionFactory.getCurrentSession();
        session.update(ebook);
        Date d = new Date();
        int next = ((Long)(session.createQuery("select count(*) from CheckoutRecord").uniqueResult())).intValue();
        org.hibernate.Query q = session.createSQLQuery("insert into checked_out values(?,?,?,?,?,?)")
                .setParameter(0, next+1)
                .setParameter(1, userId)
                .setParameter(2, ebook.getKey())
                .setParameter(3, new Timestamp(d.getTime()))
                .setParameter(4, false)
                .setParameter(5, null);
        return q.executeUpdate();
    }
    @Transactional
   public List<CheckoutRecord> getCheckoutList(User u) {
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Query q = session.createQuery("from CheckoutRecord where user = ?")
                .setParameter(0, u);
        return q.list();
    }
   
   @Transactional
   public int updateEBook(EBook book)
   {
       Session session = sessionFactory.getCurrentSession();
       org.hibernate.Query q = session.createSQLQuery("UPDATE ebooks SET TITLE = ?, PUBLISHDATE = ?, PUBLISHER = ?, IMGSRC = ?, DESCRIPTION = ?, CHECKOUTS = ?, ADDEDDATE = ?, ISBN = ?, LENGTH = ?, GENRE = ?, RATING = ? WHERE EBOOK_ID = ?")
                .setParameter(0, book.getTitle())
                .setParameter(1, book.getPublishDate())
                .setParameter(2, book.getPublisher())
                .setParameter(3, book.getImgSrc())
                .setParameter(4, book.getDescription())
                .setParameter(5, book.getCheckouts())
                .setParameter(6, book.getAddedDate())
                .setParameter(7, book.getISBN())
                .setParameter(8, book.getLength())
                .setParameter(9, book.getGenre())
                .setParameter(10, book.getRating())
                .setParameter(11, book.getKey());
       if(q.executeUpdate() == 1)
       {
           Author a = book.getAuthor();
           org.hibernate.Query q1 = session.createSQLQuery("UPDATE authors SET NAME = ? WHERE AUTHOR_ID = ?")
                        .setParameter(0,a.getName())
                        .setParameter(1,a.getKey());
           if(q1.executeUpdate() == 1)
           {
               FullTextSession fullTextSession = Search.getFullTextSession(session);
               Object bookToIndex = fullTextSession.load( EBook.class, book.getKey() );
               fullTextSession.index(bookToIndex);
               return 1;
           }else{ return 0; }
       }else { return 0; }
   }
   
   @Transactional
   public int deleteItem(String key){
       Session session = sessionFactory.getCurrentSession();
       org.hibernate.Query q = session.createSQLQuery("DELETE FROM ebooks where EBOOK_ID = ?")
                                      .setParameter(0, key);
       if(q.executeUpdate() == 1)
       {
          FullTextSession fullTextSession = Search.getFullTextSession(session);
          fullTextSession.purge( EBook.class, key );
          return 1;
       }else{ return 0; }
   }
   
   @Transactional
   public int addEBook(EBook book){
       Session session = sessionFactory.getCurrentSession();
        String bookKey = UUID.randomUUID().toString();
        String authorKey;
        boolean newAuthor = false;
        if(book.getAuthor().getKey() == null)
        {
            authorKey = UUID.randomUUID().toString();
            newAuthor = true;
        }
        else{ authorKey = book.getAuthor().getKey(); }
        if(newAuthor)
        {
            org.hibernate.Query q1 = session.createSQLQuery("insert into authors values(?,?)")
                                     .setParameter(0,authorKey)
                                     .setParameter(1,book.getAuthor().getName());
            if(q1.executeUpdate() !=1 ) { return 0; }
        }
        org.hibernate.Query q = session.createSQLQuery("insert into ebooks values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)")
                .setParameter(0, bookKey)
                .setParameter(1, book.getTitle())
                .setParameter(4, book.getPublishDate())
                .setParameter(2, authorKey)
                .setParameter(12, book.getISBN())
                .setParameter(5, book.getLength())
                .setParameter(6, book.getRating())
                .setParameter(7, book.getImgSrc())
                .setParameter(10, book.getPublisher())
                .setParameter(3, book.getCheckouts())
                .setParameter(11,book.getAddedDate())
                .setParameter(8, book.getSampleSrc())
                .setParameter(9, book.getDescription())
                .setParameter(13, book.getGenre());
        if(q.executeUpdate() == 1)
           {
               FullTextSession fullTextSession = Search.getFullTextSession(session);
               Object bookToIndex = fullTextSession.load( EBook.class, bookKey );
               fullTextSession.index(bookToIndex);
               return 1;
           }else{ return 0; }
   }
   @Transactional

    public int removeCheckoutRecord(EBook book, User currentUser) {
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Query q = session.createQuery("delete CheckoutRecord where ebook = ? and user = ?")
                .setParameter(0, book)
                .setParameter(1, currentUser);
        return q.executeUpdate();
    }
    @Transactional
    public void setFormat(EBook book, User user, String format) {
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Query q = session.createQuery("update CheckoutRecord set format = ?, downloaded = 1 where ebook = ? and user = ?")
                .setParameter(0, format)
                .setParameter(1, book)
                .setParameter(2, user);
        q.executeUpdate();
    }
    
	@Transactional
    public void rateEbook(double rating, String bookID,String userKey) {

        Session session = sessionFactory.getCurrentSession();
        long time = System.currentTimeMillis();
        java.sql.Timestamp timestamp = new java.sql.Timestamp(time);
        org.hibernate.Query q = session.createSQLQuery("insert into ebookRating values(?,?,?,?) ON DUPLICATE KEY UPDATE RATING = ?")
                .setParameter(0, bookID)
                .setParameter(1, rating)
                .setParameter(2, userKey)
                .setParameter(3, timestamp)
                .setParameter(4, rating);
        q.executeUpdate();
             
        EBook ratedBook = findById(bookID);
        q = session.createSQLQuery("SELECT AVG(RATING) AS avg_rating FROM ebookrating WHERE EBOOK_ID = ?")
                .setParameter(0, bookID);
        List result = q.list();
        double newRating = (double)result.get(0);
        ratedBook.setRating(newRating);
        updateEBook(ratedBook);
    }
    

    @Transactional

    public List<ItemHold> getHoldList(User currentUser) {
		Session session = sessionFactory.getCurrentSession();
        org.hibernate.Query q = session.createQuery("from ItemHold where user = ?")
                .setParameter(0, currentUser);
        return q.list();
	}

    @Transactional
    public double getRating(String id)
    {
        Session session = sessionFactory.getCurrentSession();

        EBook ratedBook = findById(id);
        org.hibernate.Query q = session.createSQLQuery("SELECT AVG(RATING) AS avg_rating FROM ebookrating WHERE EBOOK_ID = ?")
                .setParameter(0, id);
        List result = q.list();
        double rating = (double)result.get(0);
        return rating;
    }

    
    @Transactional
    public boolean isOnUsersWishlist(String bookId, String userId)
    {
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Query q = session.createSQLQuery("select * from wishlist WHERE EBOOK_ID = ? AND USER_ID = ?")
                                                       .setParameter(0, bookId)
                                                       .setParameter(1, userId);
        if(q.list().isEmpty())
        {
            return false;
        }
        else { return true; }
    }
    
    @Transactional
    public int addBookToWishList(String bookId, String userId)
    {
        Session session = sessionFactory.getCurrentSession();
        
        if(!(isOnUsersWishlist(bookId, userId)))
        {
            String key = UUID.randomUUID().toString();
            org.hibernate.Query q = session.createSQLQuery("insert into wishlist values(?,?,?)")
                                                       .setParameter(0, key)
                                                       .setParameter(1, userId)
                                                       .setParameter(2, bookId);
            return q.executeUpdate();
        }
        else { return 1; }
    }
    
    @Transactional
    public List<WishlistRecord> getUserWishlist(String userId)
    {
       Session session = sessionFactory.getCurrentSession();
       org.hibernate.Query q = session.createQuery("from WishlistRecord where USER_ID = ?")
                                                       .setParameter(0, userId);
       List<WishlistRecord> records = (List<WishlistRecord>)q.list();
       return records;

    }
    

    @Transactional
    public boolean holdEbook(ItemHold hold) {
		Session session = sessionFactory.getCurrentSession();
		int next = ((Long)(session.createQuery("select count(*) from ItemHold").uniqueResult())).intValue();
        org.hibernate.Query q = session.createSQLQuery("insert into item_holds values(?,?,?,?)")
                .setParameter(0, next+1)
                .setParameter(1, hold.getUser())
                .setParameter(2, hold.getEbook())
                .setParameter(3, hold.getTimeLeft());
        return q.executeUpdate()==1;
	}
	@Transactional
    public int removeFromWishlist(String bookId, String userId)
    {
		Session session = sessionFactory.getCurrentSession();
		org.hibernate.Query q = session.createSQLQuery("DELETE FROM wishlist WHERE EBOOK_ID = ? AND USER_ID = ?")
                                                        .setParameter(0, bookId)
                                                        .setParameter(1, userId);
        return q.executeUpdate();
    }


    @Transactional
    public void removeHold(User u, EBook book) {
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Query q = session.createQuery("delete from ItemHold where user = ? and ebook = ?")
                .setParameter(0, u)
                .setParameter(1, book);
        q.executeUpdate();
    }



    


}
