<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%><%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    
    <title> niche </title>
    <link rel = "icon" href = "static/res/icon.png">
    
    <!-- CSS LIBRARIES USED -->
    <link href="static/css/lib/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- JS LIBRARIES USED -->
    <script src = "static/js/lib/jquery-min.js"> </script>
    <script src = "static/js/lib/jQueryRotate.js"> </script>
    
    <!-- CSS MAIN -->
    <link href="static/css/styles.css" rel="stylesheet" type="text/css" > 

    <!-- JS MAIN -->
    <script src = "static/js/index.js"> </script>

</head>
    
<body>
    <!-- BG AND OVERLAY -->
    <div id = "background"> </div>
    <div id = "overlay"> </div>
    
    <!-- VIEWPORT START -->
    <div id = "viewport">
        <!-- NAVIGATION BAR -->
        <c:import url="nav-bar.jsp"></c:import>
        
        <!-- MAIN CONTENT START -->
        <div class = "main-content-container">
            <!-- ABOUT-CONTAINER -->
            <div class = "about-container">
                <!-- ABOUT BACKGROUND -->
                <div class = "about-bg"> </div>
                <div class = "about-info">
                    <h1 class = "about-title">
                        About niche.
                    </h1>

                    <p class = "about-defn">  
                        <b> niche (n.) </b> 
                        a place, employment, status, or activity for which a person or thing is best fitted 
                    </p>
                    <p class = "about-desc" style="text-align:justify"> 
                        "niche." was developed in accordance to the specifications of the second Machine Problem for WEBAPDE. The group was tasked to create a web application that allows its users to share images publicly and privately to a chosen set of users with the use of various web development tools such as HTML, CSS, JS, JSP EL, and Servlets. 
                    </p>
                    <p class = "about-passed"> Passed by: <br>
                        <label class = "about-member"> Paragas, Sean </label> <br>
                        <label class ="about-member"> Ticug, Jonal Ray </label> <br>
                        <label class ="about-member"> Gaba, Janelle Marie </label>
                    </p>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
