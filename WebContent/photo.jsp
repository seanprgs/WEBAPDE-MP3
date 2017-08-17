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
            		<c:when test = "${empty sessionScope.sessionuser}" >
            			<div class = "nav-user">
		                    <div class = "user nav-item nav-link"> 
		                        login
		                        <i class="nav-icon fa fa-user-circle fa-1x"> </i> 
		                    </div>
		                    <div class = "popup-form-wrapper"> 
		                        <form name = 'login' class = "popup-form" action="login" method="POST" onsubmit = "return validateLogin()">
		                            <label class = "popup-label"> username</label>
		                            <input class = "popup-field" name = "username" id = "login-username" type="text"> <br>
		                            <label class = "popup-label"> password</label>
		                            <input class = "popup-field" name = "password" id = "login-password" type="password"> <br>
		                            <p class = "popup-label warning"> </p> <br>
		                            <div class = "popup-row"><input class = "popup-button" type='submit' value = "login"> <br>
		                            </div>
                                    <div class = "popup-row">
                                    <input class = "popup-check" name = "remember" type="checkbox"> remember me <br>
                                    </div>
		                        </form>
		                        <form class = "popup-form" action = "register.html">
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
		                            <form class = "popup-row-form" action="profile" method="GET">
		                            	<input type = "hidden" name = "user" value = "${sessionScope.sessionuser.userid}">
		                                <button type="submit" class = "popup-setting-button">
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
                <form id = "about" action = "about" class = "nav-item nav-link">
                    <button class = "about-icon">
                        <i class="nav-icon fa fa-question-circle-o"> </i>
                    </button>
                </form>
            </div>
       	</nav>
         <div class = "main-content-container">
             <a onclick="window.history.back()" class = "back">
                <i class = "fa fa-arrow-left"> </i>    
                back
             </a>
             
             <div class = "photo-container"> 
                 <div class = "image-container">
                    <img class = "image" src ="${photo.path}">
                 </div>
                     <div class = "info-container">
              		<a class = "photo-name" href = "profile?=${photo.user.userid}"> <label class = "photo-name">@${photo.user.username} </label> </a> 
                     <label class = "photo-title"> ${photo.title} </label>
                     <label class = "photo-desc"> ${photo.description} </label>
					
					<div class = "tag-container">
						<i class = "fa fa-user">
							<c:forEach items = "${photo.hasAccess}" var = "item"> 
								<a class = "tag" href = "profile?user=${item.userid}">
									@${item.username}
								</a>
							</c:forEach>
						</i>
					</div>
					
					
                    <div class = "tag-container">
							tags:
                         	<c:forEach items = "${photo.tags}" var = "item">
	                         	<a class = "tag"> 
	                                ${item.tag}
	                            </a>
                         	</c:forEach>
                     </div>
                     
                     <c:choose>
						<c:when test = "${sessionScope.sessionuser.userid == photo.user.userid}">
		                    <div class = "tag-container">
                        		<form action="addtag" method="POST">
			                    <input type="text" class = "users" name="tag">
			                      <input type="hidden" name="photoid" value="${photo.photoid}"/>
			                        <button class = "share" type="submit">
			                            Add tag 
			                    </button>
							</form>
                    		</div>   
						</c:when>
					</c:choose>
					
                     <c:choose>
						<c:when test = "${not empty sessionScope.sessionuser}">
		                    <div class = "tag-container">
                        					
			                    <input list="users" class = "users" name="users">
			                        <button class = "share">
			                            share 
			                    </button>
                    		</div>   
						</c:when>
					</c:choose>
                 </div>

             </div>
        </div>
        </div>
    </body>
</html>
