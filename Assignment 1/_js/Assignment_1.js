//Assignment_1.js is the computer file that controls the website, while accepting requests from the buttons from index.html, Mugiesshan Anandarajah
document.getElementById("enter").style.display = "inline";
//"passages" will store an array of passages, "encrypted" will turn each letter of the passage into a blank
var passages = 0, encrypted = "";
/* "blanks" is an array that records the number of characters in the passage and turns them into blanks.
 * "letters" is an array that records each letter of the passage.
 * "questions" is an array that records the number of questions depending on the repetition of a letter in the passage.
 * "answers" is an array that records the number of answers for each question
 * "options" is an array that records the number of options. Each questions will have 4 options to answer from.
 */
var blanks = 0, letters = 0, questions = 0, answers = 0, options = 0;
/* "count" is an int variable that increments by 1 to move to the next question of the game.
 * "oCount" is an int variable that increments by 4 to display each set of options.
 * "wrong" is an int variable that keeps track of the number of questions the user got wrong.
 * "decrypt" is an int variable that helps unlock a random letter if the user gets a question right.
 * "nextOp" is a boolean variable that operates the alternation between the result and question.
 * "run" is a boolean variable that operates the duration of the whole game, deciding weather it ends or not.
 * "guess" is a string variable that accepts the user's input when they want to solve the passage.
 * "selected" is an int variable that is only used once to select a random passage.
 */
var count = 0, oCount = 0, wrong = 0, decrypt = 0, nextOp = true, run = true, guess, selected = 0;
/* "qFile" is a browser object that sends the data of "Questions.txt" to set the questions.
 * "aFile" is a browser object that sends the data of "Answers.txt" to set the answers.
 * "oFile" is a browser object that sends the data of "Options.txt" to set the options.
 * "pFile" is a browser object that sends the data of "Passage.txt" to set the passages.
 * "next" is a browser object that sends no data but sets the next question.
 */
var qFile = textReader(), aFile = textReader(), oFile = textReader(), pFile = textReader(), next = textReader();

//textReader() is a function that sets and returns the object type for the web browser
function textReader() {
	var textRead = null;
	
	if (window.XMLHttpRequest) {
		textRead = new XMLHttpRequest();
	}
	
	else if (window.ActiveXObject) {
		textRead = new ActiveXObject('MSXML2.XMLHTTP.3.0');
	}
	return textRead;
}
//the qFile calls in its function to load the questions into the "questions" variable through setQuestions(<varname>);
qFile.onreadystatechange = function () {
	if (qFile.readyState == 4) {
		if ((qFile.status >= 200 && qFile.status < 300) || (qFile.status == 304)) {
			setQuestions(qFile.responseText);
		}
		
		else {
			alert("ERROR!");
		}
	}
};
//the aFile calls in its function to load the questions into the "answers" variable through setAnswers(<varname>);
aFile.onreadystatechange = function () {
	if (aFile.readyState == 4) {
		if ((aFile.status >= 200 && aFile.status < 300) || (aFile.status == 304)) {
			setAnswers(aFile.responseText);
		}
		
		else {
			alert("ERROR!");
		}
	}
};
//the oFile calls in its function to load the questions into the "options" variable through setOptions(<varname>);
oFile.onreadystatechange = function () {
	if (oFile.readyState == 4) {
		if ((oFile.status >= 200 && oFile.status < 300) || (oFile.status == 304)) {
			setOptions(oFile.responseText);
		}
		
		else {
			alert("ERROR!");
		}
	}
};
//the pFile calls in its function to load the questions into the "passages" variable through setPassages(<varname>);
pFile.onreadystatechange = function () {
	if (pFile.readyState == 4) {
		if ((pFile.status >= 200 && pFile.status < 300) || (pFile.status == 304)) {
			setPassages(pFile.responseText);
		}
		
		else {
			alert("ERROR!");
		}
	}
};

//textSplitter accepts a string data and splits it into a number of arrays using the "." as a unique identifier.
function textSplitter(text) {
	return text.split(".");
}

//setQuestions accepts a string data, specifically data from "Questions.txt" to the the questions. This sets & displays the first question.
function setQuestions(data) {
	if (questions == 0) {
		questions = textSplitter(data);
	}
	displayQuestion(count);
	document.getElementById("text").innerHTML = questions[0];
}

//setQuestions accepts a string data, specifically data from "Answers.txt" to the the questions. This sets the first answer.
function setAnswers(data) {
	if (answers == 0) {
		answers = textSplitter(data);
	}
	document.getElementById("answer").innerHTML = answers[0];
}

//setQuestions accepts a string data, specifically data from "Options.txt" to the the questions. This sets the first 4 options.
function setOptions(data) {
	if (options == 0) {
		options = textSplitter(data);
	}
	
	for (i = 0; i < 4; i++) {
		document.getElementById("option"+(i+1)).innerHTML = options[i];
	}
}

//setQuestions accepts a string data, specifically data from "Passage.txt" to the the questions. This sets the random passage, "blanks", and "letters".
function setPassages(data) {
	if (passages == 0) {
		passages = textSplitter(data);
	}
	selected = Math.random() * (passages.length - 1);
	selected = parseInt(selected);
	document.getElementById("passage").innerHTML = passages[selected];
	blanks = [passages[selected].length], letters = [passages[selected].length];
	var space = 0;
	
	for (var i = 0; i < passages[selected].length; i++) {
		if (passages[selected].charAt(i) != ' ') {
			blanks[i] = "_";
			space++;
		}
		
		else {
			blanks[i] = passages[selected].charAt(i);
		}
		encrypted += blanks[i];
		letters[i] = passages[selected].charAt(i);
	}
	
	for (var i = 0; i < letters.length; i++) {
		if (letters[i] == " ") {
			letters[i] = "-";
			blanks[i] = "-";
		}
	}
	document.getElementById("inGame").innerHTML += encrypted;
	document.getElementById("inGame").style.left += letters.length - questions.length;
}

//rules() is a function that gets called by pressing the "ENTER" button. It displays the instructions and the start button.
function rules() {
	document.getElementById("instructions").style.display = "inline";
	document.getElementById("start").style.display = "inline";
	document.getElementById("titleScreen").style.display = "none";
}

//start() is a function that passes the data of all the ".txt" files into their respective objects, and starts the whole game. It get's called by pressing the "Start" button.
function start() {
	qFile.open("GET", "_txt/Questions.txt", true);
	qFile.send();
	aFile.open("GET", "_txt/Answers.txt", true);
	aFile.send();
	oFile.open("GET", "_txt/Options.txt", true);
	oFile.send();
	pFile.open("GET", "_txt/Passage.txt", true);
	pFile.send();
	
	document.getElementById("board").style.display = "inline";
	document.getElementById("next").style.display = "inline";
	document.getElementById("solve").style.display = "inline";
	document.getElementById("quit").style.display = "inline";
	document.getElementById("box").style.display = "inline";
	document.getElementById("inGame").style.display = "inline";
	document.getElementById("instructions").style.display = "none";
	gameplay();
}

//gameplay() is a function that displays questions, answers, and options. It also keeps track if every question has been answered.
function gameplay() {
	
	if (count > questions.length - 1) {
		document.getElementById("text").innerHTML = "Those are all the questions, you can only solve the passage.";
		document.getElementById("next").style.backgroundColor = "rgba(0, 0, 0, 0.5)";
		nextOp = false;
	}
	
	else if (nextOp) {
		displayQuestion(count);
		document.getElementById("text").innerHTML = questions[count];
		document.getElementById("answer").innerHTML = answers[count];
		document.getElementById("options").style.display = "inline";
		
		for (var i = oCount; i < oCount + 4; i++) {
			document.getElementById("option"+(i-oCount+1)).innerHTML = options[i];
		}
	}
}

//displayQuestion(<variable>) sets the question header to let the user know how many questions there are in the game.
function displayQuestion(count) {
	document.getElementById("fheader").innerHTML = "Question " + (count + 1) + "/" + questions.length;
}

//check() is a function that runs the verification system to verify if the user's answer is correct or not. It gets called when the "Next" button is pressed.
function check() {
	
	if (run) {
		document.getElementById("options").style.display = "none";
		var checked = "", decrypted = "";
		
		if (count > questions.length - 1) {}
		
		else if (!nextOp) {
			nextOp = true;
		}
		
		else { 
			nextOp = false;
			
			for (var i = oCount; i < oCount + 4; i++) {
				if (document.game.radio[i-oCount].checked) {
					checked = options[i];
				}
			}
			
			if (checked == document.getElementById("answer").innerHTML) {
				document.getElementById("text").innerHTML = "You got it right!";
				decrypt = Math.random() * (letters.length - 1);
				decrypt = parseInt(decrypt);
			
				while (letters[decrypt] == '-' || blanks[decrypt] == letters[decrypt]) {
					decrypt = Math.random() * (blanks.length - 1);
					decrypt = parseInt(decrypt);
				}
				
				for (var i = 0; i < letters.length; i++) {
					if (letters[decrypt].toUpperCase() == letters[i].toUpperCase()) {
						blanks[i] = letters[i];
					}
					
					if (blanks[i] != '-') {
						decrypted += blanks[i];
					}
					
					else {
						decrypted += " ";
					}
				}
				document.getElementById("inGame").innerHTML = "";
				document.getElementById("inGame").innerHTML = decrypted;
			}
			
			else {
				document.getElementById("text").innerHTML = "You got it wrong. The answer was " + '"' + document.getElementById("answer").innerHTML + '".';
				
				if (checked != "") {
					wrong++;
					drawBody(wrong);
				}
			}
			
			if (checked == "") {
				document.getElementById("text").innerHTML = "You didn't even answer the question.";
			}
			
			else {
				count++;
				oCount += 4;
			}
		}
		
		if (document.getElementById("inGame").innerHTML == document.getElementById("passage").innerHTML) {
			win();
		}
		
		else {
			gameplay();
		}
	}
}

//drawBody(<variable>) is a function that accepts an int variable, particularly "wrong". This draws out the body of the hangman.
function drawBody(wrong) {
	switch (wrong) {
		case 1:
			document.getElementById("head").style.display = "inline";
			break;
		case 2:
			document.getElementById("body").style.display = "inline";
			break;
		case 3:
			document.getElementById("LA").style.display = "inline";
			break;
		case 4:
			document.getElementById("RA").style.display = "inline";
			break;
		case 5:
			document.getElementById("LL").style.display = "inline";
			break;
		case 6:
			document.getElementById("RL").style.display = "inline";
			lose();
			break;
	}
}

//solve() is a function that get's called by pressing the "Solve" button. It allows the user to enter their guess.
function solve() {
	
	if (run) {
		guess = prompt("Enter in your guess (It's not case sensitive)");
		
		if (guess.toUpperCase() == document.getElementById("passage").innerHTML.toUpperCase()) {
			win();
		}
		
		else {
			document.getElementById("text").innerHTML = "Wrong guess.";
			document.getElementById("options").style.display = "none";
			nextOp = false;
			wrong++;
			drawBody(wrong);
		}
	}
}

//win() is a function that displays a win message.
function win() {
	document.getElementById("options").style.display = "none";
	document.getElementById("text").innerHTML = "Congratulations, you've won!";
	document.getElementById("inGame").innerHTML = "";	
	document.getElementById("inGame").innerHTML = document.getElementById("passage").innerHTML;
	disable();
}

//lose() is a function that displays a lose message.
function lose() {
	document.getElementById("options").style.display = "none";
	document.getElementById("text").innerHTML = "Sorry, you've lost.";
	disable();
}

//disable() is a function that disables the "Next" and "Solve" button once the game is finished.
function disable() {
	document.getElementById("next").style.background = "rgba(0, 0, 0, 0.5)";
	document.getElementById("solve").style.background = "rgba(0, 0, 0, 0.5)";
	run = false;
}

//gameOver() is a function that gets called by pressing the "Quit" button. It displays a thank you message.
function gameOver() {
	document.getElementById("gameover").style.display = "inline";
	document.getElementById("clear").style.display = "none";
}
