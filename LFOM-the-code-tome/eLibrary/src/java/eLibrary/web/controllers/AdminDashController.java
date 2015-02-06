package eLibrary.web.controllers;


import eLibrary.Domain.*;
import eLibrary.Handlers.ItemHandler;
import eLibrary.Handlers.MetricsHandler;
import eLibrary.Handlers.UserHandler;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminDashController {
    
    @Autowired
    private User currentUser;
    private UserHandler userHandler;
    private ItemHandler itemHandler;
    private MetricsHandler metricsHandler;
    
    public UserHandler getUserHandler(){ return userHandler; }
    public void setUserHandler(UserHandler handler) { userHandler = handler; }
    
    public ItemHandler getItemHandler(){ return itemHandler; }
    public void setItemHandler(ItemHandler handler) { itemHandler = handler; }
    
    public void setMetricsHandler(MetricsHandler handler) { metricsHandler = handler; }
    public MetricsHandler getMetricsRepo() { return metricsHandler; }
    

    @RequestMapping(value = "/admin", method= RequestMethod.GET)
    public ModelAndView AdminDash() {
        ModelAndView mv = new ModelAndView("admin");
        if(currentUser !=null){ mv.addObject("currentUser", currentUser); }
        return mv;
    }
    
    @RequestMapping(value= "admin/addUser", method = RequestMethod.GET)
    public ModelAndView getAddUser(){
        ModelAndView mv = new ModelAndView("ajax/addUser");
        if(currentUser !=null){ mv.addObject("currentUser", currentUser); }
        return mv;
    }
    
    @RequestMapping(value ="admin/addUser", method = RequestMethod.POST)
    public ModelAndView doAddUser(@RequestParam("fname") String fname, @RequestParam("lname") String lname,
            @RequestParam("email") String email, @RequestParam("password") String pass)throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        ModelAndView mv = new ModelAndView("redirect:/admin");
        if(currentUser !=null){ mv.addObject("currentUser", currentUser); }
        User newUser = new User();
        UUID gen = UUID.randomUUID();
        newUser.setKey(gen.toString());
        newUser.setFirstName(fname);
        newUser.setLastName(lname);
        newUser.setEmail(email);
        newUser.setPassword(pass);
        int err = userHandler.createUser(newUser);
        if(err==0){ mv.addObject("msg", "error has occured"); }
        else { mv.addObject("msg", "Account Created"); }
        return mv;
    }
    
    @RequestMapping(value= "admin/editUser", method = RequestMethod.GET)
    public ModelAndView getEditUser(){
        ModelAndView mv = new ModelAndView("ajax/editUser");
        if(currentUser !=null){ mv.addObject("currentUser", currentUser); }
        return mv;
    }
    
    @RequestMapping(value ="admin/fillEditForm", method = RequestMethod.POST)
    public ModelAndView fillEditForm(@RequestParam(value = "email") String email){
        ModelAndView mv = new ModelAndView("ajax/editUser");
        User userToEdit = userHandler.loadByEmail(email);
        mv.addObject("userToEdit", userToEdit);
        if(currentUser !=null){ mv.addObject("currentUser", currentUser); }
        return mv;
    }
    
    @RequestMapping(value ="admin/editUser", method = RequestMethod.POST)
    public ModelAndView doEditUser(@RequestParam("email") String email, @RequestParam("lname") String lname, 
                                    @RequestParam("fname") String fname, @RequestParam("password") String password,
                                    @RequestParam("role") String role, @RequestParam("key") String key) throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        ModelAndView mv = new ModelAndView("redirect:/admin");
        if(currentUser !=null){ mv.addObject("currentUser", currentUser); }
        User userToEdit = new User();
        userToEdit.setKey(key);
        userToEdit.setFirstName(fname);
        userToEdit.setLastName(lname);
        userToEdit.setEmail(email);
        userToEdit.setPassword(password);
        Set roleSet = new HashSet();
        roleSet.add(role);
        userToEdit.setUserRole(roleSet);
        int err = userHandler.updateUser(userToEdit);
        if(err==0){ mv.addObject("msg", "error has occured"); }
        else { mv.addObject("msg", "Account Updated"); }
        return mv;
    }
    
    @RequestMapping(value= "admin/deleteUser", method = RequestMethod.GET)
    public ModelAndView getDeleteUser(){
        ModelAndView mv = new ModelAndView("ajax/deleteUser");
        if(currentUser !=null){ mv.addObject("currentUser", currentUser); }
        return mv;
    }
    
    @RequestMapping(value ="admin/deleteUser", method = RequestMethod.POST)
    public ModelAndView doDeleteUser(@RequestParam("key") String key, @RequestParam("email") String email){
        ModelAndView mv = new ModelAndView("redirect:/admin");
        if(currentUser !=null){ mv.addObject("currentUser", currentUser); }
        int err = userHandler.deleteUser(key, email);
        if(err==0){ mv.addObject("msg", "error has occured"); }
        else { mv.addObject("msg", "Account Deleted"); }
        return mv;
    }
    
    @RequestMapping(value = "admin/editItem", method = RequestMethod.GET)
    public ModelAndView getEditItem(@RequestParam("id") String id){
        EBook book = itemHandler.findById(id);
        ModelAndView mv = new ModelAndView("adminEditItem");
        if(currentUser !=null){ mv.addObject("currentUser", currentUser); }
        mv.addObject("book", book);
        return mv;
    }
    
    @RequestMapping(value ="admin/editItem", method = RequestMethod.POST)
    public ModelAndView doEditItem(@RequestParam("title") String title, @RequestParam("publishDate") String publishDate, 
                                    @RequestParam("authorName") String authorName, @RequestParam("publisher") String publisher,
                                    @RequestParam("imgSrc") String imgSrc, @RequestParam("description") String description,
                                    @RequestParam("checkouts") String checkouts, @RequestParam("addedDate") String addedDate,
                                    @RequestParam("ISBN") String ISBN, @RequestParam("length") String length,
                                    @RequestParam("rating") String rating, @RequestParam("genre") String genre,
                                    @RequestParam("key") String key, @RequestParam("authorKey") String authorKey)
    {
        ModelAndView mv = new ModelAndView("redirect:/admin");
        if(currentUser !=null){ mv.addObject("currentUser", currentUser);}
        EBook bookToEdit = new EBook();
        Author author = itemHandler.findAuthorById(authorKey);
        bookToEdit.setAuthor(author);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date pDate = new Date();
        Date aDate = new Date();
        try{
            pDate = df.parse(publishDate);
            aDate = df.parse(addedDate);
        }catch(Exception e) {}
        bookToEdit.setKey(key);
        bookToEdit.setTitle(title);
        bookToEdit.setPublishDate(pDate);
        bookToEdit.setPublisher(publisher);
        bookToEdit.setImgSrc(imgSrc);
        bookToEdit.setDescription(description);
        bookToEdit.setCheckouts(Integer.parseInt(checkouts));
        bookToEdit.setAddedDate(aDate);
        bookToEdit.setISBN(ISBN);
        bookToEdit.setLength(Integer.parseInt(length));
        bookToEdit.getAuthor().setName(authorName);
        bookToEdit.setRating(Integer.parseInt(rating));
        bookToEdit.setGenre(genre);
        int err = itemHandler.updateEBook(bookToEdit);
        if(err==0){ mv.addObject("msg", "error has occured"); }
        else { mv.addObject("msg", "Book Info Updated"); }
        return mv; 
    }
    
    @RequestMapping(value ="admin/deleteItem", method = RequestMethod.GET)
    public ModelAndView deleteItem(@RequestParam("id") String id)
    {
        ModelAndView mv = new ModelAndView("redirect:/admin");
        if(currentUser !=null){ mv.addObject("currentUser", currentUser);}
        int err = itemHandler.deleteEBook(id);
        if(err==0){ mv.addObject("msg", "error has occured"); }
        else { mv.addObject("msg", "Book Deleted"); }
        return mv; 
    }
    
    @RequestMapping(value= "admin/addItem", method = RequestMethod.GET)
    public ModelAndView getAddItem(){
        ModelAndView mv = new ModelAndView("ajax/addItem");
        if(currentUser !=null){ mv.addObject("currentUser", currentUser); }
        return mv;
    }
    
    @RequestMapping(value ="admin/addItem", method = RequestMethod.POST)
    public ModelAndView doAddItem(@RequestParam("title") String title, @RequestParam("publishDate") String publishDate, 
                                    @RequestParam("authorName") String authorName, @RequestParam("publisher") String publisher,
                                    @RequestParam("imgSrc") String imgSrc, @RequestParam("description") String description,
                                    @RequestParam("checkouts") String checkouts, @RequestParam("addedDate") String addedDate,
                                    @RequestParam("ISBN") String ISBN, @RequestParam("length") String length,
                                    @RequestParam("rating") String rating, @RequestParam("genre") String genre)
    {
        ModelAndView mv = new ModelAndView("redirect:/admin");
        if(currentUser !=null){ mv.addObject("currentUser", currentUser);}
        EBook bookToAdd = new EBook();
        Author author = itemHandler.findAuthorByName(authorName);
        if(author != null)
        {
            bookToAdd.setAuthor(author);
        }
        else{
            author = new Author();
            author.setName(authorName);
            bookToAdd.setAuthor(author);
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date pDate = new Date();
        Date aDate = new Date();
        try{
            pDate = df.parse(publishDate);
            aDate = df.parse(addedDate);
        }catch(Exception e) {}
        bookToAdd.setTitle(title);
        bookToAdd.setPublishDate(pDate);
        bookToAdd.setPublisher(publisher);
        bookToAdd.setImgSrc(imgSrc);
        bookToAdd.setDescription(description);
        bookToAdd.setCheckouts(Integer.parseInt(checkouts));
        bookToAdd.setAddedDate(aDate);
        bookToAdd.setISBN(ISBN);
        bookToAdd.setLength(Integer.parseInt(length));
        bookToAdd.getAuthor().setName(authorName);
        bookToAdd.setRating(Integer.parseInt(rating));
        bookToAdd.setGenre(genre);
        int err = itemHandler.addEBook(bookToAdd);
        if(err==0){ mv.addObject("msg", "error has occured"); }
        else { mv.addObject("msg", "Book Added"); }
        return mv; 
    }
    
    @RequestMapping(value = "admin/searchData", method = RequestMethod.GET)
    public @ResponseBody List getSearchData(){
        return metricsHandler.getSearchData();
    }
    
    @RequestMapping(value ="admin/pageData", method = RequestMethod.GET)
    public @ResponseBody List getPageData(){
        return metricsHandler.getPageData();
    }
    
    @RequestMapping(value="admin/checkoutData", method = RequestMethod.GET)
    public @ResponseBody List getCheckoutData(){
        return metricsHandler.getCheckoutData();
    }
    
    @RequestMapping(value= "admin/tickets", method = RequestMethod.GET)
    public ModelAndView getTickets(){
        ModelAndView mv = new ModelAndView("ajax/ticketList");
        if(currentUser !=null){ mv.addObject("currentUser", currentUser); }
        List tickets = metricsHandler.getTickets();
        mv.addObject("tickets", tickets);
        return mv;
    }
    @RequestMapping(value= "admin/tickets", method = RequestMethod.POST)
    public ModelAndView resolveTicket(@RequestParam("key") String key){
        ModelAndView mv = new ModelAndView("ajax/ticketList");
        if(currentUser !=null){ mv.addObject("currentUser", currentUser); }
        int keyInt = Integer.parseInt(key);
        metricsHandler.resolveTicket(keyInt);
        return mv;
    }
    
}
