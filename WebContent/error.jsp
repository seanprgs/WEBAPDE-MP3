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
        <%-- MAIN CONTENT START --%>
        <div class = "main-content-container">
            <%-- ERROR MESSAGE CONTAINER START --%>
            <div class = "error-message-container">
                <%-- ERROR CONTENT START --%>
                <div class = "error-content">
                    <%-- SWITCH CASE TO WHAT ERROR START --%>
                    <c:choose>
                        <c:when test = "${error == 'restricted-access-error'}" >
                            <%-- ERROR ICON CONTAINER START --%>
                            <div class = "error-icon-container">
                                <i class = "error-small-icon fa fa-lock"> </i>
                                <i class = "error-large-icon fa fa-photo"> </i>
                            </div>
                            Oops! It seems like you don't have access to this photo. 
                        </c:when>
                        
                        <c:when test = "${error == 'db-connection-error' }">
                            <div class = "error-icon-container">   
                                <i class = "error-small-icon fa fa-close"> </i>
                                <i class = "error-large-icon fa fa-database"> </i>
                            </div>
                            Oops! There seems to be a problem with the database.
                        </c:when>
                    </c:choose>
                    <%-- SWITCH CASE TO WHAT ERROR END --%>
                    
                    <%-- BACK BUTTON START --%>
                    <br>
                    <a class = "error-back" onclick = "window.history.back()"> 
                         <i class = "fa fa-arrow-left"> </i>
                         Back 
                    </a>
                    <%-- BACK BUTTON END --%>
                </div>
                <%-- ERROR CONTENT END --%>
            </div>
            <%-- ERROR MESSAGE CONTAINER END --%>
        </div>
        <%-- MAIN CONTENT END --%>
    </div>
</body>

</html>
