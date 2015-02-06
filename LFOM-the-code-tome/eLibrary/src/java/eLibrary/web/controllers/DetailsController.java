package eLibrary.web.controllers;

import eLibrary.Aspectj.Audit;
import eLibrary.Domain.EBook;
import eLibrary.Domain.User;
import eLibrary.Handlers.ItemHandler;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DetailsController {
    
    
    @Autowired
    private User currentUser;
    
    private ItemHandler itemHandler;
    
    public ItemHandler getItemHandler() { return itemHandler; }
    public void setItemHandler(ItemHandler h) { itemHandler = h; }
    
    @Audit("Add to Wishlist")
    @RequestMapping(value = "/addtowishlist", method = RequestMethod.GET)
    public ModelAndView addToWishlist(@RequestParam("id") String bookId)
    {
        ModelAndView mv = new ModelAndView("redirect:/details"+bookId);
        if(currentUser !=null){ mv.addObject("currentUser", currentUser); }
        int err = itemHandler.addBookToWishList(bookId, currentUser.getKey());
        if(!(err==1)){ System.out.println("ERROR"); }
        return mv;
    }
    
    
    @Audit("Rate Book")
    @RequestMapping(value = "/setRating", method = RequestMethod.POST)
    public ModelAndView setBookRating(@RequestParam(value = "bookID", required = true) String bookId,
        @RequestParam(value = "rating", required = true) String rating  
    )
    {
        double ratingDoubleVal = Double.parseDouble(rating);
        itemHandler.rateEbook(ratingDoubleVal,bookId,currentUser.getKey());
        ModelAndView mv = new ModelAndView("redirect:/details"+bookId);
        if(currentUser !=null){ mv.addObject("currentUser", currentUser); }
        return mv;
    }
       
    @Audit("Book Details")
    @RequestMapping(value = "/details{itemId}", method= RequestMethod.GET)
    public ModelAndView Details(@PathVariable("itemId") String itemId, ModelAndView mv) {
        EBook book;
        book = itemHandler.findById(itemId);
        if(itemHandler.isOnUsersWishList(itemId, currentUser.getKey()))
        {
            mv.addObject("onWishlist", true);
        }
        else{ mv.addObject("onWishlist", false); }
        if(book!= null){
           mv.addObject("currentBook", book);
           mv.setViewName("details"); 
        }
        else{ mv.setViewName("redirect:/mainpage"); }
        List<EBook> results = itemHandler.getSimilar(itemId);
        mv.addObject("similarBooks", results );
        if(currentUser !=null){ mv.addObject("currentUser", currentUser); }
        return mv;
    }        
}
