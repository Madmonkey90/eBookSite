/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ePublisher.downloadSubsystem;

/**
 *
 * @author Quack
 */
public class FileRecord {
    private String bookId;
    private String fileFormat;
    private String filePath;
    private String bookCover;
    private int checkoutsLeft;
    private int licenseType;
    
    public FileRecord(){}
    
    public String getBookId(){ return bookId; }
    public String getFileFormat(){ return fileFormat; }
    public String getFilePath(){ return filePath; }
    public String getBookCover(){ return bookCover; }
    public int getCheckoutsLeft(){ return checkoutsLeft; }
    public int getLicenseType(){ return licenseType; }
    
    public void setBookId(String s){ bookId = s; }
    public void setFileFormat(String s){ fileFormat = s; }
    public void setFilePath(String s){ filePath = s; }
    public void setBookCover(String s){ bookCover = s; }
    public void setCheckoutsLeft(int i){ checkoutsLeft = i; }
    public void setLicenseType(int i){ licenseType = i; }
}
