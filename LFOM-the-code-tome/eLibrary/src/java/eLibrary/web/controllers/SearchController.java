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
public class SearchController {
    
    @Autowired
    private User currentUser;
    
    private ItemHandler itemHandler;
    
    public SearchController() {  }
    
    public ItemHandler getItemHandler(){ return itemHandler; }
    public void setItemHandler(ItemHandler handler) { itemHandler = handler; }
    
    @Audit("Search")
    @RequestMapping(value = "/search", method= RequestMethod.POST)
    public ModelAndView Search(@RequestParam("searchInput") String searchInput, ModelAndView mv) {
        if(!searchInput.trim().isEmpty()){
            List<EBook> results = itemHandler.search(searchInput, true);
            mv.addObject("results", results);
            mv.addObject("searchInput", searchInput);
            mv.setViewName("searchResults");
        }
        else{ mv.setViewName("redirect:mainpage"); }
        if(currentUser !=null){ mv.addObject("currentUser", currentUser); }
        return mv;
    }   
    
    @Audit("AdvancedSearch")
    @RequestMapping(value = "/advancedSearch", method= RequestMethod.POST)
    public ModelAndView advSearch(@RequestParam("searchInput") String searchInput,ModelAndView mv
            )
    {
        if(!searchInput.trim().isEmpty()){
            List<EBook> results = itemHandler.advancedSearch(searchInput, true);
            mv.addObject("results", results);
            mv.addObject("searchInput", searchInput);
            mv.setViewName("searchResults");
        }
        else{
            mv.setViewName("advancedSearch");
            mv.addObject("status","empty");
        }
        if(currentUser !=null){ mv.addObject("currentUser", currentUser); }
        return mv;
    }       
    
    @RequestMapping(value = "/browse{genre}", method= RequestMethod.GET)
    public ModelAndView browse(@PathVariable("genre") String genre, ModelAndView mv) {
        if(!genre.trim().isEmpty()){
            List<EBook> results = itemHandler.browse(genre);
            mv.addObject("results", results);
            mv.addObject("searchInput", genre);
            mv.setViewName("searchResults");
        }
        else{ mv.setViewName("redirect:mainpage"); }
        if(currentUser !=null){ mv.addObject("currentUser", currentUser); }
        return mv;
    }
    
    @RequestMapping(value ="/search/sortBy{sortCriteria}", method = RequestMethod.POST)
    public ModelAndView sortResults(@PathVariable("sortCriteria") String sortCritera, @RequestParam("input") String input, ModelAndView mv)
    {
            List<EBook> results = itemHandler.search(input, false);
            List<EBook> sortedResults = itemHandler.sortResults(results, sortCritera);
            mv.addObject("results", sortedResults);
            mv.addObject("searchInput", input);
            mv.setViewName("ajax/sortResults");
        if(currentUser !=null){ mv.addObject("currentUser", currentUser); }
        return mv;
    }
    
}
