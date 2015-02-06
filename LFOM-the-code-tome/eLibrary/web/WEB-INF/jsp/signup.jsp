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
      body{
        margin:150px 75px 10px;	
      }
      input{
        float:right;
      }
      div.footer{
        position:absolute;
        bottom:0;
        left:0;
      }
      
    </style>
</head>

    <jsp:include page="common/toolbar.jsp"/>
    <div class="container">
      
      <form role="form" id="signupForm">
        <fieldset style = " 
                background-color: #f9f9f9;
                width: 70%;
                min-width: 198px;
                padding: 10px;
                border: 1.5px solid black;
                border-radius: 5px;
                box-shadow: 0px 0px 10px 4px rgba(119, 119, 119, 0.75);
                -moz-box-shadow: 0px 0px 10px 4px rgba(119, 119, 119, 0.75);
                -webkit-box-shadow: 0px 0px 10px 4px rgba(119, 119, 119, 0.75);
                ">
          
            <table class="table table-bordered">
                <div class="col-md-offset-1">
                    <div class="page-header" font-weight: bold>
                <h2>Signup <small>Fill in all sections</small></h2>
            </div>
            <form class="form-horizontal" role="form">
            <div class="form-group">
              <div class="row">
                  <label for="fname" class="col-md-2 col-md-offset-1">First Name</label>
                  <div class="col-md-4 nopadding">
                    <input type="text" class="form-control" id="fname" name="fname">
                  </div>
              </div>
              <div class="row">
                  <label for="lname" class="col-md-2 col-md-offset-1">Last Name</label>
                  <div class="col-md-4 nopadding">
                    <input type="text" class="form-control" id="lname" name="lname">
                  </div>
              </div>  
              <div class="row">
                  <label for="password" class="col-md-2 col-md-offset-1">Password</label>
                  <div class="col-md-4 nopadding">
                     <input type="password" class="form-control" id="password" name="password">
                  </div>
               </div>  
               <div class="row">
                    <label for="cpassword" class="col-md-2 col-md-offset-1">Confirm Password</label>
                    <div class="col-md-4 nopadding">
                        <input type="password" class="form-control" id="cpassword" name="cpassword">
                    </div>
                </div>
                <span id="errPass" style="color:red"></span><br> 
                <div class="row">
                    <label for="email" class="col-md-2 col-md-offset-1">Email</label>
                    <div class="col-md-4 nopadding">
                        <input type="text" class="form-control" id="email" name="email">
                    </div>
                </div>
                <div class="row">
                    <label for="cemail" class="col-md-2 col-md-offset-1">Confirm Email</label>
                    <div class="col-md-4 nopadding">
                        <input type="text" class="form-control" id="cemail" name="cemail">
                    </div>
                </div>                   
                <span id="errEmail" style="color:red"></span><br><br>
                    <button type="button" class="btn btn-primary" onclick="signUp(document.getElementById('password'),
                        document.getElementById('cpassword'),
                        document.getElementById('email'),
                        document.getElementById('cemail'),
                        document.getElementById('signupForm'))">Submit</button>
                        <span style="color:red">${err}</span>
		</fieldset> 
      </form>
    </div>
    <div class="footer">            
        <jsp:include page="common/footer.jsp"/>    
    </div>
</html>

