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
      body{
        margin:150px 75px 10px;	
      }
      .container search-results img{
        margin-left: 15%;
      }
      .container search-results p{
        margin-left: 15%;
      }

      #page-content-wrapper{
        position: absolute;
        border: solid 2px black;
        border-radius: 5px;
        background-color: #f9f9f9;
        width: 85%;
        z-index: -1;
        margin-top: -50px;
        left: 185px;
      }
      div.footer{
        position: fixed;
        bottom: 0;
        left: 172px;
    }
    
    #title{
        margin-left: 25px;
    }
      
      
    </style>
</head>
<body>
    <jsp:include page="common/toolbar.jsp"/>
	<div id="sidebar-wrapper">
      <ul class="nav nav-pills nav-stacked" id="sidebar-nav">
        <li><a href="#sortByTitle" onclick="sortBy('title','${searchInput}')">Sort by Title</a></li>      
        <li><a href="#sortByAuthor" onclick="sortBy('author','${searchInput}')">Sort by Author</a></li>
        <li><a href="#sortByPublishDate" onclick="sortBy('publishDate','${searchInput}')">Sort by Publish Date</a></li>
        <li><a href="#sortByMostPopular" onclick="sortBy('mostPopular','${searchInput}')">Sort by Most Popular</a></li>
        <li><a href="#sortByNewest" onclick="sortBy('newest','${searchInput}')">Sort by Newest</a></li>
      </ul>
    </div>
	<div id="page-content-wrapper">
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

