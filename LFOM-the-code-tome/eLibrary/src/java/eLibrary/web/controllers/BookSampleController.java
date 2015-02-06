package eLibrary.web.controllers;

import eLibrary.Aspectj.Audit;
import eLibrary.Domain.EBook;
import eLibrary.Domain.User;
import eLibrary.Handlers.ItemHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookSampleController {
    
    @Autowired
    private User currentUser;
    private ItemHandler itemHandler;
    
    public ItemHandler getItemHandler() { return itemHandler; }
    public void setItemHandler(ItemHandler h) { itemHandler = h; }    
    
    @Audit("Book Sample")
    @RequestMapping(value = "/bookSample{itemId}", method= RequestMethod.GET)
    public ModelAndView BookSample(@PathVariable("itemId") String itemId, ModelAndView mv) {
        EBook book = itemHandler.findById(itemId);
        if(book != null){
            mv.addObject("sampleBook", book);
            mv.setViewName("bookSample"); 
        }
        else{ 
            //Sample unavailable
            //have the JSP go to an unavaiable page or 
            //have ajax grey out the sample button?
        }
        //mv.setViewName("bookSample");
        if(currentUser !=null){ mv.addObject("currentUser", currentUser); }
        return mv;
    }    
}
