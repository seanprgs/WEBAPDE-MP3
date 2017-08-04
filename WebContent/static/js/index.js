var tags = [];
var toggle = false;
var wordCount = 0;

$(function () {
    var string = window.location.href;
    if(!string.endsWith('register'))
        $('.popup-form-wrapper').hide();
    
    
    $('.user').click(function () {
        $('.popup-form-wrapper').slideToggle();
    });
    
    $('#add-tag').click(function () {
        var titleStr = $('#tag-field').val();
        
        if(titleStr.trim() != "") {
            var tag = document.createElement('div');
            var title = document.createElement('label')
            var delButton = document.createElement('i');
            var hidden = document.createElement('input');
            var value = $('#tagNum').val();
            var value = parseInt(value);
            
            console.log(value+1);
            $(hidden).attr('type', 'hidden');
            $(hidden).attr('name', 'tag' + value);
            $(hidden).val(titleStr);
            
            $(tag).addClass('upload-tag');
            $(title).addClass('upload-tag-title');
            $(delButton).addClass('delete-tag');
            $(delButton).addClass('fa');
            $(delButton).addClass('fa-close');

            $(delButton).click(function() {
                $(tag).remove();
                $('#tagNum').val(value-1);
            });
            
            $('#tagNum').val(value+1);
            $(title).html(titleStr);
            $(tag).append(title);
            $(tag).append(delButton);
            $(tag).append(hidden);
            $('.upload-tag-container').append(tag)
        }
    });

    $("input:checkbox").on('click', function() {
        var $box = $(this);
        if ($box.is(":checked")) {
        var group = "input:checkbox[name='" + $box.attr("name") + "']";
            
        $(group).prop("checked", false);
            $box.prop("checked", true);
        } else {
            $box.prop("checked", false);
        }
    });
    
    $('.upload-container').hide();
    
    $('.add-photo').click(function() {
        $('.upload-container').fadeToggle();
        
        if(toggle) {
            $('.add-photo').rotate(0);
            toggle = false;
        } else {
            $('.add-photo').rotate(45);
            $('#file').attr('src', '#');
            toggle = true;
        }
    });
    
    $('.reg-area').val("");

    $("#file").change(function(){
        readURL(this);
    });
})

function readURL(input) {

    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#preview').attr('src', e.target.result);
        }

        reader.readAsDataURL(input.files[0]);
    }
}

function charLimit(limitField, limitCount, limitNum) {
    if (limitField.value.length > limitNum) {
      limitField.value = limitField.value.substring(0, limitNum);
  } else {
      limitCount = limitNum - limitField.value.length;
       }
    console.log(limitCount);
    
    if(limitField.value.length == limitNum) {
        $('.reg-area-update').addClass('warning');
    } else {
        if($('.reg-area-update').hasClass('warning'))
            $('.reg-area-update').removeClass('warning');
    }
    
    $('.reg-area-update').html((limitNum - limitCount) + "/" + limitNum);
}

function validateLogin () {
	var username = $('#login-username').val();
	var password = $('#login-password').val();
	if(username.trim() == "" && password.trim() == "") {
		$('.popup-form .warning').html("username and password is empty!");
		return false;
	} else if(password.trim() == "") {
		$('.popup-form .warning').html("password is empty!");
		return false;
	} else if(username.trim() == "") {
		$('.popup-form .warning').html("username is empty!");
		return false;
	}
}

function validateRegistration () {
	var username = $('.reg-field-username').val();
	var password = $('.reg-field-pass').val();
	if(username.trim() == "" && password.trim() == "") {
		$('.reg-form .warning').html("username and password is empty!");
		return false;
	} else if(password.trim() == "") {
		$('.reg-form .warning').html("password is empty!");
		return false;
	} else if(username.trim() == "") {
		$('.reg-form .warning').html("username is empty!");
		return false;
	}
}

function validateUpload() {
	var isValid = true;
	var message = "";
	var file = $('#file').val();
	var title = $('#title').val();
    
	if(file.trim() == "") {
		message += "no file selected <br>";
		isValid = false;
	}
    
    if (title.trim() == "") {
		message += "no title input <br>";
		isValid = false;
	}
    
    if (!$('#pub').is(":checked") || !$('#pri').is(":checked")  ) {
		message += "did not select if private or public! <br>";
		isValid = false;
	}
	
	$('#warning-upload').html(message);
    if(!isValid)
	   return false;
}