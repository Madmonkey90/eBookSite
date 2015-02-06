package eLibrary.web.controllers;




import eLibrary.Aspectj.Audit;
import eLibrary.Domain.CheckoutRecord;
import eLibrary.Domain.EBook;
import eLibrary.Domain.User;

import eLibrary.Handlers.UserHandler;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import eLibrary.Domain.WishlistRecord;
import eLibrary.Handlers.ItemHandler;
import java.io.IOException;
import java.util.List;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountPageController {

    @Autowired
    private User currentUser;
    

    private UserHandler userHandler;

    private ItemHandler itemHandler;

    

    public UserHandler getUserHandler(){ return userHandler; }
    public void setUserHandler(UserHandler h){ userHandler = h; }

    public ItemHandler getItemHandler(){ return itemHandler; }
    public void setItemHandler(ItemHandler handler) { itemHandler = handler; }

    
    @Audit("Account Page")
    @RequestMapping(value = "/accountPage", method= RequestMethod.GET)
    public ModelAndView AccountPage(ModelAndView mv) {
        List<EBook> books = itemHandler.getRecommended(currentUser.getKey());
        mv.addObject("books", books);
        mv.setViewName("accountPage");
        mv.addObject("name", currentUser.getFirstName() + " " + currentUser.getLastName());
        mv.addObject("email", currentUser.getEmail());
        if(currentUser !=null){ mv.addObject("currentUser", currentUser); }
        return mv;
    }

    @RequestMapping(value = "/accountPage/changePass", method = RequestMethod.GET)
    public ModelAndView getChangePassForm(){
        ModelAndView mv = new ModelAndView("ajax/changePass");
		if(currentUser !=null){ mv.addObject("currentUser", currentUser); }
        return mv;
	}
    
    @RequestMapping(value = "accountPage/wishlist", method = RequestMethod.GET)
    public ModelAndView getWishlist(ModelAndView mv){
        mv.setViewName("ajax/userWishlist");
        List<WishlistRecord> items = itemHandler.getUserWishlist(currentUser.getKey());
        mv.addObject("items", items);
        if(currentUser !=null){ mv.addObject("currentUser", currentUser); }
        return mv;
    }

    @RequestMapping(value = "/accountPage/changePass", method = RequestMethod.POST)
    public ModelAndView changePass(@RequestParam(value = "password", required = true) String pass) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        userHandler.changePass(pass, currentUser);
        ModelAndView mv = new ModelAndView("redirect:http://69.121.220.130:8085/eLibrary/login?logout=true");
		if(currentUser !=null){ mv.addObject("currentUser", currentUser); }
		return mv;
    }
    
    @RequestMapping(value = "accountPage/removeFromWishlist", method = RequestMethod.GET)
    public ModelAndView removeFromWishlist(@RequestParam("key") String key){
        ModelAndView mv = new ModelAndView("redirect:/accountPage/wishlist");
        if(currentUser !=null){ mv.addObject("currentUser", currentUser); }
		int err = itemHandler.removeFromWishlist(key, currentUser.getKey());
        return mv;
    }


    @RequestMapping(value = "/accountPage/deactivateAccount", method = RequestMethod.GET)
    public ModelAndView deactivateAccount(){
        userHandler.deactivateAccount(currentUser);
        ModelAndView mv = new ModelAndView("redirect:http://69.121.220.130:8085/eLibrary/login?logout=true");
        return mv;
    }

    
    @Audit("View Account Items")
    @RequestMapping(value = "accountPage/checkedoutItems", method = RequestMethod.GET)
    public ModelAndView getCheckoutList() throws IOException{
        ModelAndView mv = new ModelAndView("ajax/checkedoutItems");
        Set<CheckoutRecord> soco = itemHandler.updateCheckoutList(currentUser);
        mv.addObject("checkedOut", soco);
        if(currentUser !=null){ mv.addObject("currentUser", currentUser); }

        return mv;
    }

}
