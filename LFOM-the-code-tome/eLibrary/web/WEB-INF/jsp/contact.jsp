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
      #name{
        width:200px;
      }
      #email{
        width:200px;
      }
      
      div.footer{
        position: fixed;
        bottom: 0;
    }
    </style>
</head>
<body>
    <jsp:include page="common/toolbar.jsp"/>
    <div class="container contentBackgroundWithAliceBlue" style="margin-top:125px;min-width: 280px;">
      <h1>Contact our Dev Team!</h1>
      <p>Enter your name and email below and choose the type of message you wish to send</p>
      <form role="form">
        <div class="form-group">
          <label for="name"></label>
          <div class="input-group">
            <span class="input-group-addon">From:</span>
            <input type="text" class="form-control"  id="name" placeholder="Enter name">
          </div>
        </div>
        <div class="form-group">
          <label for="email"></label>
          <div class="input-group">
            <span class="input-group-addon">Email: </span>
            <input type="email" class="form-control" id="email" placeholder="Enter email">
          </div>
        </div>
        <div class="dropdown">
          <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown">
   Choose message type 
          <span class="caret"></span>
          </button>
          <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
          <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Bug</a></li>
          <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Comment</a></li>
          <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Suggestion</a></li>
          <li role="presentation" class="divider"></li>
          <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Other</a></li>
          </ul>
        </div>
        <div class="form-group">
          <label for="body"></label>
          <input type ="text" class="form-control" id="body" placeholder="Enter message here">
        </div>
        <button type="submit" class="btn btn-success">Send</button>
      </form>
      <br><br><br>
      <h1>Submit a support ticket</h1>
      <p>Please enter a detailed description of your problem</p>
      <form role="form" method ="POST" action="ticketSubmit">
        <div class="form-group">
          <label for="ticketSenderName"></label>
          <div class="input-group">
            <span class="input-group-addon">From:</span>
            <input type="text" class="form-control"  name="ticketSenderName" placeholder="Enter name">
          </div>
        </div>
        <div class="form-group">
          <label for="ticketEmail"></label>
          <div class="input-group">
            <span class="input-group-addon">Email: </span>
            <input type="email" class="form-control" name="ticketEmail" placeholder="Enter email">
          </div>
        </div>
        <div class="form-group">
          <label for="ticketBody"></label>
          <input type ="text" class="form-control" name="ticketBody" placeholder="Enter problem description here">
        </div>
        <button type="submit" class="btn btn-success">Submit</button>
      </form>
    </div>
    <div class="footer">
        <jsp:include page="common/footer.jsp"/>
    </div>
</body>
</html>
