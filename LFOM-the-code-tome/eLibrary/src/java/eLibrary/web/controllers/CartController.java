package eLibrary.web.controllers;

import eLibrary.Aspectj.Audit;
import eLibrary.Domain.CheckoutRecord;
import eLibrary.Domain.EBook;
import eLibrary.Domain.User;
import eLibrary.Handlers.ItemHandler;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CartController {
    
    @Autowired
    private User currentUser;
    private ItemHandler itemHandler;
    
    public ItemHandler getItemHandler() { return itemHandler; }
    public void setItemHandler(ItemHandler h) { itemHandler = h; }
    @RequestMapping(value = "/cart", method= RequestMethod.GET)
    public ModelAndView Cart() {
        ModelAndView mv = new ModelAndView("cart");
        if(currentUser !=null){ mv.addObject("currentUser", currentUser); }
        return mv;
    }
    
    @RequestMapping(value = "/addtocart", method = RequestMethod.GET)
    public ModelAndView addBookToCart(@RequestParam(value = "id", required = true) String bookId){
        EBook ebook;
        ebook = itemHandler.findById(bookId);
        if(!currentUser.getCart().contains(ebook))
        {
            currentUser.getCart().add(ebook);
        }
        ModelAndView mv = new ModelAndView("cart");
        if(currentUser !=null){ mv.addObject("currentUser", currentUser); }
        return mv;
    }
    
    @RequestMapping(value ="/removefromcart" , method = RequestMethod.GET)
    public ModelAndView removeFromCart(@RequestParam(value ="id", required = true) String bookId)
    {
        EBook ebook;
        ModelAndView mv = new ModelAndView("redirect:cart");
        ebook = itemHandler.findById(bookId);
        List<EBook> userCart = currentUser.getCart();
        userCart.remove(ebook);
        if(currentUser !=null){ mv.addObject("currentUser", currentUser); }
        return mv;
    }
    
    @Audit("Checkout Book")
    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public ModelAndView checkOutBook(@RequestParam(value = "id", required = true) String bookId){
        ModelAndView mv = new ModelAndView("cart");
        EBook ebook = new EBook();
        Iterator<EBook> it = currentUser.getCart().iterator();
        while(it.hasNext()){
            EBook e = it.next();
            if(e.getKey().equals(bookId))ebook = e;
        }
        int err = itemHandler.checkout(bookId, currentUser.getKey());
        if(err==1){
            CheckoutRecord cr = new CheckoutRecord();
            cr.setEbook(ebook);
            cr.setUser(currentUser);
            cr.setDate(new Date());
            currentUser.getCheckedOut().add(cr);
            currentUser.getCart().remove(ebook);
        }
        else{mv.addObject("err", "An error occurred during checkout. Please try again.");}
        if(currentUser !=null){ mv.addObject("currentUser", currentUser); }
        return mv;
    }
    
    @Audit("Checkout Book")
    @RequestMapping(value = "/checkoutAll", method = RequestMethod.GET)
    public ModelAndView checkoutAll(){
        ModelAndView mv = new ModelAndView("redirect:accountItemsDisplay");
        List<EBook> cart = currentUser.getCart();
        boolean err = false;
        for(EBook e : cart){
            if(itemHandler.checkout(e.getKey(), currentUser.getKey())==1){
                CheckoutRecord cr = new CheckoutRecord();
                cr.setEbook(e);
                cr.setUser(currentUser);
                cr.setDate(new Date());
                currentUser.getCheckedOut().add(cr);
            }else{
                err=true;
                mv.addObject("err", "An error occurred during cart checkout. Please try again.");
                break;
            }
        }
        if(!err)currentUser.getCart().clear();
        if(currentUser !=null){ mv.addObject("currentUser", currentUser); }
        return mv;
    }
}
