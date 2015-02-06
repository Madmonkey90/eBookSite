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
        left: 100px;
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
        margin-top: -30px;
      }
      #sidebar-wrapper{
        margin-top:-100px;
        margin-left: -70px;
        left: 70px;
        width: 150px;
        background-color: rgba(170,137,100,.9);
        position: fixed;
        height: 100%;
        z-index: 1000;
      }
      #sidebar-nav{
        display: block;
        float: left;
        width: 150px;
        list-style: none;
        margin: 0;
        padding: 0; 
      }
      .nav-pills .nav-stacked{
          color: rgb(79,55,40);
      }
      #sidebar-nav>li>a{
          color: white;
      }
      #sidebar-nav>li>:hover{
          background-color: rgb(79,55,40);
      }
      #page-content-wrapper{
        left: 170px;
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
      
      div.footer{
        position: fixed;
        bottom: 0;
      }
    </style>
</head>
<body>
    <jsp:include page="common/toolbar.jsp"/>
    <div id="sidebar-wrapper">
      <ul class="nav nav-pills nav-stacked" id="sidebar-nav">
        <li><a href="accountPage">Overview</a></li>      
        <li class="active"><a href="accountItemsDisplay">View Checked Out Items</a></li>
        <li><a href="accountItemsDisplay?h=1">View Held Items</a></li>
        <li><a href="#wishlist" onclick="getWishlist()">View Wishlist</a></li>
      </ul>
    </div>
    <div id="page-content-wrapper">
        <div class="container">
          <div class="panel-heading"><h1>Checked Out Items</h1></div>
            </div>
          <table class="table table-hover">
            <thead>
                <tr><td><strong>Title</strong></td><td><strong>Author</strong></td><td><strong>Date Checked Out</strong></td><td><strong>Format</strong></td><td><strong>eBook Download</strong></td>
            </thead>
            <tbody>
              <c:forEach items="${currentUser.checkedOut}" var="cor" varStatus="count">
                            <tr>
                                <td>${cor.ebook.title}</td>
                                <td>${cor.ebook.author.name}</td>
                                <td>${cor.date}</td>
                                <c:if test="${!cor.downloaded}">
                                    <td><a href="accountItemsDisplay/return?id=${cor.ebook.key}" class="btn btn-danger">Return Book</a></td>
                                </c:if>
                                <c:if test="${!empty cor.format}">
                                    <td>${cor.format}</td>
                                </c:if>
                                    <td><a href="http://69.121.220.130:8084/ePublisher/download?id=${cor.ebook.key}&userId=${currentUser.key}" class="btn btn-primary" target="_blank">Download from LFOM ePub</a></td>
                            </tr>
                </c:forEach>
            </tbody>
          </table> 
        </div>
    </div>
    <div class="footer">
        <jsp:include page="common/footer.jsp"/>
    </div> 
                     <script>
$(document).ready(function(){
   $('ul.nav.nav-pills.nav-stacked li a').click(function() {			
    $(this).parent().addClass('active').siblings().removeClass('active');			
});

});




    </script>
</body>
</html>
