/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eLibrary.Handlers;

import eLibrary.Domain.User;
import eLibrary.Repos.UserRepo;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;


public class UserHandler {
    private UserRepo userRepo;
    public UserHandler(){}
    
    public UserRepo getUserRepo() { return userRepo; }
    public void setUserRepo(UserRepo r) { userRepo = r; }   
    
    public List<User> list() { return userRepo.getAllUsers(); }

    public int createUser(User u) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return userRepo.addUser(u);
    }
    
    public int updateUser(User u) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return userRepo.updateUser(u);
    }
    
    public User loadByEmail(String email){
        return userRepo.findByUserName(email);
    }

    public int deleteUser(String key, String email){
        return userRepo.deleteUser(key, email);
    }

    public User loadById(String userId) {
        return userRepo.findById(userId);
    }

    public void changePass(String pass, User currentUser) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        userRepo.changePass(pass, currentUser);
    }

    public void deactivateAccount(User currentUser) {
        userRepo.deactivateAccount(currentUser);
    }
    
}
