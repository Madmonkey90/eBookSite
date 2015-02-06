/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eLibrary.Repos;




import eLibrary.Domain.Ticket;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Brian
 */
public class MetricsRepo {
    private SessionFactory sessionFactory;
    
    public MetricsRepo(SessionFactory f) { sessionFactory = f;}
    
    @Transactional
    public int addSearchRecord(String searchString, long searchTime) throws Exception
    {
        try{
        Session session = sessionFactory.getCurrentSession();

        long time = System.currentTimeMillis();
        java.sql.Timestamp timestamp = new java.sql.Timestamp(time);
        org.hibernate.Query q = session.createSQLQuery("INSERT INTO search_metrics VALUES(?,?,?)")
                                        .setParameter(0,searchTime)
                                        .setParameter(1, searchString)
                                        .setParameter(2, timestamp);
        return q.executeUpdate();
        } catch(Exception e) { e.printStackTrace(); return 0;}
    }
    
    @Transactional
    public List getAvgSearchTimeData()
    {
       Session session = sessionFactory.getCurrentSession();
       org.hibernate.Query q = session.createSQLQuery("SELECT DATE(SEARCH_TIMESTAMP) AS day, AVG(SEARCH_TIME) AS avg_search_time FROM search_metrics GROUP BY DATE(SEARCH_TIMESTAMP) ORDER BY DATE(SEARCH_TIMESTAMP) ASC");
       List results = q.list();
       return results;
    }
    
    @Transactional
    public int addPageView(String userName, String pageName) throws Exception 
    {
        try{
        Session session = sessionFactory.getCurrentSession();
        long time = System.currentTimeMillis();
        java.sql.Timestamp timestamp = new java.sql.Timestamp(time);
        org.hibernate.Query q = session.createSQLQuery("INSERT INTO page_metrics VALUES(?,?,?)")
                                        .setParameter(0, pageName)
                                        .setParameter(1, userName)
                                        .setParameter(2, timestamp);
        return q.executeUpdate();
        } catch(Exception e) { e.printStackTrace(); return 0;}
        
    }
    
    @Transactional
    public List getPageData(){
       Session session = sessionFactory.getCurrentSession();
       org.hibernate.Query q = session.createSQLQuery("SELECT PAGENAME, COUNT(PAGENAME) AS total FROM page_metrics GROUP BY PAGENAME ORDER BY total DESC");
       List results = q.list();
       return results;
    }
    
    @Transactional
    public List getCheckoutData(){
       Session session = sessionFactory.getCurrentSession();
       org.hibernate.Query q = session.createSQLQuery("SELECT DATE(TIMESTAMP), COUNT(TRANS_ID) AS total FROM checked_out GROUP BY DATE(TIMESTAMP) ORDER BY DATE(TIMESTAMP) ASC");
       List results = q.list();
       return results; 
    }
    
    @Transactional
    public List getTickets(){
        @SuppressWarnings("unchecked")
        List list = (List) sessionFactory.getCurrentSession()
                .createCriteria(Ticket.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return list;
    }
    
    @Transactional
    public int resolveTicket(int key){
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createSQLQuery("UPDATE tickets SET RESOLVED = ? WHERE TICKET_KEY = ?")
                .setParameter(0, 1)
                .setParameter(1, key);
        return q.executeUpdate();
    }
}
