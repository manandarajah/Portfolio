/*Mugiesshan Anandarajah
*The variable position generates the star positions once created
*The variable star stores in the number of stars that appear on the card
*The variable sp keeps track of it's verticle position
*The variables green & blue keeps track of the change of the sky color
*The variable sunp keeps track of the sun's position
*The variable name stores in the recepient's name
*The variable doe determines if it's day or night
*The variable color stores in the value of the chosen text color
*The array time is of type String which store in values 'day' & 'night'
*/
var position = 0, star = 0, sp, green, blue, sunp;
var name = "", doe = "", color = "";
var time = [2];
time[0] = "night", time[1] = "day";
//The window object calls itself when the program runs, which runs the statements inside it
window.addEventListener("load", function(event) {

	document.getElementById("button").addEventListener("mouseover", highlight);
	document.getElementById("button").addEventListener("mouseout", erase);
	document.getElementById("button").addEventListener("click", createCard);
});
//This function created the card
function createCard() {
	document.getElementById("form").style.display = "none";
	name = document.cardInfo.fname.value;
	star = document.cardInfo.star.value;
	color = document.cardInfo.color.value;
	
	for (var i = 0; i < document.cardInfo.check.length; i++) {
		if (document.cardInfo.check[i].checked) {
			doe = time[i];
		}
	}
	
	if (doe == time[1]) {
		green = 0;
		blue = 35;
		sunp = 40;
	}
	
	else {
		green = 225;
		blue = 255;
		sunp = 10;
		document.getElementById("sun").style.top = "10em";
	}
	document.getElementById("card").style.backgroundColor = "rgba(0, " + green + ","+blue+", 1)";
	document.getElementById("sun").style.display = "inline";
	
	stars(star);
	setInterval(animation, 16);
}
//This function generate the number of stars
function stars(numStar) {
	
	for (var i = 0; i < numStar; i++) {
		var img = document.createElement("img");
		document.body.appendChild(img);
		img.id = "star"+(i+1);
		img.src = "_images/star.png";
		position = Math.random() * 480;
		position = parseInt(position);
		img.style.position = "absolute";
		img.style.left = position+"px";
		
		if (doe == time[0]) {
			position = 610 + Math.random() * 615;
			position = parseInt(position);
			img.style.top = position+"px";
		}
		
		else {
			img.style.top = (position/2)+"px";
		}
	}
	sp = document.getElementById("star1").style.top;
	sp = parseInt(sp);
}
//This function creates the animation within the card
function animation() {
	
	document.getElementById("sun").style.top = sunp+"em";
	if (doe == time[1] && sunp > 10) {
		sp += 10;
		sunp -= 0.5;
	}
	
	else if (doe == time[0] && sunp < 45) {
		sp -= 10;
		sunp += 0.5;
	}
	
	else if (sunp >= 45) {
		document.getElementById("sun").style.display = "none";
	}
	
	if (sunp >= 45 || sunp <= 10) {
		document.getElementById("name").style.display = "inline";
		document.getElementById("name").innerHTML = "Happy Birthday " + name + "!";
		document.getElementById("name").style.color = color;
	}
	
	for (var i = 0; i < star; i++) {
		if (sp < window.innerHeight + (i+3) || sp > window.innerHeight) {
			document.getElementById("star"+(i+1)).style.top = (sp+i)+"px";
		}
		
		else {
			document.getElementById("star"+(i+1)).style.display = "none";
		}
		
		if (doe == time[1]) {
			green++;
			blue++;
		}
	
		else if (green > 0 && blue > 35) {
			green--;
			blue--;
		}
		document.getElementById("card").style.backgroundColor = "rgba(0,"+green+","+blue+",1)";
	}
}
//This function changes the color of the button "Generate Card" when mouse is over it
function highlight() {
	document.getElementById("button").style.backgroundColor = "blue";
	document.getElementById("button").style.borderColor = "green";
}
//This function changes the color of the button "Generate Card" back to its original when mouse is moved out
function erase() {
	document.getElementById("button").style.backgroundColor = "pink";
	document.getElementById("button").style.borderColor = "yellow";
}
