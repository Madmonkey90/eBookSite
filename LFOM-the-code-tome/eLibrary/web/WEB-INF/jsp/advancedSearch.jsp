<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
      input{
        float:right;
      }
      body{
          margin-left: -10px;
      }
      
      div.footer{
        position: fixed;
        bottom: 0;
    }
    </style>
</head>
<body>
    <jsp:include page="common/toolbar.jsp"/>
    <div class="container">
      <h1>Advanced search<br><small> Fill in one or more fields</small></h1>
      <form name="advanced" role="form" method="POST">
        <fieldset style = " 
                background-color: #F0F0F0;
                max-width: 400px; 
                min-width: 295px;
                padding: 7px;
                border: 1.5px solid black;
                border-radius: 5px;
                box-shadow: 0px 0px 10px 4px rgba(119, 119, 119, 0.75);
                ">
			Title: <input type="text" name="title"><br><br>
			Author: <input type="text" name="author"><br><br>
			Publisher: <input type="text" name="publisher"><br><br>
			ISBN: <input type="text" name="isbn"><br><br>
                        Publication Year: <input type="number" name="date"><br><br>
                        <input type="hidden" name="searchInput">
                        <button type="button" class="btn btn-primary" onclick="advancedSearch()">Search </button>

                        <c:if test='${status eq "empty"}'>
                        <div class="alert alert-danger" style="margin-top:10px">
                            <a href="login.html" class="alert-link">Blank Search! Please try again. </a>
                        </div>                              
                        </c:if>  
		</fieldset>
      </form>
    </div>
    
    <div class="footer">
        <jsp:include page="common/footer.jsp"/>
    </div>
    </div>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="bootstrap-3.2.0-dist/js/bootstrap.min.js"></script>     <script src="assets/lfomjslib.js"></script>
	<script>
            function advancedSearch()
            {
                var advancedSearchForm = document.forms.advanced;
                var title = advancedSearchForm.title.value;
                advancedSearchForm.title.value = "";
                var author = advancedSearchForm.author.value;
                advancedSearchForm.author.value = "";
                var publisher = advancedSearchForm.publisher.value;
                advancedSearchForm.publisher.value = "";
                var isbn = advancedSearchForm.isbn.value;
                advancedSearchForm.isbn.value = "";
                var date = advancedSearchForm.date.value;
                advancedSearchForm.date.value = "";
                //Only a consolidated search string will be submitted
                advancedSearchForm.searchInput.value = title + " " + author + " " +publisher +" " + isbn + " " + date;
                advancedSearchForm.submit();
            }
	</script>
</body>
</html>
