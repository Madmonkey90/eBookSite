<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation" style="margin-bottom:320px;">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
            <a class="navbar-brand" href="mainpage.html">
                <img alt="Brand" src="assets/lfom.png" style="margin-top: -10px; ">
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
            <li><a href="advancedSearch.html">Advanced Search <span class ="glyphicon glyphicon-search"></span></a></li>
	    <li><a href="help.html">Help <span class ="glyphicon glyphicon-question-sign"></span></a></li>
            <li><a href="contact.html">Contact Us <span class ="glyphicon glyphicon-envelope"></span></a></li>
            <li class="dropdown">
                <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" style="background: none; margin-top: 8px; border: none" data-toggle="dropdown"  aria-expanded="true">
                <li><a href="about.html" style="color: #ffffff">About <span class ="glyphicon glyphicon-question-sign" style="color: #ffffff"></span></a></li> 
                <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                  <li role="presentation"><a role="menuitem" tabindex="-1" href="about.html">About Us</a></li>
                  <li role="presentation"><a role="menuitem" tabindex="-1" href="help.html">Help</a></li>
                  <li role="presentation"><a role="menuitem" tabindex="-1" href="policy.html">Policy Page</a></li>
                </ul>
            </li>
            
	  </ul>
          <ul class ="nav navbar-nav navbar-right">
            <sec:authorize access="isAnonymous()">
                <li><a href="login">Login <span class ="glyphicon glyphicon-log-in"></span></a></li>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_USER')">
                <li><a href="logout">Logout <span class ="glyphicon glyphicon-log-out"></span></a></li>
                <li><a href="accountPage"><span class ="glyphicon glyphicon-user"></span> ${currentUser.firstName} ${currentUser.lastName}</a></li>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                <li><a href="logout">Logout <span class ="glyphicon glyphicon-log-out"></span></a></li>
                <li><a href="admin"><span class ="glyphicon glyphicon-user"></span> ${currentUser.firstName} ${currentUser.lastName}</a></li>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <li><a href="cart"><span class ="glyphicon glyphicon-shopping-cart"></span>(${currentUser.cart.size()}) </a></li>
                </sec:authorize>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div><!--/.navbar--->
    
    
    
    
