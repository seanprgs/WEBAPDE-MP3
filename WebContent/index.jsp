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
    <script>
	page = 1;
    $(function(){
    	$(window).scroll(function(){
    		if($(window).scrollTop() >= $('.post-container').offset().top + $('.post-container').outerHeight() - window.innerHeight) {
    			$.ajax({
    				url : 'more',
    				data : 'page=' + page,
    				success : function(responseText) {
						for(var i = 0; i < responseText.length; i++) {
							var post = document.createElement('div');
							$(post).addClass('post');
							$(post).attr("id", responseText[i].photoid);
							
    						var img = document.createElement('img');
    						$(img).addClass('post-image');
    						$(img).attr("src", responseText[i].path);
    						
    						var visibility = document.createElement('div');
    						$(visibility).addClass('post-visibility');
    						
    						if(responseText[i].visible) {
    							$(visibility).html("public <i class = 'visible-icon fa fa-globe'> </i>");
    						} else {
    							$(visibility).html("private <i class = 'visible-icon fa fa-lock'> </i>");
    						}
    						
							post.append(img);
							post.append(visibility);
							
							$('.post-container').append(post);
							page += 1;
						}
    				}, 
					
    				beforeSend: function(){
					    $("#loader").show();
					},
					
					complete: function(){
						$("#loader").show();
					}
    			});
    		}
    	});
    });
	
    
	</script>
</head>
    
<body>
    <%-- BG AND OVERLAY --%>
    <div id = "background"> </div>
    <div id = "overlay"> </div>
    
    <%-- VIEWPORT START --%>
    <div id = "viewport">
        <%-- NAVIGATION BAR --%>
        <jsp:include page="nav-bar.jsp"/>
        
        <%-- MAIN CONTENT CONTAINER START --%>
        <div class = "main-content-container">
            <%-- HEADER CONTAINER --%>
            <div class = "header-container"> posts: </div>
            
            <%-- POST CONTAINER --%>
            <div class = "post-container">
                <%-- FOR EACH OF PHOTOS START --%>
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
                 <%-- FOR EACH OF PHOTOS END --%>
            </div>
            <%-- POST CONTAINER END --%>
            <%-- CONDITION TO CHECK IF THERE IS USER START --%>
            <c:choose>
                <%-- CHECK IF THERE IS USER --%>
                <c:when test = "${not empty sessionScope.sessionuser}" >
                    <%-- UPLOAD CONTAINER START --%>
                    <div class = "upload-container">
                        <%-- UPLOAD CONTENT START --%>
                        <div class = "upload">
                            <%-- UPLOAD FORM START --%>
                            <form action = "post" class = "upload-form" method = "POST" enctype="multipart/form-data" onsubmit="return validateUpload()">
                                <%-- UPLOAD MAIN TITLE --%>
                                <label class = "upload-title"> upload photo </label>
                                <br>
                                
                                <img id = "preview" src = "#" alt = "">
                                
                                <%-- UPLOAD FILE INPUT --%> 
                                <div class = "upload-row">
                                    <label class = "upload-label">
                                        choose file
                                    </label>
                                    <input name = "file" id = "file" class = "upload-pic-field" type = "file" accept = ".jpeg, .jpg, .png, .tif, .tiff">
                                    <label for="file"> 
                                        <i class = "fa fa-image"> </i> <span class = "image-message">.JPEG, .PNG, .TIFF files
                                        </span> 
                                    </label>  
                                </div>
                                
                                <%-- UPLOAD TITLE INPUT --%>
                                <div class = "upload-row">
                                    <label class = "upload-label">
                                        title </label>
                                    <input name = "title" id = "title" class = "upload-field" type ="text">
                                </div>
                                
                                <%-- UPLOAD DESCRIPTION INPUT --%>
                                <div class = "upload-row">
                                    <label  class = "upload-label">
                                    description </label>
                                    <textarea  name = "description" id = "desc" placeholder = "optional" class = "upload-field" type ="text" oninput="charLimit(this.form.description, wordCount, 150);">
                                    </textarea>
                                    <label class = "reg-area-update" value = "0"> 0/150 </label>
                                </div>

                                <%-- UPLOAD VISIBILITY --%>
                                <div class = "upload-row">
                                    <label class = "upload-label">
                                    visibility</label>
                                    <div class="toggleWrapper">
                                        <input type="checkbox" id="dn" class="dn">
                                        <label for="dn" class="toggle"><span class="toggle__handler"></span></label>
                                    </div>
                                </div>
                                
                                <%-- UPLOAD ADD TAG --%>
                                <div class = "upload-row">
                                    <label class = "upload-label">
                                        add tag
                                    </label>                        
                                    <div id = "upload-row-addtag">
                                        <input id="tag-field" class = "upload-field" type ="text">
                                        <button id = "add-tag" class = "upload-button" type = "button"> <i class = "fa fa-plus"> </i> </button>
                                    </div>
                                </div>
                                
                                <%-- UPLOAD TAG CONTAINER START --%>
                                <div class = "upload-row">
                                    <label class = "upload-label"> tags </label>
                                    <div class = "upload-tag-container"> 
                                    </div>
                                </div>
                                <%-- UPLOAD TAG CONTAINER END --%>
                                
                                <%-- UPLOAD WARNINGS --%>
                                <p id = "warning-upload" class = "popup-label warning"> </p> <br> 
                                <input type = "hidden" id = "tagNum" name = "tagNum" value = "0">
                                <button class = "upload-button" type = "submit" id = "upload"> <i class = "fa fa fa-upload"> </i> Upload </button>
                            </form>
                            <%-- UPLOAD FORM END --%>
                        </div>    
                        <%-- UPLOAD CONTENT END --%>
                        <div class = "upload-bg"> </div>
                    </div>
                    <%-- UPLOAD CONTAINER END --%>
					
                    <div class = "add-container">
                        <button class = "add-photo"> <i class = "fa fa-plus"> </i> </button>
                    </div>
                </c:when>
            </c:choose>
            <%-- CONDITION TO CHECK IF THERE IS USER END --%>
        </div>
        <%-- MAIN CONTENT CONTAINER END --%>
    </div>
    <%-- VIEWPORT END --%>
</body>
</html>
