<%-- 
    Document   : addItem
    Created on : Nov 29, 2014, 3:41:01 PM
    Author     : Brian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
             <form role="form" method="POST" action="admin/addItem">
        <fieldset style = " 
                background-color: #F0F0F0;
                width: 33%;
                min-width: 198px;
                padding: 10px;
                border: 1.5px solid black;
                border-radius: 5px;
                box-shadow: 0px 0px 10px 4px rgba(119, 119, 119, 0.75);
                -moz-box-shadow: 0px 0px 10px 4px rgba(119, 119, 119, 0.75);
                -webkit-box-shadow: 0px 0px 10px 4px rgba(119, 119, 119, 0.75);
                ">
            <h1>Add Item Form</h1>
            Title: <input type="text" name="title" id="title" value="${book.title}"><br><br>
                        Author: <input type="text" name="authorName" value="${book.author.name}"><br><br>
                        Publish Date: <input type="text" name="publishDate" value="${book.publishDate}"><br><br>
                        ISBN: <input type="text" name="ISBN" value="${book.ISBN}"><br><br>
                        Length: <input type="text" name="length" value="${book.length}"><br><br>
                        Rating: <input type="text" name="rating" value="${book.rating}"><br><br>
                        Cover Image Source: <input type="text" name="imgSrc" value="${book.imgSrc}"><br><br>
                        Publisher: <input type="text" name="publisher" value="${book.publisher}"><br><br>
                        Total Number of Checkouts: <input type="text" name="checkouts" value="${book.checkouts}"><br><br>
                        Date Added: <input type="text" name="addedDate" value="${book.addedDate}"><br><br>
                        Sample Source: <input type="text" name="sampleSrc" value="${book.sampleSrc}"><br><br>
                        Book Description: <input type="text" name="description" value="${book.description}"><br><br>
                        Genre: <input type="text" name="genre" value="${book.genre}"><br><br>
                        <button type="submit" class="btn btn-primary">Add Item</button>
		</fieldset> 
      </form>
</html>
