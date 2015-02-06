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
    <jsp:include page="common/toolbar.jsp"/>
	<div id="sidebar-wrapper">
      <ul class="nav nav-pills nav-stacked" id="sidebar-nav">
        <li class="active"><a href="admin.html">Admin Dashboard</a></li>      
        <li><a data-target="#" href="/admin.html" class="dropdown-toggle" data-toggle="dropdown">User Directory Options <span class="caret"></span></a>
		<ul class="dropdown-menu" role="menu" style="margin-left:150px;margin-top:-62px;">
				  <li><a href="#addUser" onclick="getAddUser()">Add User</a></li>
				  <li><a href="#editUser" onclick="getEditUser()">Edit User</a></li>
				  <li><a href="#deleteUser" onclick="getDeleteUser()">Delete User</a></li>
		</ul>
		</li>
        <li><a href="/admin.html" data-target="#" data-toggle="dropdown" class="dropdown-toggle">Database Options <span class="caret"></span></a>
		<ul class="dropdown-menu" role="menu" style="margin-left:150px;margin-top:-62px;">
				  <li><a href="#addItem" onclick ="getAddItem()">Add Item</a></li>
		</ul>
        </li>
        <li><a href="/admin.html" data-target="#" data-toggle="dropdown" class="dropdown-toggle">Ticket System<span class="caret"></span></a>
		<ul class="dropdown-menu" role="menu" style="margin-left:150px;margin-top:-62px;">
				  <li><a href="#getTicketList" onclick ="getTicketList()">Get Ticket List</a></li>
		</ul>
        </li>
        <li><a href="#rebuildSearchIndex" onclick="rebuildSearchIndex()">Rebuild Search Index</a></li>
      </ul>
    </div>
	<div id="page-content-wrapper">
                <c:if test="${not empty msg}">
                    <h2>${msg}</h2>
                </c:if>
		<h1 style="padding-left:150px">Admin Dashboard</h1>
		<div class="container graph-container">
                    <div class ="well well-lg">
                   <canvas id="topCanvas" width="300" height="300"></canvas>
                    </div>
                </div>
		  <ul class="nav nav-pills">
			<li class="active"><a href="#pageUsageData" onclick="getPageGraph()">Page Usage Data</a></li>
			<li><a href="#searchStats" onclick="getSearchGraph()">Average Search Time (in ms)</a></li>
			<li><a href="#checkoutStats" onclick="getCheckoutGraph()"># of Checkouts per Day</a></li>
                  </ul>
                <br><br>
        </div>
    <jsp:include page="common/footer.jsp"/>
    <script src="assets/Chart.js"></script>
    <script>
$(document).ready(function(){
   $('ul.nav.nav-pills li a').click(function() {			
    $(this).parent().addClass('active').siblings().removeClass('active');			
});
    getPageGraph();
});




    </script>
</body>
</html>
