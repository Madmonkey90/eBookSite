<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>LFOM page</title>
    <!-- Bootstrap core CSS -->
    <link href="bootstrap-3.2.0-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/css/elibraryCommon.css" rel="stylesheet">
    <style type="text/css">
      #foot-container{
        margin:50px 15px 50px;
      }
      body{
        margin:150px 75px 0px 10px;	
      }
      #sidebar-wrapper{
        margin-top:-100px;
        margin-left: -70px;
        left: 70px;
        width: 150px;
        background: #222;
        position: fixed;
        height: 100%;
        z-index: 1000;
      }
      
      #page-content-wrapper{
        position: absolute;
        border: solid 2px black;
        border-radius: 5px;
        background-color: #f9f9f9;
        z-index: -1;
        margin-top: -50px;
      }

      
      #sidebar-nav{
        display: block;
        float: left;
        width: 150px;
        list-style: none;
        margin: 0;
        padding: 0;
      }

      input{
        float:right;
      }
      .nopadding{
          padding: 0 !important;
          margin: 0 !important;
      }
      .alert{
        margin: 0px 0px 50px 50px;
        width:25%;
        position:relative; 
      }
      
      .alert-message p a:hover {
      text-decoration: underline;
      }

      div.footer{
        position: fixed;
        bottom: 0;
      }
      
    </style>
</head>
<body>
    <jsp:include page="common/toolbar.jsp"/>
    <div id="page-content-wrapper">
        <div class="container">
            <c:if test="${!empty err}">
                <div class="alert alert-danger alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert">
                        <span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <strong>${err}</strong>
                </div>
            </c:if>
            <div class="panel-heading"><h1>Your Cart: </h1></div>
        </div>
        <table class="table table-hover">
            <thead>
                <a href="checkoutAll" class="btn btn-success" style="margin-left: 82%;">Checkout All Items</a>
        <thead>
            <tr><td></td><td><strong>Book Title</strong></td><td><strong>Author</strong></td><td><strong>Description</strong></td></tr>
        </thead>
            <tbody>
                    <c:forEach items="${currentUser.cart}" var="book" varStatus="count">
                        <tr>
                            <td>${count.index+1}</td>
                            <td>${book.title}</td>
                            <td>${book.author.name}</td>
                            <td style="width:50%">${book.description.substring(0,150)}
                                <span id="exell${count.index+1}" onclick="expand(${count.index+1})">...</span>
                                <span id="expand${count.index+1}" style="visibility:hidden">${book.description.substring(150)}</span>
                            </td>
                            <td><a href="checkout?id=${book.key}" class="btn btn-success">Check Out</a></td>
                            <td><a href ="removefromcart?id=${book.key}"><span class="glyphicon glyphicon-minus"></span><span class="glyphicon glyphicon-shopping-cart"></span></a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="footer">
        <jsp:include page="common/footer.jsp"/>
    </div>
</body>
</html>
