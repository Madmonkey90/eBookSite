package eLibrary.web.controllers;

import eLibrary.Aspectj.Audit;
import eLibrary.Domain.EBook;
import eLibrary.Domain.Ticket;
import eLibrary.Domain.User;
import eLibrary.Handlers.ItemHandler;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainPageController {
    
    @Autowired
    private User currentUser;
    
    private SessionFactory sessionFactory;
    private ItemHandler itemHandler;
    
    public ItemHandler getItemHandler() { return itemHandler; }
    public void setItemHandler(ItemHandler u) { itemHandler = u; }
    
    public SessionFactory getSessionFactory() { return sessionFactory; }
    public void setSessionFactory(SessionFactory s) { sessionFactory = s; }
    @Audit("Main Page")
    @RequestMapping(value = "/mainpage", method= RequestMethod.GET)
    public ModelAndView MainPage(ModelAndView mv) {
        mv.setViewName("mainPage");
        List<EBook> popularBooks = itemHandler.getMostPopular();
        List<EBook> newestBooks = itemHandler.getNewestAdditions();
        List<String> genres = itemHandler.getAllGenres();
        mv.addObject("popularBooks", popularBooks);
        mv.addObject("newestBooks", newestBooks);
        mv.addObject("genres", genres);
        mv.addObject("currentUser", currentUser);
        return mv;
    }
    
    @RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView redirectToMain()
    {              
        ModelAndView mv = new ModelAndView("redirect:mainpage");
        return mv;
    }
    
    @RequestMapping(value="admin/rebuildIndex", method = RequestMethod.GET)
    //CODE BELOW SET UPS INDEX FOR HIBERNATE SEARCH, CURRENTLY THE PATH FOR THIS
    //INDEX IS C:\lucene\indexes, THIS IS SET BY hibernate.search.default.indexBase 
    //PROPERTY IN hibernate.cfg.xml 
    @Transactional
    public ModelAndView rebuildIndex()
    {
    try{
        Session session = sessionFactory.getCurrentSession();
        FullTextSession fullTextSession = Search.getFullTextSession(session);
        fullTextSession.createIndexer().startAndWait();
        }catch(HibernateException | InterruptedException e){ e.printStackTrace();}
         

               
        ModelAndView mv = new ModelAndView("admin");
        return mv;
    }
    
    @RequestMapping(value ="/ticketSubmit", method = RequestMethod.POST)
    @Transactional
    public ModelAndView submitTicket(@RequestParam("ticketSenderName") String userName, 
                                     @RequestParam("ticketEmail") String userEmail,
                                     @RequestParam("ticketBody") String description)
    {
        Session session = sessionFactory.getCurrentSession();
        Ticket ticket = new Ticket();
        long time = System.currentTimeMillis();
        java.sql.Timestamp timestamp = new java.sql.Timestamp(time);
        ticket.setDescription(description);
        ticket.setUserEmail(userEmail);
        ticket.setUserName(userName);
        ticket.setTimeStamp(timestamp);
        session.save(ticket);
        ModelAndView mv = new ModelAndView("redirect:mainpage");
        return mv;
    }
    
   
}
