<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>LFOM eLibrary</title>
    <!-- Bootstrap core CSS -->
    <link href="bootstrap-3.2.0-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/css/ebookCartouche.css" rel="stylesheet">
    <link href="assets/css/elibraryCommon.css" rel="stylesheet">
  </head>
  <style>
    html{
        min-width: 320px;
    }
    
    div.footer{
        position: fixed;
        bottom: 0;
        left: 7%;
    }

      
  </style>
  <body>
    <jsp:include page="common/toolbar.jsp"/>
    <div class="row" >
    <div class="contentBackgroundWithAliceBlue"style="margin-bottom:20px;">
    <h1>Popular Books <small>See what our readers love to read</small></h1>
 </div>
                <c:forEach items="${popularBooks}" var="book" varStatus="carouselCount"> 
                    
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
                    <a href="addtocart?id=${book.key}"><span class="glyphicon glyphicon-plus"></span><span class="glyphicon glyphicon-shopping-cart"></span></a>
                </span>
                </sec:authorize>
                </div>
             
                </c:forEach>                         
                <div class="rounded"style=";margin-bottom:10px;">
                    <h1> <small>  </small>&nbsp;</h1>
                 </div>
        </div>
        <div class="row" style="padding-top:15px;">
        <div class="contentBackgroundWithAliceBlue"style="margin-bottom:20px;">
        <h1>Newest Books <small>Check out the newest additions to our library</small></h1>
         </div>

            <c:forEach items="${newestBooks}" var="book" varStatus="carouselCount"> 

            <div class="itemCartouche" >
            <img class="topMiddle" src="${book.imgSrc}" width="155" height="225"/>
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
            <div class="rounded"style=";margin-bottom:10px;">
                <h1> <small>  </small>&nbsp;</h1>
             </div>
        </div>                
        <div class="row" style="padding-top:15px;">
            <div class="contentBackgroundWithAliceBlue"style="margin-bottom:20px;">
                <h1>Browse a Genre! <small>We're sure to have your favorite genre</small></h1>
                <c:forEach items="${genres}" var="genre" varStatus="carouselCount"> 
                <a href="browse${genre}" class="btn btn-xs btn-info" style="margin:5px;">
                    ${genre}
                </a>
                </c:forEach>
            </div>
        </div>                 
 
        <jsp:include page="common/footer.jsp"/>
    
  </body>
</html>
