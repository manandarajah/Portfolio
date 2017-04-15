var feedColour = "", fcx = [], fcy = [], feedeclick = 0, eraser = false, yval = 195, id = "";
		
$(document).ready(function() {
	$("#feedcanvas").mousedown(function(event) {
		$(document).bind("mousemove", function(event) {
						
			if (eraser == true) {
				feedErase(event);
			}
						
			else {
				feedDraw(event);	
			}
		});
	});
				
	$("#feedcanvas").mouseup(function() {
		//if (eraser == false) {
			createFeedPath();
		//}
		$(document).unbind("mousemove");
	});
});
			
function feedDraw(e) {
	var canvas = document.getElementById("feedcanvas").getContext("2d");
	canvas.arc(e.clientX-25, e.clientY-yval, 0, 0, 2*Math.PI);
	fcx.push(e.clientX-25);
        fcy.push(e.clientY-yval);
	canvas.strokeStyle = feedColour;
	canvas.stroke();
}
			
function createFeedPath() {
	var canvas = document.getElementById("feedcanvas").getContext("2d");
	canvas.beginPath();
	canvas.closePath();
}
			
function feedErase(e) {
	var canvas = document.getElementById("feedcanvas").getContext("2d");
	var cx = e.clientX-25;
	var cy = e.clientY-yval;
	canvas.globalCompositeOperation = "destination-out";
	canvas.fillRect(cx, cy, 30, 30);
	canvas.globalCompositeOperation = "source-over";
	fcx.splice(0, 1);
    fcy.splice(0, 1);
}
			
function eraseFeedTransistor() {
	feedeclick++;
				
	if (feedeclick % 2 == 0) {
		eraser = false;
		document.getElementById("feederase").style.backgroundColor = "";
		document.getElementById("feedcanvas").style.cursor = "crosshair";
	}
				
	else {
		eraser = true;
		document.getElementById("feederase").style.backgroundColor = "pink";
		document.getElementById("feedcanvas").style.cursor = "url('../GSNIMG/eraser.png'),auto";
	}	
}
			
function changeFeedColour() {
	feedColour = document.feedform.feedcolour.value;
}
			
function convertFeedCanvas() {
	var feedimg = document.getElementById("feedhidden");
	
	if (document.getElementsByClassName("feedimg").length > 0) {
		document.body.removeChild(document.getElementsByClassName("feedimg")[0]);
		feedimg.value = "";
	}
	
	if (fcx.length != 0 && fcy.length != 0) {
		var canvas = document.getElementById("feedcanvas");
		var img = canvas.toDataURL("image/png");
		var image = document.createElement("IMG");
		document.body.appendChild(image);
		image.setAttribute("class", "feedimg");
		image.src = img;
		feedimg.value = img;
	}
}
			
function clearFeedCanvas() {
	var canvas = document.getElementById("feedcanvas");
	var context = canvas.getContext("2d");
	fcx.length = 0;
	fcy.length = 0;
	context.clearRect(0, 0, canvas.width, canvas.height);
}
