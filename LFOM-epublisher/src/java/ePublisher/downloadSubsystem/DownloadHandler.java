/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ePublisher.downloadSubsystem;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Quack
 */
public class DownloadHandler {
    private FileRepo fileRepo;
    
    public DownloadHandler(){}
    
    public FileRepo getFileRepo(){ return fileRepo; }
    public void setFileRepo(FileRepo r){ fileRepo = r; }
    
    public File retrieveFile(String bookId, String format) throws IOException, MalformedURLException, URISyntaxException {
        return fileRepo.getFile(bookId, format);
    }

    public List<FileRecord> getBookInfo(String bookId) {
        return fileRepo.getBookInfo(bookId);
    }

    public void returnBook(String bookId, String format) {
        fileRepo.returnBook(bookId, format);
    }

    public Date calculateDueDate(String bookId) {
        FileRecord rep = fileRepo.getBookInfo(bookId).get(0);
        Date d;
        switch(rep.getLicenseType()){
            case 2: Calendar cal = Calendar.getInstance();
                    cal.add(Calendar.WEEK_OF_YEAR, 2);
                    d = cal.getTime();
                    break;
            default: Calendar cal2 = Calendar.getInstance();
                     cal2.add(Calendar.WEEK_OF_YEAR, 1);
                     d = cal2.getTime();
        }
        return d;
    }

    
    
}
