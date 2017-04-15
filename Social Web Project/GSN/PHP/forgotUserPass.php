<?php
	date_default_timezone_set('Etc/UTC');
	include 'GSNDB.php';
	require '../Email/PHPMailerAutoload.php';
	
	$systemEmail = "noreply.gsocialnet@gmail.com";
	$systemPass = "gsnsocial2015";
	
	$mail = new PHPMailer();
	$mail->isSMTP();
   /*
	* 0 = off
	* 1 = client
	* 2 = client and server
	*/
	$mail->SMTPDebug = 2;
	$mail->Host = "smtp.gmail.com";
	$mail->SMTPAuth = true;
	//$mail->Debugoutput = "html";
	$mail->Username = $systemEmail;
	$mail->Password = $systemPass;
	$mail->SMTPSecure = "tls";
	$mail->Port = 587;

    if (isset($_POST["username"])) {
        $username = $_POST["username"]; //gets username from session
		$statement = $db->prepare("SELECT Password FROM GSNAccounts WHERE Username = ?");
		$array = array($username);
		$statement->execute($array);
		$decrypted = "";
		
		if ($row = $statement->fetch()) {
			for ($i = 0; $i < strlen($row[0]); $i++) {
				$char = substr($row[0], $i);
				$charn = ord($char) - ($i+1);
				$decrypted .= chr($charn);
			}
		}
		$statement = $db->prepare("SELECT Email FROM GSNAccounts WHERE Username = ?");
		$statement->execute($array);
		
		if ($row = $statement->fetch()) {
			$mail->From = $systemEmail;
			$mail->FromName = "GSocialNet";
			$mail->addAddress($row[0]);
			$mail->Subject = "Account recovery";
			$message = "Your password is $decrypted";
			$mail->Body = $message;
			
			if ($mail->send()) {
				header("Location: forgotUserPass.php");
			}
			
			else {
				echo "ERROR: ". $mail->ErrorInfo;
			}
		}
    }
    
    if (isset($_POST["email"])) {
        $email = $_POST["email"]; //gets email from session
		$statement = $db->prepare("SELECT Username FROM GSNAccounts WHERE Email = ?");
		$array = array($email);
		$statement->execute($array);
		
		if ($row = $statement->fetch()) {
			$mail->From = $systemEmail;
			$mail->FromName = "GSocialNet";
			$mail->addAddress($email);
			$mail->Subject = "Account recovery";
			$message = "Your username is $row[0]";
			$mail->Body = $message;
			
			if ($mail->send()) {
				header("Location: forgotUserPass.php"); 
			}
			
			else {
				echo "ERROR: ". $mail->ErrorInfo;
			}
		}
    }
?>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
		<link rel="stylesheet" href="../CSS/commonstyle.css">
        <style>
            body {
                color: white;
            }
        </style>
    </head>
    <body>
        <h1>Recover your account information</h1>
        <form method="POST" action="forgotUserPass.php">
            <fieldset>
                <legend><h2>Forgot your password?</h2></legend>
                <a>Username: <input type="text" name="username" placeholder="Enter username"></a>
            </fieldset>
            <fieldset>
                <legend><h2>Forgot your username?</h2></legend>
                <a>E-mail: <input type="text" name="email" placeholder="Enter E-mail address"></a>
            </fieldset>
            <input type="submit" value="Send">
        </form>
		<form method="POST" action="forgotUserPass.php">
		</form>
    </body>
</html>
