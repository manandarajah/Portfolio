<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 session_start();
 date_default_timezone_set('Etc/UTC');
 include "GSNDB.php"; //includes database
 //require "Email/class.phpmailer.php";
 
 require '../Email/PHPMailerAutoload.php';

 /*$systemEmail = "noreply.gsocialnet@gmail.com";
 $systemPass = "gsnsocial2015";
 
 $mail = new PHPMailer();
 $mail->isSMTP();
 /*
  * 0 = off
  * 1 = client
  * 2 = client and server
  */
 /*$mail->SMTPDebug = 2;
 $mail->Host = "smtp.gmail.com";
 $mail->SMTPAuth = true;
 //$mail->Debugoutput = "html";
 $mail->Username = $systemEmail;
 $mail->Password = $systemPass;
 $mail->SMTPSecure = "tls";
 $mail->Port = 587;
 
 /*$_SESSION["username"] = $_POST["username"]; //stores username in session
 $_SESSION["fname"] = $_POST["fname"];*/ //stores firstname in session
 $username = $_POST["username"]; //username
 $firstname = $_POST["fname"]; //firstname
 $lastname = $_POST["lname"]; //lastname
 $email = $_POST["email"]; //email
 $gender = $_POST["gender"]; //gender
        
/*$mail->From = $systemEmail;
$mail->FromName = "GSocialNet";
$mail->addAddress($email, "$firstname $lastname");
$mail->Subject = "New Account Created";
$message = "Hello $firstname, you've just created a new account.";
$mail->Body = $message;*/
 
$subject = "New Account Created";
$message = "Hello $firstname, you've just created a new account.";
	 
$statement = $db->prepare("SELECT * FROM gsnaccounts"); //prepares statement to get data from table
$statement->execute(); //executes
$repeat = false; //determines weather username and email address are already taken
	 
while ($row = $statement->fetch()) {
	if ($username == $row[0] || $email == $row[4]) {
		$repeat = true;
		break;
	}
}

if ($repeat == false && $_POST["pass"] == $_POST["cpass"] && mail($email,$subject,$message)) {
	$pass = $_POST["pass"]; //stores int password
	$encrypted = ""; //stores in password encrypted
		 
	for ($i = 0; $i < strlen($pass); $i++) {
		$char = substr($pass, $i); //stores in char of password
		$charn = ord($char) + ($i+1); //changes ASCII value of that char before storing
		$encrypted .= chr($charn); //stores in the new ASCII char
	}
	$statement = $db->prepare("INSERT INTO gsnaccounts (Username,Password,FirstName,LastName,Email,Gender,Disable) VALUES ('$username','$encrypted','$firstname','$lastname','$email','$gender',0)");
	$dataresult = $statement->execute(); //returns a boolean
	//inserts new account info into database
		 
	/*$statement = $db->prepare("CREATE TABLE $username (ContactFirstName VARCHAR(255), ContactLastName VARCHAR(255), ContactUsername VARCHAR(255), Disable BOOLEAN)");
	$tableresult = $statement->execute();*/ //returns a boolean
	//creates a personal table to store contact names
		 
	if ($dataresult /*&& $tableresult*/) {
            $_SESSION["username"] = $username;
            header('Location: home.php');
	}
	 
	else {
            echo "Error: ". $db->Error;
	}
}
 
 else {
     header('Location: ../HTML/newAccount.html'); //returns to newAccount.html
 }

?>