<?php
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 session_start();
 include "GSNDB.php";
 
 $contacts = ""; //stores in list of contacts
 $contactNum = 0; //stores in number of contacts
 $number = 0; //stores in form ids
 $m = ""; //stores in temporary chat messages soon to be stored in chatbox tables
 $code = ""; //stores in html code to load contacts on page
 $reloadCode = ""; //stores html code to reload chat box once user enter their input
 $chatid = ""; //stores in the chatbox table name
 $canvas = ""; //stores in the html code for the canvas
 $post = ""; //stores in the html code to display posts and helps store them into the database
 $id = ""; //stores in the id of the input box
 $url = ""; //stores in the url path for images
 $image = ""; //stores in the html code to display and upload images to the chatbox table
 $jqids = ""; //stores in the number of contacts to pass on to a javascript variable
 $jqidsrv = ""; //takes the comma from the previous variable before storing it into javascript variable
 $gotData = false; //prevents data from being displayed on html page more than once
 $dir = "";
 $username = $_SESSION["username"]; //gets username from session
 $statement = $db->prepare("SELECT FirstName,LastName FROM gsnaccounts WHERE Username = ?"); //prepares statement to get first name and last name
 $array = array($username); //stores username into an array
 $statement->execute($array);
 
 if ($row = $statement->fetch()) {
     $profileName = "$row[0] $row[1]"; //stores in first name and last name once table finds its data
 }
 $statement = $db->prepare("SELECT * FROM gsncontacts where contactuser1 = ? or contactuser2 = ?"); //selects data from the user's personal table
 $array = array($username,$username);
 $statement->execute($array);
 $array = array($username);
 
 while ($row = $statement->fetch()) {
        $statement2 = $db->prepare("select firstname,lastname,disable from gsnaccounts where username = ?");
        $contactUserInfo = null;
        $newcontact = "";
        
        if ($username === $row[1]) {
            $newcontact = $row[2];
            $contactUserInfo = array($row[2]);
        } 
        else if ($username === $row[2]) {
            $newcontact = $row[1];
            $contactUserInfo = array($row[1]);
        }
        
        if ($contactUserInfo != null) {
            $statement2->execute($contactUserInfo);
        }
	 
        if ($row2 = $statement2->fetch()) {
            if ($row2[2] == 0) {
		 $contacts .= "<a id=$newcontact onclick='chatBox(\"$newcontact\", \"$username\", \"$row2[0] $row2[1]\", $contactNum);' onmouseover='link(\"$newcontact\");' onmouseout='link(\"$newcontact\");'>$row2[0] $row2[1]</a><br>";
		 $canvas .= "<canvas id=\"canvas$contactNum\" width=480 height=163 onmouseover=\"setCanvas('canvas$contactNum');changeColour(document.f$contactNum.colour.value);\" onmouseout=\"convertCanvas('canvas$contactNum',$contactNum);\">";
                 $jqids .= "#canvas$contactNum,";
            }
            
            else
                $contacts .= "$row2[0] $row2[1] (this account has been deactivated)<br>";
	 }
     /*$statement2 = $db->prepare("SELECT * FROM chatCount"); //selects all data from the chat id table
     $statement2->execute();*/
     
     /*if (!$gotData) {
        while ($row2 = $statement2->fetch()) {
            if (strstr($row2[1], $username)) {
               $statement3 = $db->prepare("SELECT MsgNone FROM $row2[1]"); //selects all messages without CSS styles from chatbox table
               $statement3->execute();

               while ($row3 = $statement3->fetch()) {
                  $code .= base64_decode($row3[0]); //decodes data before storing it in
               }
            }
            $gotData = true;
        }
     }*/
     /*$code .= "<canvas id=\"canvas$contactNum\" name=\"canvas$contactNum\" class=\"canvas$contactNum\" width=480 height=163 onmouseover=setCanvas(\"canvas$contactNum\");"
             . "changeColour(document.f$contactNum.colour.value); onmouseout=convertCanvas(\"img$contactNum\");></canvas>";*/
     $contactNum++;
 }
 
 for ($i = 0; $i < strlen($jqids) - 1; $i++) {
     $char = substr($jqids, $i); //stores in each char of the text
     $charn = ord($char); //changes into an ASCII value
     $jqidsrv .= chr($charn); //stores ASCII character
 }
 
 /*if (isset($_POST["newuser"])) {
     $newuser = $_POST["newuser"]; //gets the new contact's username from the add contacts box
     $newuserN = 0; //stores in the value of the new contact's username
     $usernameN = 0; //stores in the value of the user's username
	 
	 $statement = $db->prepare("SELECT * FROM GSNAccount WHERE Username = ?");
	 $array2 = array($newuser);
	 $statement->execute($array2);
	 
	 if ($row = $statement->fetch()) {
		$inSystem = true;
	 }
	 
	 else {
		$inSystem = false;
	 }
     
     if ($newuser != $username && $inSystem) {
     
        for ($i = 0; $i < strlen($newuser); $i++) {
            $char = substr($newuser, $i);
            $newuserN += ord($char);
        }

        for ($i = 0; $i < strlen($username); $i++) {
            $char = substr($username, $i);
            $usernameN += ord($char);
        }
        
        if ($usernameN < $newuserN) {
            $chatid = "$username$newuser";
        }
        
        else {
            $chatid = "$newuser$username";
        }
		fopen("../TXT/$chatid.txt", "x");
		fclose("../TXT/$chatid.txt");
		
        /*if ($chatid !== $username) {
            $statement = $db->prepare("INSERT INTO chatCount (ChatID) VALUES ('$chatid')"); //prepares to insert chat id into the chatCount table
            $statement->execute();
        }*/
        /*$statement = $db->prepare("SELECT FirstName,LastName FROM GSNAccount WHERE Username = ?");
        $statement->execute($array);

        if ($row = $statement->fetch()) {*/
            /*$statement = $db->prepare("INSERT INTO gsncontacts (contactuser1, contactuser2) VALUES ('$username','$newuser')"); //stores new contact info into user's table
            $statement->execute();*/
        //}
        /*$statement = $db->prepare("SELECT FirstName,LastName FROM GSNAccount WHERE Username = ?");
		$array2 = array($username);
        $statement->execute($array2);

        if ($row = $statement->fetch()) {
            $statement = $db->prepare("INSERT INTO $newuser VALUES ('$row[0]','$row[1]','$username','0')"); //stores user's information into new contact's table
            $statement->execute();
        }*/
     /*}
     header('Location: home.php');
 }*/
 
 if (isset($_POST["logout"])) {
     session_unset();
     session_destroy();
     header('Location: ../index.html');
 }
 
 if (isset($_POST["attach"])) {
	$statement = $db->prepare("SELECT contactuser2 FROM gsncontacts where contactuser1 = ?");
	$statement->execute($array);
	
	while ($row = $statement->fetch()) {
		$idN = "$row[0]$username";
		$idR = "$username$row[0]";
		
		if (isset($_url[$idN])) {
			$id = $idN;
		}
		
		else if (isset($_url[$idR])) {
			$id = $idR;
		}
		
		for ($i = 0; $i < $contactNum; $i++) {
			if (isset($_POST[$i]) && isset($_url[$id])) {
				$number = $i;
			}
		}
	
		 if (isset($_url[$id])) {
			$selected_file = $_url[$id];
			$ext = explode('.', $selected_file["name"]);
			$ext = strtolower(end($ext));
			
			if ($ext === "jpg" 
				|| $ext === "png" 
				|| $ext === "gif" 
				|| $ext === "tif"
				|| $ext === "tiff"
				|| $ext === "jpeg"
				|| $ext === "rif"
				|| $ext === "bmp"
				|| $ext === "pbm"
				|| $ext === "pgm"
				|| $ext === "ppm"
				|| $ext === "pnm"
				|| $ext === "webp"
				|| $ext === "hdr"
				|| $ext === "bpg") {
				$canvas = "canvas$number";
				$uniqid = uniqid();
				$new_file = uniqid().".$ext";
				$dir = "Uploads/$new_file";
				
				if (move_uploaded_file($selected_file["tmp_name"], $dir)) {
					$reloadCode = "<form id=\"$number\" class=\"c$number c\" name=\"f$number\" method=\"POST\" action=\"home.php\""
              . "onmouseover='reloadChat($number, \"$id\");setCanvas(\"canvas$number\");'>"
              . "<legend>$fullname<br></legend>"
              . "<div id=$id style='overflow-y: scroll';>$post</div>"
              . "</form>"
              . "<selection id=\"selection\">"
              . "<canvas id=\"canvas$number\" name=\"canvas$number\" width=\"480\" height=\"163\""
              . "onmouseover='reloadChat($number, \"$id\");setCanvas(\"canvas$number\");changeColour(document.f$number.colour.value);' style='display: inline';>"
              . "</canvas>"
              . "<input type=\"hidden\" id=\"hiddentemp\" name=$number value=$number><br id=\"br\">"
              . "<input type=\"text\" id=\"texttemp\" onmouseover='reloadChat($number, \"$id\");' name=$id>"
              . "<input type=\"button\" id=\"$id"."erase\" onmouseover='reloadChat($number, \"$id\");' onclick='eraseTransistor(\"$id\", \"canvas$number\");' value=\"Erase\">"
              . "<input type=\"color\" id=\"colourtemp\" onmouseover='reloadChat($number, \"$id\");' name=\"colour\">"
              . "<input type=\"button\" id=\"cleartemp\" value=\"Clear\" onclick='clearCanvas(\"canvas$number"
              . "img\", \"img$number\");' onmouseover='reloadChat($number, \"$id\");'>"
              . "<input type=\"submit\" id=\"submittemp\" onmouseover='reloadChat($number, \"$id\");'>"
              . "<input type=\"button\" id=\"button2temp\" onmouseover='reloadChat($number, \"$id\");' value=\"Exit\" onclick='closeChatBox($number, \"$id\");'>"
			  . "<br>"
			  . "<input type=\"file\" name=\"file$number\" id=\"file2temp\" class=\"fileText\">"
			  . "<input type=\"submit\" name=\"attach\" id=\"attach2temp\" class=\"attach\">"
              . "</selection>"
              . "<script>document.getElementById(\"$id\").scrollTop = document.getElementById(\"$id\").scrollHeight;</script>";
			  break;
				}
			}
		 }
	}
}
 
 /*if (isset($_POST["submit"]) or !isset($_POST["submit"])) {
     $statement = $db->prepare("SELECT contactuser2 FROM gsncontacts where contactuser1 = ?"); //gets contact username
     $statement->execute($array);
     
     while ($row = $statement->fetch()) {
         $idN = "$row[0]$username"; //stores contact and username together
         $idR = "$username$row[0]"; //stores contactn and username together in reverse
         $id = "";
         
         if (isset($_POST[$idN])) {
             $id = $idN;
         }
         
         else if (isset($_POST[$idR])) {
             $id = $idR;
         }
         
         for ($i = 0; $i < $contactNum; $i++) {
             if (isset($_POST[$i]) && isset($_POST[$id])) {
                 $number = $i;
                 
                 if (isset($_POST["img$i"])) {
                     $url = $_POST["img$i"];
                     $image = "<img src=$url style='display: inline';><br>";
                 }
             }
         }
         
         if (isset($_POST[$id])) {
             /*$statement = $db->prepare("SELECT FirstName,LastName FROM GSNAccount WHERE Username = ?");
             $array7 = array($row[0]);
             $statement->execute($array7);
             
             if ($row2 = $statement->fetch()) {
                 $fullname = "$row2[0] $row2[1]"; //stores in full name of contact
             }
             $m = $_POST[$id];
             $data = "<a id=\"post\" class=\"$id\"><strong>$profileName: </strong>$m<br>$image</a>"; //stores in chat message without any styling
             $encryptedNone = base64_encode($data); //encodes data in base64
             $data2 = "<a id=\"post\" class=\"$id\" style='display: inline';><strong>$profileName: </strong>$m<br>$image</a>"; //stores in chat message with styling
             $encryptedInline = base64_encode($data2); //encodes data in base64*/
             
             /*$statement = $db->prepare("INSERT INTO $id (MsgNone,MsgInline) VALUES ('$encryptedNone','$encryptedInline')"); //inserts message without and with styling into chatbox
             $statement->execute();*/
             
             /*$statement = $db->prepare("SELECT * FROM gsncontacts where contactuser1 = ?"); //selects all messages with styling
             $statement->execute($array);
             
             while ($row = $statement->fetch()) {
                 $post .= base64_decode($row[2]); //decodes the data from base64 to regular text before storing
             }*/
             /*$reloadCode = "<form id=\"$number\" class=\"c$number c\" name=\"f$number\" method=\"POST\" action=\"home.php\""
              . "onmouseover='reloadChat($number, \"$id\");setCanvas(\"canvas$number\");'>"
              . "<legend>$fullname<br></legend>"
              . "<div id=$id style='overflow-y: scroll';>$post</div>"
              . "</form>"
              . "<selection id=\"selection\">"
              . "<canvas id=\"canvas$number\" name=\"canvas$number\" width=\"480\" height=\"163\""
              . "onmouseover='reloadChat($number, \"$id\");setCanvas(\"canvas$number\");changeColour(document.f$number.colour.value);' style='display: inline';>"
              . "</canvas>"
              . "<input type=\"hidden\" id=\"hiddentemp\" name=$number value=$number><br id=\"br\">"
              . "<input type=\"text\" id=\"texttemp\" onmouseover='reloadChat($number, \"$id\");' name=$id>"
              . "<input type=\"button\" id=\"$id"."erase\" onmouseover='reloadChat($number, \"$id\");' onclick='eraseTransistor(\"$id\", \"canvas$number\");' value=\"Erase\">"
              . "<input type=\"color\" id=\"colourtemp\" onmouseover='reloadChat($number, \"$id\");' name=\"colour\">"
              . "<input type=\"button\" id=\"cleartemp\" value=\"Clear\" onclick='clearCanvas(\"canvas$number"
              . "img\", \"img$number\");' onmouseover='reloadChat($number, \"$id\");'>"
              . "<input type=\"submit\" id=\"submittemp\" onmouseover='reloadChat($number, \"$id\");'>"
              . "<input type=\"button\" id=\"button2temp\" onmouseover='reloadChat($number, \"$id\");' value=\"Exit\" onclick='closeChatBox($number, \"$id\");'>"
              . "</selection>"
              . "<script>document.getElementById(\"$id\").scrollTop = document.getElementById(\"$id\").scrollHeight;</script>";
             break;
         }
     }
  }*/
?>

<!DOCTYPE HTML>

<html>
    <head>
        <meta charset="utf-8">
        <title>GSN</title>
		<link rel="stylesheet" href="../CSS/commonstyle.css">
        <style>
            body {
                overflow-x: hidden;
            }
            
            h1 {
				text-align: left;
            }
            
            legend {
                font-weight: bolder;
            }
            
            h2 {
                color: #FFCC99;
            }
            
            img {
                display: none;
            }
            
            div {
                overflow-x: hidden;
                background-color: #FFFFCC;
                border: thin solid black;
                width: 30em;
                height: 10em;
            }
            
            canvas {
                display: none;
                border: thin solid black;
                background-image: url('../GSNIMG/canvas.jpg');
                background-size: cover;
                cursor: crosshair;
            }
            
            form {
                color: white;
            }
            
            .input {
                position: relative;
                left: 0.5em;
                top: 9em;
            }
            
            .c,#selection {
                position: relative;
                left: 30em;
            }
            
			.fileText {
				background-color: #87CEEB;
				border: none;
			}
			
			.attach {
				position: absolute;
				left: 16.5em;
			}
			
            #post {
                display: inline;
                color: black;
            }
            
            #feedpost {
                display: inline;
                color: white;
            }
            
            #fixed {
                position: fixed;
            }
        </style>
        <script src="../JS/jquery-3.1.1.js"></script>
        <script src="../JS/paintFunc.js"></script>
        <script src="../JS/paintFeed.js"></script>
        <script>
            var linkover = 0, reload = 0, directory = "<?= $dir ?>", url = [], pos = 0; //linkover changes style when mouse is over account names, reload ensures contents aren't reloaded more than once
            var textFile = textReader(), prevPosts = "";
            userA.length = <?= $contactNum ?>; //from userA to jqids, stores in the necesarry length to handle data. userA stores in chatids, eraser stores in boolean for the eraseTransistor, eclick stores in int to count how many times the erase button is clicked, canvas to keep track of the canvases opened, jqids stores in id for jquery
            eraser.length = <?= $contactNum ?>;
            eclick.length = <?= $contactNum ?>;
            canvas.length = <?= $contactNum ?>;
            url.length = <?= $contactNum ?>;
            jqids = "<?= $jqidsrv ?>";
			
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
            
            for (var i = 0; i < userA.length; i++) {
                userA[i] = "";
                canvas[i] = "";
		url[i] = "";
                
                /*if (i !== 0) {
                    ycoord += 15;
                    ycspace += 15;
                }*/
                eraser[i] = false;
                eclick[i] = 0;
            }
            
            textFile.onreadystatechange = function() {
                if (textFile.readyState == 4) {
                    if ((textFile.status >= 200 && textFile.status < 300) || textFile.status == 304) {
                        console.log("In file");
                        var chatbox = document.getElementById(userA[pos]);        
                        var postList = textFile.responseText.split(".");
                        var posts = "";
                        
                        for (var i = 0; i < postList.length; i++) {
                            posts += atob(postList[i]);
                        }
                        
                        if (posts !== "" && prevPosts !== posts) {
                            prevPosts = posts;
                            chatbox.innerHTML = posts;
                            chatbox.scrollTop = chatbox.scrollHeight;
                        }
                    }
                }
            };
            
            //opens new contact window
            function openNCW() {
                document.getElementsByTagName("fieldset")[2].style.display = "inline";
            }
            //closes new contact window
            function closeNCW() {
                document.getElementsByTagName("fieldset")[2].style.display = "none";
            }
            
            function openFW() {
                document.getElementsByTagName("fieldset")[1].style.display = "inline";
            }
            //closes new contact window
            function closeFW() {
                document.getElementsByTagName("fieldset")[1].style.display = "none";
            }
            //closes chat box
            function closeChatBox(index, id) {
                var nullpos = 0;
                
                for (var i = 0; i < inc; i++) {
                    if (userA[i] === id) {
                        userA[i] = "";
                        canvas[i] = "";
			url[i] = "";
                        eclick[i] = 0;
                        
                        if (inc > 0) {
                            for (var j = i; j < inc - 1; j++) {
                                userA[j] = userA[j+1];
                                canvas[j] = canvas[j+1];
                                eclick[j] = eclick[j+1];
				url[j] = url[j+1];
                                nullpos = j;
                            }
                        }
                        break;
                    }
                }
                
                for (var i = nullpos + 1; i < userA.length; i++) {
                    userA[i] = "";
                    canvas[i] = "";
                    url[i] = "";
                    eclick[i]= 0;
                }
                inc--;
                document.body.appendChild(document.getElementById("canvas"+index));
                document.getElementById("canvas"+index).style.display = "none";
                
                document.body.removeChild(document.getElementById(""+index));
				
				
            }
            //updates char box
            function updateChatBox() {
                console.log("can call");
                for (var i = 0; i < url.length; i++) {
                    if (userA[i] !== "") {
                        console.log("Meets condition");
                        pos = i;
                        textFile.open('GET',url[i],true);
                        textFile.send();
                        /*$.ajax({
                           type:'POST',
                           data: { chatid : userA[i] },
                           url: "getChatMessages.php",
                           success: function(data) {
                               console.log(userA[i]);
                               if (data !== "") {
                                document.getElementById(userA[i]).innerHTML = data;
                                }
                           }
                        });*/
                    }
                }
            }
            //opens chat box
            function chatBox(id, user, clientName, number) {
                var idN = 0, userN = 0, chatid = "", idMatch = false;
                var classcheck = document.getElementsByClassName("c"+number);
                
                for (var i = 0; i < id.length; i++) {
                    idN += id.charCodeAt(i);
                }
                
                for (var i = 0; i < user.length; i++) {
                    userN += user.charCodeAt(i);
                }
                
                if (userN < idN) {
                    chatid = user+""+id;
                }
                
                else {
                    chatid = id+""+user;
                }
                console.log(userA.length);
                
                if (classcheck.length == 0 && userA[inc] === "") {
                    userA[inc] = chatid;
                    canvas[inc] = "canvas"+number;
                    url[inc] = "../TXT/"+chatid+".txt";
                    inc++;
                    idMatch =  true;
                    console.log("Check 1");
                }
                
                else if (userA[inc - 1] === "") {
                    userA[inc - 1] = chatid;
                    canvas[inc - 1] = "canvas"+number;
                    console.log("Check 2");
                }
                
                else {
                    console.log("Check 3");
                }
                
                if (idMatch) {
                    var div = document.createElement("DIV");
                    var cv = document.getElementById("canvas"+number);
                    var br = document.createElement("BR");
                    var br2 = document.createElement("BR");
                    var legend = document.createElement("LEGEND");
                    var form = document.createElement("FORM");
                    var input = document.createElement("INPUT");
                    var color = document.createElement("INPUT");
                    var clear = document.createElement("INPUT");
                    var erase = document.createElement("INPUT");
                    var numberinput = document.createElement("INPUT");
                    var imgsrc = document.createElement("INPUT");
                    var button = document.createElement("INPUT");
                    var exit = document.createElement("INPUT");
					var attachImg = document.createElement("INPUT");
					//var attach = document.createElement("INPUT");
                    document.body.appendChild(form);
                    form.id = number;
                    form.name = "f"+number;
                    form.setAttribute("class", "c"+number+" c");
                    form.setAttribute("method", "POST");
                    form.setAttribute("action", "home.php");
                    form.setAttribute("enctype", "multipart/form-data");
                    //form.setAttribute("onmouseover", "setCanvas(\"canvas"+number+"\");");
                    div.id = chatid;
                    div.style.overflowY = "scroll";
                    /*cv.id = "canvas"+number;
                    cv.name = "canvas"+number;
                    cv.width = 480;
                    cv.height = 163;
                    cv.setAttribute("onmouseover","setCanvas(\"canvas"+number+"\");changeColour(document.f"+number+".colour.value);");*/
                    legend.innerHTML = clientName+"<br>";
                    input.type = "text";
                    input.id = chatid+"text";
                    input.name = chatid+"text";
                    input.setAttribute("onchange", "setHidden(\""+chatid+"\");");
                    color.type = "color";
                    color.name = "colour";
                    clear.type = "button";
                    clear.value = "Clear";
                    clear.setAttribute("onclick", "clearCanvas(\"canvas"+number+"\","+number+");");
                    erase.type = "button";
                    erase.value = "Erase";
                    erase.id = chatid+"erase";
                    erase.setAttribute("onclick", "eraseTransistor(\""+chatid+"\", \"canvas"+number+"\");");
                    numberinput.type = "hidden";
                    numberinput.id = chatid+"hidden";
                    imgsrc.type = "hidden";
                    imgsrc.id = "canvas"+number+"img";
                    imgsrc.name = "canvas"+number+"img";
                    button.type = "button";
                    button.name = "Submit";
                    button.value = "Submit";
                    button.setAttribute("onclick","sendMessage(\""+chatid+"\","+number+");");
                    exit.type = "button";
                    exit.value = "Exit";
                    exit.setAttribute("onclick", "closeChatBox("+number+", \""+chatid+"\");");
					attachImg.type = "file";
                                        attachImg.id = chatid+"file";
					attachImg.name = chatid+"file";
					attachImg.setAttribute("class", "fileText");
                                        attachImg.setAttribute("onchange","attachImage(\""+chatid+"\",\"canvas"+number+"\");");
					/*attach.type = "button";
					attach.name = "attach";
                                        attach.id = "Attach";
					attach.setAttribute("class", "attach");
					attach.value = "Attach";*/
                    form.appendChild(legend);
                    form.appendChild(div);
                    form.appendChild(cv);
                    form.appendChild(numberinput);
                    form.appendChild(imgsrc);
                    form.appendChild(br);
                    form.appendChild(input);
                    form.appendChild(erase);
                    form.appendChild(color);
                    form.appendChild(clear);
                    form.appendChild(button);
                    form.appendChild(exit);
					form.appendChild(br2);
					form.appendChild(attachImg);
					//form.appendChild(attach);
                    document.getElementById("canvas"+number).style.display = "inline";
                    div.scrollTop = div.scrollHeight;
                }
            }
            //changes style when mouse is over contact names
            function link(id) {
                linkover++;
                
                if (linkover % 2 == 0) {
                    document.getElementById(id).style.color = "white";
                    if (id != "<?= $profileName ?>") {
                        document.getElementById(id).style.textDecoration = "none";
                    }
                    document.getElementById(id).style.cursor = "initial";
                }
                
                else {
                    document.getElementById(id).style.color = "blue";
                    document.getElementById(id).style.textDecoration = "underline";
                    document.getElementById(id).style.cursor = "pointer";
                }
            }
            //reloads chat box
            /*function reloadChat(index, id) {
            
				userA[0] = id;
				canvas[0] = "canvas"+index;
                userA[index] = "";
                canvas[index] = "";
            
                if (reload === 0) {
                    var form = document.getElementById(""+index);
                    var inputs = document.getElementsByClassName("temp");
                    var aTag = document.body.getElementsByClassName(id);
                    var divATag = document.getElementById(id).getElementsByClassName(id);
                    var outsideLength = aTag.length - divATag.length;
                    form.appendChild(document.getElementById("canvas"+index));
                    form.appendChild(document.getElementById("hiddentemp"));
                    form.appendChild(document.getElementById("br"));
                    form.appendChild(document.getElementById("texttemp"));
                    form.appendChild(document.getElementById(id+"erase"));
                    form.appendChild(document.getElementById("colourtemp"));
                    form.appendChild(document.getElementById("cleartemp"));
                    form.appendChild(document.getElementById("submittemp"));
                    form.appendChild(document.getElementById("button2temp"));
                    document.body.removeChild(document.getElementsByClassName("canvas"+index)[0]);
                    
                    for (var i = 0; i < outsideLength; i++) {
                        document.body.removeChild(aTag[aTag.length - 1]);
                    }
                    reload++;
                }
            }*/
			
            function setHidden(chatid) {
                var a = document.getElementById(chatid+"text").value;
                
                if (a !== " " && a !== "") {
                    document.getElementById(chatid+"hidden").value = a;
                }
                
                else {
                    document.getElementById(chatid+"hidden").value = "";
                }
            }
			
			/*if (directory !== "") {
				var image = new Image(), width = 0, height = 0;
				image.src = directory;
				image.onload = function() {
					
					if (image.width > 480 && image.height > 163) {
						width = 480;
						height = 163;
					}
					
					if (width === 0 || height === 0) {
						document.getElementById("<?= $canvas ?>").getContext("2d").drawImage(image);
					}
					
					else {
						document.getElementById("<?= $canvas ?>").getContext("2d").drawImage(image,0,0,width,height);
					}
				}
			}*/
            setInterval(updateChatBox, 1000);
        </script>
        <script src="../JS/externalInvoker.js"></script>
    </head>
    <body onload="closeNCW();closeFW();">
        <selection id="fixed">
            <h1>GSN</h1>
            <a id="<?= $profileName ?>" href="profile.php" onmouseover="link('<?= $profileName ?>');" onmouseout="link('<?= $profileName ?>');"><strong><?= $profileName ?></strong></a>
            <fieldset id="feed">
                <legend>
                    <h2>Feed</h2>
                    <section id="feedout"></section>
                </legend>
            </fieldset>
            <input type='button' value='Make a post' onclick="openFW();"><br>
            <form method="POST" id="feedform" name="feedform">
                <fieldset>
                    <legend>Make a post</legend>
                    <canvas id="feedcanvas" width="400" height="200" 
                            onmouseover="changeFeedColour();" onmouseout="convertFeedCanvas();"
                            style="display: inline;"></canvas><br>
                    <input id="feedtext" type="text">
                    <input id="feederase" type="button" onclick="eraseFeedTransistor();" value="Erase">
                    <input id="feedcolour" name="feedcolour" type="color">
                    <input type="button" value="Clear" onclick="clearFeedCanvas();">
                    <input id="feedsubmit" type="button" value="Submit">
                    <input type="button" value="Exit" onclick="closeFW();"><br>
                    <input id="feedfile" type="file" class="fileText" onchange="attachImage('feedform','feedcanvas');">
                    <input id="feedhidden" type="hidden">
                </fieldset>
            </form>
            <a id="contacts">Contacts<br>
                <?= $contacts ?>
            </a>
            <input type="button" value="Add New Contact" onclick="openNCW();">
            <form method="POST" name="addnewuser">
                <fieldset>
                    <legend>New Friend Username</legend>
                    <input name="newuser" type="text" placeholder="Enter username of new contact">
                    <input type="button" id="Add" value="Add">
                    <input type="button" value="Cancel" onclick="closeNCW();">
                    <input type="hidden" name="contactCount" value='<?= $contactNum ?>'>
                </fieldset><br>
                <input name="logout" type="submit" value="Log Out">
            </form>
        </selection>
        <?= $canvas ?>
    </body>
</html>