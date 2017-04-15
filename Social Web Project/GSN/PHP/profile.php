<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 session_start();
 include "GSNDB.php";
 
 $profileInfo = ""; //stores in profile information
 $profileName = ""; //stores in the user's full name
 $username = $_SESSION["username"]; //gets username from session and stores it in
 $statement = $db->prepare("SELECT * FROM gsnaccounts WHERE Username = ?"); //gets data from table using username, sql injection prevention
 $array = array($username); //stores username into array
 $statement->execute($array);
 
 if ($row = $statement->fetch()) {
     $profileInfo .= "<a><strong>Name:</strong> $row[2] $row[3]</a><br>";
     $profileInfo .= "<a><strong>Username:</strong> $row[0]</a><br>";
     $profileInfo .= "<a><strong>Email:</strong> $row[4]</a><br>";
     $profileInfo .= "<a><strong>Gender:</strong> $row[5]</a><br>";
     $profileName = "$row[2] $row[3]";
 }
?>
<!DOCTYPE HTML>
<!--Profile page-->
<html>
    <head>
        <title><?= $profileName ?></title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="../CSS/commonstyle.css">
        <style>
            a {
                font-size: 2em;
            }
			
			#delete {
				position: absolute;
				top: 9.72em;
				left: 4.5em;
			}
        </style>
    </head>
    <body>
        <?= $profileInfo ?>
		<form method="POST" action="disable.php">
			<input type="submit" value="Disable">
		</form>
		<form id="delete" method="POST" action="delete.php">
			<input type="submit" value="Delete">
		</form>
    </body>
</html>