 <?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
session_start();
include "GSNDB.php";

$username = $_SESSION["username"]; //gets username from session
$first = ""; //gets first name
$statement = $db->prepare("SELECT FirstName FROM GSNAccount WHERE Username = ?");
$a = array($username);
$statement->execute($a);

if ($row = $statement->fetch()) {
    $first = $row[0]; 
}
$statement = $db->prepare("SELECT * FROM $username"); //gets data from personal table
$statement->execute();

while ($row = $statement->fetch()) {
    $statement2 = $db->prepare("SELECT Username FROM GSNAccount WHERE FirstName = ?");
    $array = array($row[0]); //stores in username from social table
    $statement2->execute($array);
    
    if ($row2 = $statement2->fetch()) {
        $statement3 = $db->prepare("DELETE FROM gsncontacts WHERE contactuser1 = ? or contactuser2 = ?"); //preparing to delete yourself from other contacts
        $array2 = array($first,$first); //stores in first name in array
        $statement3->execute($array2);
    }
}
/*$statement = $db->prepare("DROP TABLE $username"); //deletes personal table
$statement->execute();*/
$statement = $db->prepare("DELETE FROM GSNAccount WHERE Username = ?"); //erases your data from the social table
$array3 = array($username); //stores username into array
$statement->execute($array3);
/*$statement = $db->prepare("SELECT * FROM chatCount"); //selects data from chat box ids
$statement->execute();

while ($row = $statement->fetch()) {
    if (strstr($row[1], $username)) {
        $statement2 = $db->prepare("DROP TABLE $row[0]"); //prepares to delete chatbox ids associated with the user
        $statement2->execute();
		$statement2 = $db->prepare("SELECT chatID FROM chatCount WHERE charID LIKE '%$username%'");
		$statement2->execute();
		
		while ($row2 = $statement2->fetch()) {
			unlink("../TXT/$row[0].txt");
		}
        $statement2 = $db->prepare("DELETE FROM chatCount WHERE chatID LIKE '%$username%'");
        $statement2->execute();
    }
}*/
header('Location: ../index.html'); //goes back to log in page
?>