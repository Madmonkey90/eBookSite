<%-- 
    Document   : checkedoutItems
    Created on : Dec 15, 2014, 10:04:36 AM
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
        <div class ="container">
        <div class="panel-heading"><h1>Checked Out Items</h1></div>
          <table class="table table-hover">
            <thead>
                <tr><td><strong>Title</strong></td><td><strong>Author</strong></td><td><strong>Date Checked Out</strong></td><td><strong>eBook Download</strong></td>
            </thead>
            <tbody>
              <c:forEach items="${currentUser.checkedOut}" var="cor" varStatus="count">
                            <tr>
                                <td>${cor.ebook.title}</td>
                                <td>${cor.ebook.author.name}</td>
                                <td>${cor.date}</td>
                                <td><a href="download?id=${cor.ebook.key}&format=.epub" class="btn btn-primary">Download .epub</a></td>
                            </tr>
                </c:forEach>
            </tbody>
          </table>
        </div>
    </body>
</html>
