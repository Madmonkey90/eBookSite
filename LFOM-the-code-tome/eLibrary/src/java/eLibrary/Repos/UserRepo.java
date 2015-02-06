/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eLibrary.Repos;

import eLibrary.Domain.User;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;
import org.apache.commons.codec.binary.Hex;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import org.springframework.transaction.annotation.Transactional;


public class UserRepo {
    private SessionFactory sessionFactory;
    
    public UserRepo(SessionFactory f) { sessionFactory = f;}
        
    @Transactional
    public List<User> getAllUsers(){
        @SuppressWarnings("unchecked")
        List<User> listUser = (List<User>) sessionFactory.getCurrentSession()
                .createCriteria(User.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listUser;
    }
    
    @Transactional
    public User findByUserName(String username)
    {
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery("from User where email = ?")
                .setString(0, username);
        List result = q.list();
        if(result.isEmpty())return null;
        User u = (User) result.get(0);
        return u;
    }
    @Transactional
    public User findById(String userId){
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery("from User where key = ?")
                .setParameter(0, userId);
        List<User> result = q.list();
        if(result.isEmpty()) return null;
        else return result.get(0);
    }
    
    @Transactional
    public int addUser(User u) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        Session session = sessionFactory.getCurrentSession();
        String key = UUID.randomUUID().toString();
        Query q = session.createSQLQuery("insert into users values(?,?,?,?,?,?)")
                .setParameter(0, u.getKey())
                .setParameter(1, u.getLastName())
                .setParameter(2, u.getFirstName())
                .setParameter(3, u.getEmail())
                .setParameter(4, hash(u.getPassword()))
                .setParameter(5, true);
        if (q.executeUpdate() >= 1)
        {
            Query q1 = session.createSQLQuery("insert into user_roles values(?,?,?)")
                        .setParameter(0,key)
                        .setParameter(1,u.getKey())
                        .setParameter(2, "ROLE_USER");
            return q1.executeUpdate();
        }else {return 0; }
    }
    
    @Transactional
    public int updateUser(User u) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createSQLQuery("UPDATE users SET LASTNAME = ?, FIRSTNAME = ?, EMAIL = ?, PASSWORD = ? WHERE USER_ID = ?")
                .setParameter(0, u.getLastName())
                .setParameter(1, u.getFirstName())
                .setParameter(2, u.getEmail())
                .setParameter(3, hash(u.getPassword()))
                .setParameter(4 , u.getKey());
        if(q.executeUpdate() == 1)
        {
            String role = (String)u.getUserRole().toArray()[0];
            Query q1 = session.createSQLQuery("UPDATE user_roles SET ROLE = ? WHERE USER = ?")
                        .setParameter(0,role)
                        .setParameter(1,u.getKey());
            return q1.executeUpdate();
        }else { return 0; }
    }
    
    @Transactional
    public int deleteUser(String key, String email){
        Session session = sessionFactory.getCurrentSession();
        if(!key.isEmpty()){
            Query q = session.createSQLQuery("DELETE FROM users where USER_ID = ?")
                    .setParameter(0, key);
            return q.executeUpdate();
        }
        else{
            User u = findByUserName(email);
            Query q = session.createSQLQuery("DELETE FROM users where USER_ID = ?")
                    .setParameter(0, u.getKey());
            return q.executeUpdate();
        }
    }
    
    @Transactional
    public void changePass(String pass, User currentUser) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Query q = session.createQuery("update User set password = ? where key = ?")
                .setParameter(0, hash(pass))
                .setParameter(1, currentUser.getKey());
        q.executeUpdate();
    }
    @Transactional
    public void deactivateAccount(User currentUser) {
       Session session = sessionFactory.getCurrentSession();
       org.hibernate.Query q = session.createQuery("update User set enabled = false where key = ?")
               .setParameter(0, currentUser.getKey());
       q.executeUpdate();
    }
    private String hash(String s)throws NoSuchAlgorithmException, UnsupportedEncodingException{
        byte[] toHash = s.getBytes("UTF-8");
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashed = md.digest(toHash);
        String hash = new String(Hex.encodeHex(hashed));
        return hash;
    }
    
    

    
}
