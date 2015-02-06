<%-- 
    Document   : userWishlist
    Created on : Dec 15, 2014, 1:01:10 AM
    Author     : Brian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
                <style>
            body{ margin:150px 75px 0px 10px;
        left: 100px;
            }
            #page-content-wrapper{
        padding-left: 500px;
        position: absolute;
        border: solid 2px black;
        border-radius: 5px;
        background-color: #f9f9f9;
        z-index: -1;
        margin-top: -30px;
        float:right;
      }
        </style>
    </head>
    <body>
        <div class="container">
        <div class="panel-heading"><h1>EBook Wishlist</h1></div>
        <table class="table table-hover">
            <thead>
                <tr><td><strong>Title</strong></td><td><strong>Author</strong></td><td><strong>Publisher</strong></td><td><strong>Publish Date</strong></td>
            </thead>
            <tbody>
              <c:forEach items="${items}" var="item" varStatus="count">
                            <tr>
                                <td>${item.bookId.title}</td>
                                <td>${item.bookId.author.name}</td>
                                <td>${item.bookId.publisher}</td>
                                <td>${item.bookId.publishDate}</td>
                                <td><button class="btn btn-primary" onclick="removeFromWishlist(${item.bookId.key})">X</button>
                            </tr>
                </c:forEach>
            </tbody>
          </table> 
        </div>
    </body>
</html>
