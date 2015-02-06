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
    <title>User Login Page</title>
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
        margin-top: -100px;
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
      input{
        float:right;
      }
      .alert{
        margin: 0px 0px 50px 50px;
        width: 20%; 
        position: absolute;
        top: 60%;
        left: 35%;
        float: bottom;
      }
      
      div.footer{
          position:absolute;
          bottom:0;
          left: 8%;
      }
      
    </style>
</head>
<body>
    <jsp:include page="common/toolbar.jsp"/>
    <div id="page-content-wrapper">
    <div class="container">
      <form method ="POST" action="<c:url value='/login.do' />">
    <c:if test="${!empty newUser}">
        <div class="alert alert-warning alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert">
                <span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
            <strong>Log in using your email and password.</strong> 
        </div>
    </c:if>
    <c:if test="${!empty error}">
        <div class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert">
                <span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
            <strong>Invalid Credentials!</strong> Please Try Again. 
        </div>
    </c:if>
    <c:if test="${not empty msg}">
        <div class="alert alert-warning alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert">
                <span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
            <strong>${msg}</strong>
        </div>
    </c:if>
    <!--/ New Login Box -->
    <fieldset style = " 
                background-color: #f9f9f9;
                text-align: left; 
                bottom: 15%;
                width: 87%;
                min-width: 150px;
                padding: 5px 5px 0px 5px;
                border: 2px solid grey;
                border-radius: 5px;
                box-shadow: 0px 0px 4px 4px rgba(119, 119, 119, 0.75);
                -moz-box-shadow: 0px 0px 4px 4px rgba(119, 119, 119, 0.75);
                -webkit-box-shadow: 0px 0px 4px 4px rgba(119, 119, 119, 0.75);
                
  ">
        
            
    <table class="table table-bordered">
        <div class="col-md-offset-1">
            <div class="page-header" font-weight: bold>
                <h2>Login <small>Account authentication</small></h2>
            </div>
        </div>
        <form class="form-horizontal" role="form">
        <div class="form-group">
          <div class="row">
              <label for="email" class="col-md-2 col-md-offset-1">Email</label>
              <div class="col-md-4 nopadding">
                <input type="text" class="form-control" id="email" name="username">
              </div>
          </div>
        </div>
        <div class="form-group">
          <div class="row">  
              <label for="password" class="col-md-2 col-md-offset-1">Password</label>
              <div class="col-md-4 nopadding">
                <input type="password" class="form-control" id="pass" name="password">
              </div>
          </div>
        </div>   
          </div>
        </div>
        <div class="form-group">
            <div class="row">
                <div class="col-md-6 col-md-offset-1">
                    <button type="submit" class="btn btn-primary">Sign in</button>
                    <a href="signup.html" class="btn btn-default">Not a member? Sign up!</a> <br></br>
                </div>
            </div>
        </div>
    </table>  
    </fieldset>      
      </form>  
    </div>
    <div class="footer">
        <jsp:include page="common/footer.jsp"/>
    </div>
</body>
</html>
