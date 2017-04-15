var colour, eclick = [], eraser = [], epos = 0, cx, cy, currentCanvas = "", canvas = [], jqids = "", ycoord = 190, userA = [];
var drawn = false, x = [], y = [], inc = 0, scrollpos = 0;
//colour stores in value of colour, epos stores in canvas position for both eraser and cavas, cy and cx are points used to erase points from canvas, currentCanvas stores in canvas currently in use, & ycoord stores in the y value whenever drawing on a canvas
//x and y arrays represents the data on the canvas
		
$(document).ready(function() {
        console.log("load paint functionality doc");
	$(window).scroll(function() {
		scrollpos = $(window).scrollTop();
	});
    
	$(jqids).mousedown(function(event) {
		$(document).bind("mousemove", function(event) {
							
			if (eraser[epos] == true && currentCanvas == canvas[epos]) {
				erase(event);
			}
							
			else {
				draw(event);	
			}
		});
    });
				
    $(jqids).mouseup(function() {
		//if (eraser[epos] == false) {
			createPath();
		//}
		$(document).unbind("mousemove");
    });
});
//draws on canvas			
function draw(e) {
	var canvas = document.getElementById(currentCanvas).getContext("2d");
	canvas.arc(e.clientX-487, e.clientY-ycoord+scrollpos, 0, 0, 2*Math.PI);
        x.push(e.clientX-487);
        y.push(e.clientY-ycoord);
	canvas.strokeStyle = colour;
	canvas.stroke();
}
//creates space when done drawing			
function createPath() {
	var canvas = document.getElementById(currentCanvas).getContext("2d");
	canvas.beginPath();
	canvas.closePath();
}
//erases drawing			
function erase(e) {
	var canvas = document.getElementById(currentCanvas).getContext("2d");
	var cx = e.clientX-487;
	var cy = e.clientY-ycoord+scrollpos;
	canvas.globalCompositeOperation = "destination-out";
	canvas.fillRect(cx, cy, 30, 30);
	canvas.globalCompositeOperation = "source-over";
        x.splice(0, 1);
        y.splice(0, 1);
}
//controls the eraser tool			
function eraseTransistor(chatid, cv) {
    
        for (var i = 0; i < eclick.length; i++) {
            if (userA[i] == chatid) {
                eclick[i]++;
                
                if (eclick[i] % 2 == 0) {
                    eraser[i] = false;
                    document.getElementById(chatid+"erase").style.backgroundColor = "";
                    document.getElementById(cv).style.cursor = "crosshair";
                }
                
                else {
                    eraser[i] = true;
                    document.getElementById(chatid+"erase").style.backgroundColor = "pink";
                    document.getElementById(cv).style.cursor = "url('../GSNIMG/eraser.png'),auto";   
                }
                break;
            }
        }
}
//sets colour value			
function changeColour(colourVal) {
	colour = colourVal;
}
//converts drawing into an image			
function convertCanvas(src, formid) {
    var img = document.getElementsByClassName(currentCanvas+"img");
    var imgsrc = document.getElementById(src+"img");
    
    if (img.length > 0) {
        document.getElementById(formid).removeChild(img[0]);
        imgsrc.value = "";
    }
    
    if (x.length != 0 && y.length != 0) {
		var canvas = document.getElementById(currentCanvas);
		var img = canvas.toDataURL("image/png");
		var image = document.createElement("IMG");
		document.getElementById(formid).appendChild(image);
		image.src = img;
        imgsrc.value = img;
        image.setAttribute("class", currentCanvas+"img");
    }
}
//clears canvas			
function clearCanvas(id, formid) {
	var canvas = document.getElementById(currentCanvas);
	var context = canvas.getContext("2d");
        var img = document.getElementsByClassName(id);
        var imgsrc = document.getElementById(id+"img");
	context.clearRect(0, 0, canvas.width, canvas.height);
        x.length = 0;
        y.length = 0;
        
        if (img.length > 0) {
            document.getElementById(formid).removeChild(img[0]);
            imgsrc.value = "";
        }
}
//sets the canvas id
function setCanvas(canvasID) {
    currentCanvas = canvasID;
    scrollpos = window.pageYOffset;
    ycoord = 190;
    console.log(currentCanvas);
    
    for (var i = 0; i < inc; i++) {
        if (currentCanvas === canvas[i]) {
            epos = i;
            ycoord += (250 * i);
            scrollpos -= (147 * i); //when a row of buttons are added to a form, scrollpos pixels increases by 23px, so from 124px to 147px
        }
    }
}