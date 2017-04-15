<?php
    session_start();
    include "GSNDB.php"; //includes database class

    $msg = "SELECT Username,Password FROM gsnaccounts"; //selects name from social database
    $statement = $db->prepare($msg); //prepares the statement
    $_SESSION["username"] = $_POST["username"]; //stores username in session
    $username = $_POST["username"];
    $statement->execute(); //execute the statement
    $op = false; //determines weather the login has an error or not
    
    while ($results = $statement->fetch()) {
        $pass = $results[1]; //stores in password from database
        $decrypted = ""; //stores in the password decrypted
        
        for ($i = 0; $i < strlen($pass); $i++) {
            $char = substr($pass, $i); //stores in the char of the password
            $charn = ord($char) - ($i+1); //stores in and changes the ASCII value
            $decrypted .= chr($charn); //stores in new ASCII character
        }
        
        if ($_POST["username"] == $results[0] && $_POST["password"] == $decrypted) {
            $op = true;
            break;
        }
    }
    
    if ($op == true) {
		$statement = $db->prepare("UPDATE gsnaccounts SET Disable = 0 WHERE Username = ?");
		$array = array($username);
		$statement->execute($array);
		/*$statement = $db->prepare("SELECT ContactUsername FROM gsncontacts where username = ?");
		$statement->execute($array);
				
		while ($row = $statement->fetch()) {
			$statement2 = $db->prepare("UPDATE $row[0] SET Disable = 0 WHERE ContactUsername = ?");
			$statement2->execute($array);
		}*/
        header('Location: home.php');
    }
?>

<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<!--Login if error-->
<html>
    <head>
        <title>Welcome to Graphical Social Networking (GSN)</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="../CSS/commonstyle.css">
        <style>
            h2 {
                position: relative;
                left: 15em;
                color: white;
            }
            
            h3 {
                position: relative;
                left: 18.5em;
                color: white;
            }
            
            form {
                position: relative;
                left: 22em;
            }
            
            #error {
                position: relative;
                left: 21.5em;
            }
        </style>
        <script>
            function error() {
                document.getElementById("error").style.color = "red";
            }
			
			function highlightForgotPass() {
				document.getElementsByTagName("a")[3].style.color = "blue";
			}
			
			function eraseForgotPass() {
				document.getElementsByTagName("a")[3].style.color = "white";
			}
        </script>
    </head>
    <body onload="error();">
        <h1>Welcome to Graphical Social Networking (GSN)</h1>
        <a id="error">Invalid username or password!</a>
        <h3>Please log in to continue</h3>
        <form method="POST" action="login.php">
            <a>Username: <input name="username" type="text" placeholder="Enter username" required></a><br>
            <a>Password: <input name="password" type="password" placeholder="Password" required></a><br>
            <input type="submit" value="Log in">
            &nbsp;&nbsp;&nbsp;<a href="forgotUserPass.php" onmouseover="highlightForgotPass();" onmouseout="eraseForgotPass();">Forgot username/password</a>
        </form>
        <h2>New to this site?</h2>
        <form method="POST" action="../HTML/newAccount.html">
            <input type="submit" value="New Account">
        </form>
    </body>
</html>