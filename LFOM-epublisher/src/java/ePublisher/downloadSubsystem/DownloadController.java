/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ePublisher.downloadSubsystem;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DownloadController {
    private DownloadHandler downloadHandler;
    
    public DownloadController(){}
    
    public DownloadHandler getDownloadHandler(){ return downloadHandler; }
    public void setDownloadHandler(DownloadHandler d){ downloadHandler = d; }
    
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ModelAndView showBookDownload(@RequestParam(value = "id", required = true) String bookId,
            @RequestParam(value = "userId", required = true)String userId){
        List<FileRecord> bookInfo = downloadHandler.getBookInfo(bookId);
        ModelAndView mv = new ModelAndView("download");
        if(bookInfo==null){
            mv.addObject("imgPath","assets/images/altBookImg.png");
        }
        else{
            mv.addObject("imgPath",bookInfo.get(0).getBookCover());
            List<String> fileTypes = new LinkedList<String>();
            for(FileRecord fr : bookInfo){
                if(fr.getCheckoutsLeft()>0)fileTypes.add(fr.getFileFormat());
            }
            mv.addObject("fileTypes",fileTypes);
            mv.addObject("userId", userId);
            mv.addObject("id", bookId);
        }
        return mv;
    }
    
    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public void offerBook(@RequestParam(value = "id", required = true) String bookId,
            @RequestParam(value = "format", required = true) String format,
            @RequestParam(value = "userId", required = true) String userId,
            HttpServletResponse response){
        try{
            File f = downloadHandler.retrieveFile(bookId, format);
            InputStream is = new FileInputStream(f);
            switch(format){
                case ".epub": response.setContentType("application/epub+zip");
                              break;
                case ".pdf":  response.setContentType("application/pdf");
                              break;
            }
            response.setHeader("Content-Disposition","atttachment; filename=\""+f.getName()+"\"");
            URL url = new URL("http://localhost:8080/eLibrary/accountItemsDisplay/setFormat?"
                    + "id="+bookId+"&user="+userId+"&format="+format);
            url.openStream();
            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        }
        catch(IOException | URISyntaxException e){
            throw new RuntimeException("IOError: failure during book offer "+e.getMessage());
        }
    }
    
    @RequestMapping(value = "download/return", method = RequestMethod.GET)
    public ModelAndView returnBook(@RequestParam(value = "id", required = true) String bookId,
            @RequestParam(value = "format", required = true) String format){
        downloadHandler.returnBook(bookId, format);
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }
    
    @RequestMapping(value = "download/holdItem", method = RequestMethod.GET)
    public ModelAndView holdItem(@RequestParam(value = "bookId") String bookId,
            @RequestParam(value = "userId")String userId) throws IOException{
        Date date = downloadHandler.calculateDueDate(bookId);
        URL url = new URL("http://localhost:8080/eLibrary/accountItemsDisplay/holdItem?"
                    + "id="+bookId+"&user="+userId);
        url.openStream();
        ModelAndView mv = new ModelAndView("redirect:http://localhost:8080/eLibrary/mainPage");
        return mv;
    }
}
