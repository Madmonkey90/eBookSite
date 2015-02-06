<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
    <link href="assets/css/ebookCartouche.css" rel="stylesheet">
    <style type="text/css">
      #foot-container{
        margin:50px 15px 50px;
      }
      body{
        margin:150px 75px 0px 10px;
        left: 100px;
      }
      #page-content-wrapper{
        padding-left: 150px;
      }
      .carousel{
        height:325px;
        width: 85%;
	margin-bottom:80px;
      }
      .carousel .item{
        height:325px;
	background-color: #F0F0F0;
      }
	  .well{
		margin-bottom:25px;
	  }
	  .well h1{
		margin-top:0px;
	  }


    </style>
</head>
<body>
    <jsp:include page="common/toolbar.jsp"/>
    <div id="sidebar-wrapper">
      <ul class="nav nav-pills nav-stacked" id="sidebar-nav">
        <li class="active"><a href="accountPage">Overview</a></li>      
        <li><a href="#CheckedoutBooks" onclick="getCheckoutList()">View Checked Out Items</a></li>
        <li><a href="#wishlist" onclick="getWishlist()">View Wishlist</a></li>
      </ul>
    </div>
    <div id="page-content-wrapper">
    <div class="container">
      <h1>Account Information</h1><button type="button" class ="btn btn-primary">Edit</button>
      <h3>Name: </h3><p>${name}</p>
      <h3>Email: </h3><p>${email}</p>
      <br></br>
      <button type="button" class="btn btn-info">Change Password</button>
      <br></br>
      <button type="button" class="btn btn-info">Change Account Preferences</button>
		<h1>Recommended Books <small>based on books you have rated</small></h1>
		 <c:forEach items="${books}" var="book" varStatus="carouselCount">     
                <div class="itemCartouche" >
                <a href="details${book.key}"><img class="topMiddle" src="${book.imgSrc}" width="155" height="225"/> </a>
                <span class="buttons">
                  <a href="details${book.key}" class="btn btn-xs btn-success catourcheButtons">View Details</a>
                  <a href="bookSample${book.key}" class="btn btn-xs btn-info catourcheButtons">View Sample</a>
                </span>
                <br>
                <span>
                    <a href="addtocart?id=${book.key}"><span class="glyphicon glyphicon-plus"></span><span class="glyphicon glyphicon-shopping-cart"></span></a>
                </span>
                </div>
                </c:forEach> 
    <br></br>
    <br></br>
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