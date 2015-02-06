<%-- 
    Document   : ticketList
    Created on : Dec 13, 2014, 7:00:25 PM
    Author     : Brian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ticket List</title>
    </head>
    <body>
        <table class="table table-hover">
            <thead>
                <tr><td><strong>TimeStamp</strong></td><td><strong>User email</strong></td><td><strong>Name</strong></td><td><strong>Description</strong></td><td><strong>Resolve Ticket</strong></td>
            </thead>
            <tbody>
              <c:forEach items="${tickets}" var="ticket" varStatus="count">
                            <tr>
                                <td>${ticket.timeStamp}</td>
                                <td>${ticket.userName}</td>
                                <td>${ticket.userEmail}</td>
                                <td>${ticket.description}</td>
                                <c:choose>
                                    <c:when test="${ticket.resolved == false}">
                                        <td><a href="#" class="btn btn-primary" onclick="resolveTicket(${ticket.key})">Resolve Ticket</a></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td><a href="#" class="btn btn-primary disabled">Resolve Ticket</a></td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                </c:forEach>
            </tbody>
          </table> 
    </body>
</html>
