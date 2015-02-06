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
    <link href="../bootstrap-3.2.0-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="../assets/css/elibraryCommon.css" rel="stylesheet">
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
      #sidebar-nav{
        display: block;
        float: left;
        width: 150px;
        list-style: none;
        margin: 0;
        padding: 0;
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
	  .buttons{
		margin-left:55%;
	  }
    </style>
    
</head>
<body>
    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
            <a class="navbar-brand" href="../mainpage.html">
                <img alt="Brand" src="../assets/lfom.png" style="margin-top: -10px;">
            </a>
	  <form class="navbar-form navbar-left navbar-input-group" role="search" id="search">
            <div class="form-group">
              <div class="row">
                <div class="col-xs-9" style="padding-left:35px;">
                  <label class="sr-only" for ="searchInput">Search</label>
	          <input type="text" class="form-control " id="searchInput" name="searchInput" placeholder = "Keyword Search" onkeypress="return enterSearch(event,document.getElementById('search'))" >
                </div>
                <div class="col-xs-3" style="padding-left:-0px;" >
                    <button type="button" class="btn btn-default btn-primary " onclick="searchRedirect(document.getElementById('search'))" >
                      <span class ="glyphicon glyphicon-search pull-left btn-xs" ></span>
                    </button>
                </div>  
              </div>
            </div>
          </form>
        </div>
        <div class="collapse navbar-collapse"> 
	  <ul class="nav navbar-nav navbar-left">
            <li><a href="../advancedSearch.html">Advanced Search</a></li>
	    <li><a href="../help.html">Help <span class ="glyphicon glyphicon-question-sign"></span></a></li>
            <li><a href="../contact.html">Contact Us <span class ="glyphicon glyphicon-envelope"></span></a></li> 
	  </ul>
          <ul class ="nav navbar-nav navbar-right">
            <sec:authorize access="isAnonymous()">
                <li><a href="../login">Login <span class ="glyphicon glyphicon-log-in"></span></a></li>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_USER')">
                <li><a href="../logout">Logout <span class ="glyphicon glyphicon-log-out"></span></a></li>
                <li><a href="../accountPage"><span class ="glyphicon glyphicon-user"></span> ${currentUser.firstName} ${currentUser.lastName}</a></li>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                <li><a href="../logout">Logout <span class ="glyphicon glyphicon-log-out"></span></a></li>
                <li><a href="../admin"><span class ="glyphicon glyphicon-user"></span> ${currentUser.firstName} ${currentUser.lastName}</a></li>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <li><a href="../cart"><span class ="glyphicon glyphicon-shopping-cart"></span>(${currentUser.cart.size()}) </a></li>
                </sec:authorize>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div><!--/.navbar--->
	<div id="sidebar-wrapper">
      <ul class="nav nav-pills nav-stacked" id="sidebar-nav">
        <li class="active"><a href="../admin.html">Admin Dashboard</a></li>      
        <li><a data-target="#" href="../admin.html" class="dropdown-toggle" data-toggle="dropdown">User Directory Options <span class="caret"></span></a>
		<ul class="dropdown-menu" role="menu" style="margin-left:150px;margin-top:-62px;">
				  <li><a href="#addUser" onclick="getAddUser()">Add User</a></li>
				  <li><a href="#editUser" onclick="getEditUser()">Edit User</a></li>
				  <li><a href="#deleteUser" onclick="getDeleteUser()">Delete User</a></li>
		</ul>
		</li>
        <li><a href="../admin.html" data-target="#" data-toggle="dropdown" class="dropdown-toggle">Database Options <span class="caret"></span></a>
		<ul class="dropdown-menu" role="menu" style="margin-left:150px;margin-top:-62px;">
				  <li><a href="#">Add Item</a></li>
				  <li class="divider"></li>
				  <li><a href="#">Import outside DB</a></li>
		</ul>
        </li>
      </ul>
    </div>
        <div id="page-content-wrapper">
             <form role="form" method="POST" action="../admin/editItem">
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
            <h1>Edit Item Form</h1>
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
                        <input type="hidden" name= "key" value="${book.key}">
                        <input type="hidden" name = "authorKey" value="${book.author.key}">
                        <button type="submit" class="btn btn-primary">Edit Item</button>
		</fieldset> 
      </form>
        </div>
            <div class="container">    
      <!-- FOOTER -->
      <footer>        
        <p class="pull-right"><a href="#">Back to top</a></p>
        <p class="pull-left">&copy; 2014 Live Free or Malloc.</p>
      </footer>
    </div>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="../bootstrap-3.2.0-dist/js/bootstrap.min.js"></script><script src="../assets/lfomjslib.js"></script>
    </body>
</html>
