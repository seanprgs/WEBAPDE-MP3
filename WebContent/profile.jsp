<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    
    <title> niche </title>
    <link rel = "icon" href = "static/res/icon.png">
    
    <%-- CSS LIBRARIES USED --%>
    <link href="static/css/lib/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <%-- JS LIBRARIES USED --%>
    <script src = "static/js/lib/jquery-min.js"> </script>
    <script src = "static/js/lib/jQueryRotate.js"> </script>
    
    <%-- CSS MAIN --%>
    <link href="static/css/styles.css" rel="stylesheet" type="text/css" > 

    <%-- JS MAIN --%>
    <script src = "static/js/index.js"> </script>
</head>
    
<body>
    <%-- BG AND OVERLAY --%>
    <div id = "background"> </div>
    <div id = "overlay"> </div>
    
    <%-- VIEWPORT START --%>
    <div id = "viewport">
        <%-- NAVIGATION BAR --%>
        <jsp:include page="nav-bar.jsp"/>
        
        
        <div class = "main-content-container">
            <div class = "profile-info-container">
                 <p class = "profile-label profile-username-label">
                    @${user.username}
                 </p>
                 
                 <p class = "profile-label profile-description-label">
                    "${user.description}"
                 </p>
             </div>
            
             <div class = "profile-post-container">
                <div class = "profile-posts post-container">
                    <c:forEach items="${photos}" var="item">
	                   <%-- POST --%>
                       <div class = "post" id = "${item.photoid}"> 
                            <%-- POST IMAGE --%>
                            <img class = "post-image" src = "${item.path}" />

                            <%-- POST VISIBILITY START --%>
                            <div class = "post-visibility" >  
                                <c:choose>
                                    <c:when test = "${item.visible}">
                                        public
                                        <i class = "visible-icon fa fa-globe"></i>	
                                    </c:when>
                                    <c:otherwise>
                                        private <i class = "visible-icon fa fa-lock"> </i>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <%-- POST VISIBILITY END --%>
                        </div>
	               </c:forEach>
               </div>
             </div>
             
             </div>
        </div>  
    </body>
</html>