var color, eclick = 0, erase = false, cx, cy, upcount = 0, cv = "", yval = 40, id = "";
		
$(document).ready(function() {
	$(id).mousedown(function(event) {
		$(document).bind("mousemove", function(event) {
						
			if (erase == true) {
				eraser(event);
			}
						
			else {
				draw(event);	
			}
		});
	});
				
	$(id).mouseup(function() {
		if (erase == false) {
			createPath();
		}
		$(document).unbind("mousemove");
	});
});
			
function draw(e) {
	var canvas = document.getElementById(cv).getContext("2d");
	canvas.arc(e.clientX-8, e.clientY-yval, 0, 0, 2*Math.PI);
	canvas.strokeStyle = color;
	canvas.stroke();
}
			
function createPath() {
	var canvas = document.getElementById(cv).getContext("2d");
	canvas.beginPath();
	canvas.closePath();
}
			
function eraser(e) {
	var canvas = document.getElementById(cv).getContext("2d");
	var cx = e.clientX-8;
	var cy = e.clientY-yval;
	canvas.globalCompositeOperation = "destination-out";
	canvas.fillRect(cx, cy, 30, 30);
	canvas.globalCompositeOperation = "source-over";
}
			
function eraseTransistor() {
	eclick++;
				
	if (eclick % 2 == 0) {
		erase = false;
		document.getElementsByTagName("button")[0].style.backgroundColor = "";
		document.getElementById(cv).style.cursor = "crosshair";
	}
				
	else {
		erase = true;
		document.getElementsByTagName("button")[0].style.backgroundColor = "pink";
		document.getElementById(cv).style.cursor = "url('eraser.png'),auto";
	}	
}
			
function changeColor() {
	color = document.form.input.value;
}
			
function convertCanvas() {
	var canvas = document.getElementById(cv);
	var img = canvas.toDataURL("image/png");
	var image = document.createElement("IMG");
	document.body.appendChild(image);
	image.src = img;
}
			
function clearCanvas() {
	var canvas = document.getElementById(cv);
	var context = canvas.getContext("2d");
	context.clearRect(0, 0, canvas.width, canvas.height);
}

function setCanvas(value) {
	cv = value;
	
	if (cv == "canvas0" || cv == "test") {
		yval = 40;
	}
	
	if (cv == "canvas1") {
		yval += 300;
	}
}
