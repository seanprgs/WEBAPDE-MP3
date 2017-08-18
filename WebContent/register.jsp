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
    <%-- BACKGROUNDS AND OVERLAYS --%>
    <div id = "background"> </div>
    <div id = "overlay"> </div>
    
    <%-- VIEWPORT START --%>
    <div id = "viewport">
        <jsp:include page = "nav-bar.jsp"/>
        <%-- MAIN CONTENT START --%>
        <div class = "main-content-container">
            <div class = "reg-bg"> </div>
            <%-- REGISTRATION CONTAINER START --%>
            <div class = "reg-container">
                <%-- REGISTRATION TITLE --%>
                <p class = "reg-title"> Registration </p>
                
                <%-- REGISTRATION FORM START --%>
                <form class = "reg-form" action="register" method="POST" onsubmit = "return validateRegistration()">
                    
                    <%-- REGISTER ROW: USERNAME --%>
                    <div class = "reg-row">  
                        <label class = "reg-label" > username </label>
                        <input name = "username" class = "reg-field-username reg-field" type="text"> <br>  
                    </div>
                    
                    <%-- REGISTER ROW: PASSWORD --%>
                    <div class = "reg-row">  
                        <label class = "reg-label"> password </label>
                        <input name = "password" class = "reg-field-pass reg-field" type="password">
                    </div>
                    
                    <%-- REGISTER ROW: DESCRIPTION--%>
                    <div class = "reg-row">
                    	<label class = "reg-label"> description</label>
                        <textarea class = "reg-area" name = "description" placeholder="optional: only 250 characters" oninput="charLimit(this.form.description, wordCount, 250);">
                        </textarea>
                        <label class = "reg-area-update" value = "0"> 0/250 </label> 
                        <label class = "warning"> </label>
                        <br>
                    </div>
                    <%-- REGISTER SUBMIT --%>
                    <input class = "reg-submit" value = "register" type = "submit">
                </form>
                <%-- REGISTRATION FORM END --%>
             </div>
            <%-- REGISTRATION CONTAINER END--%>
        </div>
        <%-- MAIN CONTENT END --%>
    </div>
    <%-- VIEWPORT END --%>
</body>
</html>
