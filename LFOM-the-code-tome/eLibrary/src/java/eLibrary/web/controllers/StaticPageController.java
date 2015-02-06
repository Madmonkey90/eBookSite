package eLibrary.web.controllers;

import eLibrary.Domain.User;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
//Pages that do not need the logic of a specialized controller 
//are handled through this controller
public class StaticPageController {
    
    @Autowired
    private User currentUser;
    
    
    @RequestMapping(value = "*.html", method= RequestMethod.GET)
    public ModelAndView Page(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String[] uriSplit = uri.split("/");
        String viewName = uriSplit[uriSplit.length-1];
        viewName = viewName.replaceFirst(".html", "");
        //Clarifies if this controller was used when browsing server log
        System.out.println("StaticPageController : "+viewName);
        ModelAndView mv = new ModelAndView(viewName);
        if(currentUser !=null){ mv.addObject("currentUser", currentUser); }
        return mv;
    }   
    
}