<%-- 
    Document   : sortResults
    Created on : Dec 15, 2014, 8:11:02 AM
    Author     : Brian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
    </head>
    <body>
        <div id="title">
                <h1>Your search results for ${searchInput} : </h1>
        </div>
        <div class="well">
                    <c:forEach items="${results}" var="book" varStatus="carouselCount"> 

                    <div class="itemCartouche" >
                    <a href="details${book.key}"><img class="topMiddle" src="${book.imgSrc}" width="155" height="225"/> </a>
                    <span class="buttons">
                      <a href="details${book.key}" class="btn btn-xs btn-success catourcheButtons">View Details</a>
                      <a href="bookSample${book.key}" class="btn btn-xs btn-info catourcheButtons">View Sample</a>
                    </span>
                    <br>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <span>
                        <a id="editItem" href="admin/editItem?id=${book.key}" onclick="getEditItem()"><span class="glyphicon glyphicon-pencil"></span></a>
                        <a id ="deleteItem" href="admin/deleteItem?id=${book.key}"><span class="glyphicon glyphicon-remove"></span></a>
                    </span>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_USER')">
                    <span>
                        <a href="addtocart?id=${book.key}"><span class="glyphicon glyphicon-plus"></span></a>
                    </span>
                    </sec:authorize>
                    </div>

                    </c:forEach>                                           
		</div>
    </body>
</html>
