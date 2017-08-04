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
             <div class = "menu-container">
                Search results for ${tag}
               </div>
	            <div class = "post-container">
	             <c:forEach items="${photos}" var="item">
	             	<div class = "post">
	             		<a class = "post-title-link" href = "photo?photo=${item.photoid}"> 
	             			<p class = "post-title">
	             				${item.title}
	             				<c:choose>
			             			<c:when test = "${item.visible}">
			             				<i class = "visible-icon fa fa-globe"></i>	
			             			</c:when>
			             			<c:otherwise>
			             				<i class = "visible-icon fa fa-lock"></i>
			             			</c:otherwise>
			             		</c:choose>
	             			</p>
	             		</a>
	             		<a class = "post-username-link" href = "profile?user=${item.user.userid}">
	             			<p class = "post-username">
	             				@${item.user.username}
	             			</p>
	             		</a>
	             		
	             		<p class = "post-desc"> ${item.description} </p>
	
	             		<img class = "post-image" src = "${item.path}"/>
	
						<div class = "tag-container">
							<c:choose>
							<c:when test = "${not empty item.hasAccess}">	
							
								<i class = "fa fa-user"></i>		
								<c:forEach items = "${item.hasAccess}" var = "userItem"> 
									<a class = "tag" href = "profile?user=${userItem.userid}">
										@${userItem.username}
									</a>
								</c:forEach>
							</c:when>
							</c:choose>
						
						</div>
						
	             		<div class = "tag-container">
	             			tags:
	             			<c:forEach items = "${item.tags}" var = "tagItem">
	             				<a class = "tag">
		                        	${tagItem.tag}
		                    	</a>
	             			</c:forEach>
	             		</div>
	             	</div>
	             </c:forEach>
	           </div>
	             
	             
	            <div class = "more-container">
	                <p class = "more-message">
	                    View more photos
	                    <i class = "fa fa-arrow-down"></i>
	                </p>
	            </div>
	            
	            <c:choose>
	           		<c:when test = "${empty sessionScope.sessionuser}" >
			        </c:when>
			        <c:otherwise>
			        	<div class = "upload-container">
				            <div class = "upload">
				                <form action = "post" class = "upload-form" method = "POST" onsubmit="return validateUpload()">
				                    
				                    <label class = "upload-title"> upload photo </label>
				                    <br>
				                    <img id = "preview" src = "#" alt = "preview">
				                    
				                    <div class = "upload-row">
				                        <label class = "upload-label">
				                        choose file
				                        </label>
				                        <input name = "file" id = "file" class = "upload-pic-field" type = "file" accept = ".jpeg, .jpg, .png, .tif, .tiff">
				                        <label for="file"> <i class = "fa fa-image"> </i> .JPEG, .PNG, .TIFF files </label>  
				                    </div>
				                    <div class = "upload-row">
				                        <label class = "upload-label">
				                            title </label>
				                        <input name = "title" id = "title" class = "upload-field" type ="text">
				                    </div>
				
				                    <div class = "upload-row">
				                        <label  class = "upload-label">
				                        description </label>
				                    <input name = "description" id = "desc" placeholder = "optional" class = "upload-field" type ="text"> 
				                    </div>
				                    
				
				                    <div class = "upload-row">
				                        <label name = "description" class = "upload-label">
				                        visibility </label>
				                        <input id = "pub" name = "visible" class = "box-input" value = "public" class = "upload-box" type = "checkbox">
				                        public
				                        <input id = "pri" name = "visible" class = "box-input" value = "private" class = "upload-box" type = "checkbox">
				                        private
				                    </div>
				
				                    <div class = "upload-row">
				                        <label class = "upload-label">
				                            add tag
				                        </label>                        
				                        <input id="tag-field" class = "upload-field" type ="text"> 
				                        <button id = "add-tag" class = "upload-button" type = "button"> <i class = "fa fa-plus"> </i> </button>
				                    </div>
				
				                    <div class = "upload-row">
				                        <label class = "upload-label"> tags </label>
				                        <div class = "upload-tag-container"> 
				                        </div>
				                    </div>
				                   	 <p id = "warning-upload" class = "popup-label warning"> </p> <br> 
				                    <input type = "hidden" id = "tagNum" name = "tagNum" value = "0">
				                    <button class = "upload-button" type = "submit" id = "upload"> <i class = "fa fa fa-upload"> </i> Upload </button>
				                  </form>
				             </div>
				            <div class = "upload-bg">
				             </div>
				         </div>
				
				        <div class = "add-container">
				            <button class = "add-photo"> <i class = "fa fa-plus"> </i> </button>
				        </div>
			        </c:otherwise>
				</c:choose>
			</div>  
        </div>
    </body>
</html>
