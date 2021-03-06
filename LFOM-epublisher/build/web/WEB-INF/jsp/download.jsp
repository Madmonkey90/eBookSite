<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>ePublisher Web Site</title>
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/js/skel.min.js"></script>
        <script src="assets/js/skel-layers.min.js"></script>
        <script src="assets/js/init.js"></script>
        <noscript>
                <link rel="stylesheet" href="assets/css/skel.css" />
                <link rel="stylesheet" href="assets/css/style.css" />
                <link rel="stylesheet" href="assets/css/style-xlarge.css" />
        </noscript>
    </head>
    <body id="top">
    <!-- Header -->
        <header id="header" class="skel-layers-fixed">
                <h1><a href="index">LFOM ePub</a></h1>
                <nav id="nav">
                        <ul>
                                <li><a href="index">Home</a></li>
                                <li><a href="download?id=1&userId=2">Download</a></li>
                                <li><a href="#" class="button special">Sign Up</a></li>
                        </ul>
                </nav>
        </header>

    <!-- Main -->
        <section id="main" class="wrapper style1">
                <header class="major">
                        <h2>Your Download:</h2>
                        <img src="${imgPath}" alt="Book cover" />
                        <p>Select your file type</p>
                        <form method="POST">
                            <c:choose>
                                <c:when test="${!empty fileTypes}">
                                    <select name="format" id="typeList"
                                            style="width:10%;min-width:100px;margin-bottom:5px;margin-left:45%">
                                        <c:forEach items="${fileTypes}" var="t">
                                            <option value="${t}">${t}</option>    
                                        </c:forEach>
                                    </select>
                                    <input type="submit" class="button" value="Download Now" />
                                </c:when>
                        </form>
                                <c:otherwise>
                                    <p>No download options available.</p>
                                    <p>Like to be notified when this book is available? <a href="holdBook?userId=${userId}&bookId=${id}" class="button">Place a Hold</a></p>
                                </c:otherwise>        
                            </c:choose>
                        
                </header>
                <div class="container">
                        
                </div>
        </section>

    <!-- Footer -->
        <footer id="footer">
            <div class="container">
                    <div class="row double">
                            <div class="6u">
                                    <div class="row collapse-at-2">
                                            <div class="6u">
                                                    <h3>Related Genres:</h3>
                                                    <ul class="alt">
                                                            <li><a href="#">Historical Fiction</a></li>
                                                            <li><a href="#">Diesel Punk</a></li>
                                                            <li><a href="#">Suus</a></li>
                                                            <li><a href="#">Humor</a></li>
                                                    </ul>
                                            </div>
                                            <div class="6u">
                                                    <h3>Related Authors:</h3>
                                                    <ul class="alt">
                                                            <li><a href="#">H. P. Lovecraft</a></li>
                                                            <li><a href="#">Henry Darger</a></li>
                                                            <li><a href="#">Italo Calvino</a></li>
                                                            <li><a href="#">Vladimir Nabokov</a></li>
                                                    </ul>
                                            </div>
                                    </div>
                            </div>
                        <div class="6u">
                                <h2>Our mission</h2>
                                <p>We own the rights to a bunch of works. We are really good at making money off of them. We promise that we will provide quality works at slightly less than ridiculous prices.</p>
                                <ul class="icons">
                                        <li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
                                        <li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
                                        <li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
                                        <li><a href="#" class="icon fa-linkedin"><span class="label">LinkedIn</span></a></li>
                                        <li><a href="#" class="icon fa-pinterest"><span class="label">Pinterest</span></a></li>
                                </ul>
                        </div>
                    </div>
                <ul class="copyright">
                        <li>&copy; 2014 Live Free or Malloc. All rights reserved.</li>
                        <li>Design: <a href="http://templated.co">TEMPLATED</a></li>
                </ul>
            </div>
        </footer>
    </body>
</html>
