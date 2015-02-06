package eLibrary.web.controllers;

import eLibrary.Aspectj.Audit;
import eLibrary.Domain.CheckoutRecord;
import eLibrary.Domain.EBook;
import eLibrary.Domain.ItemHold;
import eLibrary.Domain.User;
import eLibrary.Handlers.ItemHandler;
import eLibrary.Handlers.UserHandler;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountItemsDisplayController {
    @Autowired
    private User currentUser;
    private ItemHandler itemHandler;
    private UserHandler userHandler;
    
    public ItemHandler getItemHandler(){
        return itemHandler;
    }
    public void setItemHandler(ItemHandler h){
        itemHandler = h;
    }
    

    public UserHandler getUserHandler(){
        return userHandler;
    }
    public void setUserHandler(UserHandler u){
        userHandler = u;
    }
    
    @Audit("View Account Items")

    @RequestMapping(value = "/accountItemsDisplay", method= RequestMethod.GET)
    public ModelAndView AccountPage(@RequestParam(value = "h", required = false)String h) throws IOException {
        if(h!=null&&h.equals("1")){
            ModelAndView mv = new ModelAndView("accountItemHoldsDisplay");
            Set<ItemHold> soih = itemHandler.updateHoldList(currentUser);
            if(currentUser != null){ mv.addObject("currentUser", currentUser); }
            mv.addObject("itemHolds", soih);
            return mv;
        }
        ModelAndView mv = new ModelAndView("accountItemsDisplay");
        Set<CheckoutRecord> soco = itemHandler.updateCheckoutList(currentUser);
        if(currentUser !=null){ mv.addObject("currentUser", currentUser); }
        mv.addObject("checkedOut", soco);
        return mv;
    }
    
    @RequestMapping(value = "accountItemsDisplay/return", method = RequestMethod.GET)
    public ModelAndView returnBook(@RequestParam(value = "id", required = true)String bookId){
        EBook ebook = itemHandler.findById(bookId);
        int err = itemHandler.returnBook(ebook, currentUser);
        if(err==1)currentUser.getCheckedOut().remove(ebook);
        ModelAndView mv = new ModelAndView("redirect:http://69.121.220.130:8080/eLibrary/accountItemsDisplay");
        if(currentUser !=null){ mv.addObject("currentUser", currentUser); }
        return mv;
    }
    
    @RequestMapping(value = "accountItemsDisplay/setFormat", method = RequestMethod.GET)
    public ModelAndView setFormat(@RequestParam(value = "id", required = true)String bookId,
            @RequestParam(value = "user", required = true)String userId,
            @RequestParam(value = "format", required = true)String format){
        User u = userHandler.loadById(userId);
        EBook book = itemHandler.findById(bookId);
        itemHandler.setFormat(book, u, format);
        ModelAndView mv = new ModelAndView("mainPage");
        return mv;
    }
    
    @RequestMapping(value = "accountItemsDisplay/holdItem", method = RequestMethod.GET)
    public ModelAndView holdItem(@RequestParam(value = "user", required = true) String userId,
            @RequestParam(value = "id", required = true) String bookId) throws ParseException{
        User u = userHandler.loadById(userId);
        EBook book = itemHandler.findById(bookId);
        Date d; 
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.WEEK_OF_YEAR, 1);
        d = cal.getTime();
        itemHandler.holdItem(book, u, d);
        ModelAndView mv = new ModelAndView("mainPage");
        return mv;
    }
    
    @RequestMapping(value = "accountItemsDisplay/removeHold", method = RequestMethod.GET)
    public ModelAndView removeHold(@RequestParam(value = "id", required = true) String bookId,
            @RequestParam(value = "userId", required = true) String userId){
        User u = userHandler.loadById(userId);
        EBook book = itemHandler.findById(bookId);
        itemHandler.removeHold(u, book);
        ModelAndView mv = new ModelAndView("redirect:http://69.121.220.130:8080/eLibrary/accoutItemsDisplay?h=1");
        return mv;
    }
}
