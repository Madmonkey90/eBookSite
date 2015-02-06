<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta property="og:title" content="${currentBook.title}" />
    <meta property="og:description" content="${currentBook.description}" />
    <meta property="og:image" content="${fn:replace(currentBook.imgSrc,"225x225-75.jpg","600x600-75.jpg")}" />
    <meta property="og:url" content="69.121.220.130:8085/eLibrary/details${currentBook.key}" />
    
    <title>LFOM page</title>
    <!-- Bootstrap core CSS -->
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="bootstrap-3.2.0-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="assets/css/ebookCartouche.css" rel="stylesheet">
    <link href="assets/css/elibraryCommon.css" rel="stylesheet">  
    <link href="assets/css/star-rating.min.css" media="all" rel="stylesheet" type="text/css" />
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="assets/js/star-rating.min.js" type="text/javascript"></script>
    <script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+'://platform.twitter.com/widgets.js';fjs.parentNode.insertBefore(js,fjs);}}(document, 'script', 'twitter-wjs');</script>
    
    
    <style type="text/css">
        #page-content-wrapper{
        position: absolute;
        border: solid 2px black;
        border-radius: 5px;
        background-color: #f9f9f9;
        z-index: -1;
        margin-top: -100px;
      }
      
      #carousel-section{
          position: absolute;
          top: 1300px;
          left: 80px;
      }
 
      div.footer{
          position: fixed;
          bottom: 0;
      }  
      
      #bookdescriptiondiv{
          position: absolute; 
          width: 35%; 
          top: 25%;
          left: 45%;
      }
      #bookdesc{
          display: block;
          position: absolute;
          width: 75%
      }
      #ratings{
          margin-left: 15px;
      }
      
      div.footer{
        position: relative;
        bottom: 0;
        left: 16px;
    }
      
    </style>
  </head>
  <body>
    <div id="fb-root"></div>
    <script>(function(d, s, id) {
      var js, fjs = d.getElementsByTagName(s)[0];
      if (d.getElementById(id)) return;
      js = d.createElement(s); js.id = id;
      js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.0";
      fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'facebook-jssdk'));</script>    
    <jsp:include page="common/toolbar.jsp"/>
      <div class="container">
          <img src="${fn:replace(currentBook.imgSrc,"225x225-75.jpg","600x600-75.jpg")}" align="left" style="margin-top: 80px; margin-left:20px; max-height: 600px; max-width: 600px; float: left; ">
               
                <div class="panel panel-primary">
                    <div class="panel-heading">${currentBook.title} by ${currentBook.author.name}</div>
                <div class="panel-body">
                    <div class="panel panel-primary">
                        <div id = "bookdescriptiondiv">
                                <p id="bookdesc">${currentBook.description}></</p>
                        </div>
                    </div>    
                </div>
            
            <span class="buttons" style="margin-left: 15px;">
              <a href="bookSample${currentBook.key}.html" class="btn btn-lg btn-info">Sample</a>
              <button onclick="window.print()" class="btn btn-lg btn-info">Print</button>
              <sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
                <a href ="addtocart?id=${currentBook.key}" class="btn btn-lg btn-danger">Add To Cart</a>
                <c:choose>
                                    <c:when test="${not onWishlist}">
                                        <a href="addtowishlist?id=${currentBook.key}" class="btn btn-lg btn-primary">Add to Wishlist</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="#removefromwishlist" onclick ="removeFromWishlist(${currentBook.key})" class="btn btn-lg btn-primary">Remove from Wishlist</a>
                                    </c:otherwise>
                                </c:choose>
              </sec:authorize>
              <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <a href="admin/editItem?id=${currentBook.key}" class ="btn btn-lg btn-primary">Edit Item</a>
                    <a href="admin/deleteItem?id=${currentBook.key}" class ="btn btn-lg btn-primary">Delete Item</a>
              </sec:authorize>
              <sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
              <div id = "ratings"> 
               <form name="ratingForm" method ="POST" action="setRating">
                <input id="rate" name="rating" type="text" class="rating" min=0 max=5 step=.5 data-size="md" data-show-clear="false" value ="${currentBook.rating}" >
                <input name="bookID" type="hidden" value="${currentBook.key}">
                <button type="submit" >Submit</button>
               </form>    
              </div>      
              </sec:authorize>
              <sec:authorize access="isAnonymous()">
              <div id = "ratings"> 
               <form name="ratingForm" action="setRating">
                <input id="rate" name="rating" type="text" class="rating" min=0 max=5 step=.5 data-size="md" data-show-clear="false" data-readonly="true" value ="${currentBook.rating}">
               </form>    
              </div>      
              </sec:authorize>
            </span>
     
            <div class="panel-body"> 
                <div class="panel panel-success">
                </div>
                <h3><strong><u>Book Details:</u></strong></h3>
                        <p><strong>Book Title: </strong>${currentBook.title}</p>
                        <p><strong>Author:</strong>${currentBook.author.name}</p>
                        <p><strong>Rating:</strong>${currentBook.rating}</p>
                        <p><strong>Genre:</strong>${currentBook.genre}</p>
                        <p><strong>Publisher:</strong>${currentBook.publisher}</p>
                        <p><strong>Publish date:</strong>${currentBook.publishDate}</p>
                        <p><strong>No. of Pages:</strong>${currentBook.length}</p>
            </div>
                    
              <a class ="btn btn-xs btn-primary"
                 href="mailto:?subject=Book Recommendation&amp;body=I thought you might want to read ${currentBook.title}.  You can read it here http://69.121.220.130:8084/eLibrary/details${currentBook.key}." 
                 title="Share by Email">
                Share By Email
              </a>
              <div class="fb-share-button" data-href="https://developers.facebook.com/docs/plugins/" ></div>                            
                 <a href="https://twitter.com/share" class="twitter-share-button" style="padding:24px;">Tweet</a>
              
                    <div class="panel-body">
                        <div class="panel panel-success">    
              
           
                            
                        </div>
                    </div>
                </div>

                <div class="row" style="padding-top:15px;">
                <div class="contentBackgroundWithAliceBlue" style="background-color:aliceblue;padding:10px;margin-bottom:20px;">
                    <h1>Similar Books <small>You might like these!</small></h1>
                </div>
                    
                <div style="margin-left:auto;margin-right:auto;">
                    <c:forEach items="${similarBooks}" var="book" varStatus="carouselCount"> 

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
                    <div class="rounded" style=";margin-bottom:10px;">
                        <h1> <small>  </small>&nbsp;</h1>
                     </div>
                </div>
        </div>                  
                


    <div class="footer">
        <jsp:include page="common/footer.jsp"/>
    </div>
  </body>
</html>