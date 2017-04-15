/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* global userA, eraser, canvas, url, eclick, textFile */

$(document).ready(function() {
	console.log("external invoker loaded");
   $("#Add").click(addNewUser);
   $("#feedsubmit").click(sendPost);
   setInterval(updateFeed, 3000);
});

function addNewUser() {
    $.ajax({
        type: 'POST',
        data: { newuser : document.addnewuser.newuser.value, contactCount : document.addnewuser.contactCount.value},
        url: '../PHP/addNewUser.php',
        success: function(data) {
            console.log(data);
            /*var count = document.addnewuser.contactCount.value, canvasString = "";
            userA.length = count+1; //from userA to jqids, stores in the necesarry length to handle data. userA stores in chatids, eraser stores in boolean for the eraseTransistor, eclick stores in int to count how many times the erase button is clicked, canvas to keep track of the canvases opened, jqids stores in id for jquery
            eraser.length = count+1;
            eclick.length = count+1;
            canvas.length = count+1;
            url.length = count+1;
            textFile.length = count+1;
            
            userA[count] = "";
            canvas[count] = "";
            url[count] = "";
            textFile[count] = textReader();
            eraser[count] = false;
            eclick[count] = 0;
            
            if (jqids !== "" ) {
                jqids += ",";
            }
            jqids += "#canvas"+count;
            canvasString = "<canvas id=\"canvas"+count+"\" width=480 height=163 onmouseover=\"setCanvas('canvas"+count+"');changeColour(document.f"+count+".colour.value);\"></canvas>";
            document.getElementById("contacts").innerHTML += data;
            document.body.innerHTML += canvasString;
            console.log(jqids);*/
            window.location = "../PHP/home.php";
        },
        error: function() {
            console.log("something's wrong here");
        }
    });
}

function attachImage(chatid, canvas) {
    //var fileString = document.getElementById(chatid+"file").value;
    var file_data = $('#'+chatid+"file").prop('files')[0];
    console.log(file_data);
    var form_data = new FormData();
    form_data.append('file', file_data);
    console.log(form_data);
    
    $.ajax({
       type: 'POST',
       cache: false,
       contentType: false,
       processData: false,
       data: form_data,
       url: '../PHP/attachImage.php',
       success: function(data) {
           var directory = data;
           
           if (directory !== "") {
		var image = new Image();
		image.src = directory;
		image.onload = function() {
                    var width = image.width, height = image.height;
					
                    if (width > 480 && chatid != "feed") {
			width = 480;
                    }
                    
                    else if (width > 400 && chatid == "feed") {
                        width = 400;
                    }
                    
                    if (height > 163 && chatid != "feed") {
                        height = 163;
                    }
                    
                    else if (height > 200 && chatid == "feed") {
                        height = 200;
                    }
                    x.push(width);
                    y.push(height);
                    document.getElementById(canvas).getContext("2d").drawImage(image,0,0,width,height);
                };
            }
        },
        error: function() {
            console.log("Something's wrong here");
        }
    });
}

function sendMessage(chatid, number) {
    var form = new FormData(document.getElementById(number));
    
    $.ajax({
        type: 'POST',
        cache: false,
        contentType: false,
        processData: false,
        data: form,
        url: '../PHP/sendMessage.php',
        success: function() {
            /*var textRead = null;
				
            if (window.XMLHttpRequest) {
                textRead = new XMLHttpRequest();
            }
				
            else if (window.ActiveXObject) {
                textRead = new ActiveXObject('MSXML2.XMLHTTP.3.0');
            }
            
            textRead.onreadystatechange = function() {
                if (textRead.readyState == 4) {
                    if ((textRead.status >= 200 && textRead.status < 300) || textRead.status == 304) {
                        var chatbox = document.getElementById(chatid);
                        var postList = textRead.responseText.split(".");
                        var posts = "";
                        
                        for (var i = 0; i < postList.length; i++) {
                            posts += atob(postList[i]);
                        }
                        chatbox.innerHTML = posts;
                        chatbox.scrollTop = chatbox.scrollHeight;
                    }
                }
            };
            
            textRead.open('GET','../TXT/'+chatid+'.txt',true);
            textRead.send();*/
        }
    });
}

function sendPost() {
    console.log("feed post sent");
    var form = new FormData(document.getElementById("feedform"));
    
    $.ajax({
        type: 'POST',
        cache: false,
        contentType: false,
        processData: false,
        data: form,
        url: '../PHP/sendPost.php',
        success: function() {
            console.log("feed post sent");
        },
        error: function() {
            console.log("This doesn't work");
        }
    });
}

function updateFeed() {
    $.ajax({
       type:'POST',
       url: "../PHP/getFeedPosts.php",
       success: function(data) {
           document.getElementById("feedout").innerHTML = "";
           document.getElementById("feedout").innerHTML = data;
       }
    });
}