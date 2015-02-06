<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Book Sample Reader</title>
    <!-- Bootstrap core CSS -->
    <link href="bootstrap-3.2.0-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/css/elibraryCommon.css" rel="stylesheet">
    <style type="text/css">
      body{
        min-height: 800px;
      }
      #area {
          height: 595px;
          margin: 5 auto;
          -moz-box-shadow:      inset 10px 0 20px rgba(0,0,0,.1);
          -webkit-box-shadow:   inset 10px 0 20px rgba(0,0,0,.1);
          box-shadow:           inset 10px 0 20px rgba(0,0,0,.1);
          background-color: white;
          padding: 70px;
        }    
    </style>
</head>
<body>
    <jsp:include page="common/toolbar.jsp"/>
    <!--content --->
    <div class ="row">
        <div class="col-md-12" id="area"></div>
    </div>
    <!--book controls --->
    <div class="row">
        <div class="col-sm-6">
            <img class="pull-left" src="assets/UILeft.png" alt = "prevPage" onClick = "Book.prevPage()" >
        </div>
        <div class="col-sm-6" onclick="Book.nextPage();">
            <img class="pull-right" src="assets/UIRight.png" alt="nextPage" onClick="Book.nextPage()" >
        </div>
    </div>
    <!-- FOOTER -->
    <footer>
      <p class="pull-right"><a href="#">Back to top</a></p>
      <p class="pull-left">
          &copy; 2014 Live Free or Malloc. 
          In browser reader is provided via Epub.js library 
          under the FreeBSD license. ${sampleBook.sampleSrc}
      </p>
    </footer>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="bootstrap-3.2.0-dist/js/bootstrap.min.js"></script>     
    <script src="assets/lfomjslib.js"></script>
    <!-- Load epub.js !-->
    <script src="ePUBjs/epub.min.js"></script>
    <!-- zip.js is a dependency for epub!-->
    <script src="ePUBjs/libs/zip.min.js"></script>  
    <!-- set required dependency folder for epub.js!-->
    <script>
        EPUBJS.filePath = "ePUBjs/libs/";
        var Book = ePub({restore: true }); 
        Book.open("${sampleBook.sampleSrc}");
        Book.renderTo("area");     
        function bookKeyboardNav(event)
        {
            if (event.keyCode === 39)
                Book.nextPage();
            if (event.keyCode === 37)
                Book.prevPage();
        }
        window.onkeydown = bookKeyboardNav;
    </script>
</body>
</html>
