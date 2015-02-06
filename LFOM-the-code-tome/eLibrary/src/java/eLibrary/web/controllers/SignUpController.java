/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eLibrary.web.controllers;
import eLibrary.Aspectj.Audit;
import eLibrary.Domain.User;
import eLibrary.Handlers.UserHandler;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author thecarlson
 */
@Controller
public class SignUpController {
    
    private UserHandler handler;
    
    public SignUpController(){}
    
    public UserHandler getUserHandler(){ return handler; }
    public void setUserHandler(UserHandler handler) { this.handler = handler; }
    
    @RequestMapping(value = "/signup", method= RequestMethod.GET)
    public ModelAndView Signup() {
        ModelAndView mv = new ModelAndView("signup");
        return mv;
    }
    
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView doSignup(@RequestParam("fname") String fname, @RequestParam("lname") String lname,
            @RequestParam("email") String email, @RequestParam("password") String pass) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        User u = new User();
        UUID gen = UUID.randomUUID();
        u.setKey(gen.toString());
        u.setFirstName(fname);
        u.setLastName(lname);
        u.setEmail(email);
        u.setPassword(pass);
        int err = handler.createUser(u);
        ModelAndView mv = new ModelAndView("login");
        if(err==0){
            mv = new ModelAndView("signup");
            mv.addObject("err","An error has occurred.");
        }
        mv.addObject("newUser",true);
        return mv;
        
    }
}
