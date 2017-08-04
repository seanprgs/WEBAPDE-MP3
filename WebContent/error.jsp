<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title> niche </title>
<link href="static/css/styles.css" rel="stylesheet" type="text/css" > 
<link href="static/css/lib/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link rel = "icon" href = "static/res/icon.png">
<script src = "static/js/lib/jquery-min.js"> </script>
<script src = "static/js/lib/jQueryRotate.js"> </script>
<script src = "static/js/index.js"> </script>

</head>
<body>
    <div id = "background">    
    </div>
    <div id = "overlay"> 
    </div>
    
    <div id = "viewport">
        <div class = "nav-bg"> </div>
         <nav class = "nav-bar"> 
            <div class = "nav-left">
                <form class = "nav-home" action = "view-public">
                    <button class = "nav-home" type = "submit"> 
                        <img class = "nav-main-icon" src ="static/res/icon.png"> 
                        <span class = "nav-item nav-title"> niche </span> 
                    </button>
                </form>
                </a>
            </div>
            
            <div class = "nav-center-wrapper">
                <div class = "nav-center"> 
                    <input class = "nav-item nav-field" type = "text" placeHolder = "Search by tags">
                    <button id="search">
                        <i class="nav-item nav-icon fa fa-search fa-21"> </i> 
                    </button>
                </div>
            </div>
            
            <div class = "nav-right">
                <c:choose>
            		<c:when test = "${empty sessionScope.sessionuser.username}" >
            			<div class = "nav-user">
		                    <div class = "user nav-item nav-link"> 
		                        login
		                        <i class="nav-icon fa fa-user-circle fa-1x"> </i> 
		                    </div>
		                    <div class = "popup-form-wrapper"> 
		                        <form onsubmit = "return validateLogin()" name = 'login' class = "popup-form" action="login" method="POST">
		                            <label class = "popup-label"> username</label>
		                            <input class = "popup-field" name = "username" type="text"> <br>
		                            <label class = "popup-label"> password</label>
		                            <input class = "popup-field" name = "password" type="password"> <br>
		                            <input class = "popup-button" type='submit' value = "Login"> <br>
		                            <input class = "popup-check" name = "remember" type="checkbox"> Remember me <br>
		                        </form>
		                        <form name = 'register' class = "popup-form" action = "register.html">
		                            <button type = "submit" class = "popup-link">
		                                New here? Click here to register.
		                            </button>
		                        </form>
		                    </div>
		                </div>
            		</c:when>
            		<c:otherwise>
            			 <div class = "nav-user">
							<div class = "user nav-item nav-link"> 
		                        ${sessionScope.sessionuser.username}
		                        <i class="nav-icon fa fa-user-circle fa-1x"> </i> 
		                    </div>
		                    <div class = "popup-form-wrapper logout-form"> 
		                        <div class = "popup-form">
		                            <form class = "popup-row-form" action="profile">
		                                <button type="submit" class = "popup-setting-button" value="Profile">
		                                    <i class = "fa fa-user-o"> </i> Profile 
		                                </button>
		                            </form>
		                            <form action = "logout" class = 'popup-row-form'>
		                                <button type = "submit" class = "popup-setting-button" value = "">
		                                    <i class = "fa fa-sign-out"></i> Logout 
		                                </button>
		                            </form>
		                        </div>
		                    </div>
		                </div>
            		</c:otherwise>
				</c:choose>  
                <form id = "about" action="about" class = "nav-item nav-link">
                    <button class = "about-icon">
                        <i class="nav-icon fa fa-question-circle-o"> </i>
                    </button>
                </form>
            </div>
       	</nav>
             <div class = "main-content-container">
                 <c:choose>
            		<c:when test = "${error == 'restricted-access-error'}" >
		                 <div class = "error-message-container">
		                     <div class = "error-content">
		                        <i class = "error-small-icon fa fa-lock"> </i>
		                         <i class = "error-large-icon fa fa-photo"> </i>
		                     </div>
		                     Oops! It seems like you don't have access to this photo. 
		                     <br>
		                     <a class = "error-back" onclick = "window.history.back()"> <i class = "fa fa-arrow-left"> </i>
		                         Back </a>
		                </div>
		            </c:when>
		            <c:when test = "${error == 'db-connection-error' }">
		                 <div class = "error-message-container">
		                     <div class = "error-content">
		                        <i class = "error-small-icon fa fa-close"> </i>
		                         <i class = "error-large-icon fa fa-database"> </i>
		                     </div>
		                     Oops! There seems to be a problem with the database.
		                     <br>
		                     <a class = "error-back" onclick = "window.history.back()"> <i class = "fa fa-arrow-left"> </i>
		                         Back </a>
		                </div>
		            </c:when>
                </c:choose>
            </div>
        </div>
    </body>
</html>
