<?php
    session_start();
    include "GSNDB.php";

    $newuser;
    $contactNum;
    $chatid;
    $username;
    $inSystem = false;

    if (isset($_POST["newuser"]) && isset($_POST["contactCount"])) {
        $newuser = $_POST["newuser"];
        $username = $_SESSION["username"];
        $contactNum = $_POST["contactCount"];
        
        $newuserN = 0; //stores in the value of the new contact's username
        $usernameN = 0; //stores in the value of the user's username

        $statement = $db->prepare("SELECT * FROM gsnaccounts WHERE Username = ?");
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
                   $file = fopen("../TXT/$chatid.txt", "w+");
                   fclose($file);

           /*if ($chatid !== $username) {
               $statement = $db->prepare("INSERT INTO chatCount (ChatID) VALUES ('$chatid')"); //prepares to insert chat id into the chatCount table
               $statement->execute();
           }*/
           /*$statement = $db->prepare("SELECT FirstName,LastName FROM GSNAccount WHERE Username = ?");
           $statement->execute($array);

           if ($row = $statement->fetch()) {*/
               $statement = $db->prepare("INSERT INTO gsncontacts (contactuser1, contactuser2) VALUES ('$username','$newuser')"); //stores new contact info into user's table
               $statement->execute();
            $statement = $db->prepare("select * from gsnaccounts where username = ?");
            $statement->execute($array2);
            
            if ($row = $statement->fetch()) {
                echo "<a id=$row[0] onclick='chatBox(\"$row[0]\", \"$username\", \"$row[2] $row[3]\", $contactNum);' onmouseover='link(\"$row[0]\");' onmouseout='link(\"$row[0]\");'>$row[2] $row[3]</a><br>";
                $contactNum++;
            }
        }
    }
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

?>