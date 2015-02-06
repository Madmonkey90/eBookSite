/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ePublisher.downloadSubsystem;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;


public class FileRepo {
    private final SessionFactory sessionFactory;
    
    public FileRepo(SessionFactory f){ sessionFactory = f; }
    
    @Transactional
    public File getFile(String bookId, String format) throws MalformedURLException, URISyntaxException {
        String path = "";
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Query q = session.createQuery("select filePath from FileRecord where bookId = ? and fileFormat = ?")
                .setParameter(0, bookId)
                .setParameter(1, format);
        List<String> result = q.list();
        if(result.isEmpty())return null;
        org.hibernate.Query q1 = session.createQuery("select checkoutsLeft from FileRecord where bookId = ? and fileFormat = ?")
                .setParameter(0, bookId)
                .setParameter(1, format);
        List<Integer> result2 = q1.list();
        org.hibernate.Query q2 = session.createQuery("update FileRecord set checkoutsLeft = ? where bookId = ? and fileFormat = ?")
                .setParameter(0, result2.get(0)-1)
                .setParameter(1, bookId)
                .setParameter(2, format);
        q2.executeUpdate();
        path = result.get(0);
        URL url = new URL(path);
        File file = new File(url.toURI());
        return file;
    }
    
    @Transactional
    public List<FileRecord> getBookInfo(String bookId) {
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Query q = session.createQuery("from FileRecord where bookId = ?")
                .setParameter(0, bookId);
        List<FileRecord> result = q.list();
        if(result.isEmpty())return null;
        return result;
    }
    
    @Transactional
    public void returnBook(String bookId, String format) {
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Query q1 = session.createQuery("select checkoutsLeft from FileRecord where bookId = ? and fileFormat = ?")
                .setParameter(0, bookId)
                .setParameter(1, format);
        List<Integer> result2 = q1.list();
        org.hibernate.Query q2 = session.createQuery("update FileRecord set checkoutsLeft = ? where bookId = ? and fileFormat = ?")
                .setParameter(0, result2.get(0)+1)
                .setParameter(1, bookId)
                .setParameter(2, format);
        q2.executeUpdate();
    }
    
}
