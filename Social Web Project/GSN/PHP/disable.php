<?php
	session_start();
	include 'GSNDB.php';
	
	$username = $_SESSION["username"];
	$statement = $db->prepare("UPDATE GSNAccount SET Disable = 1 WHERE Username = ?");
	$array = array($username);
	$statement->execute($array);
	/*$statement = $db->prepare("SELECT ContactUsername FROM $username");
	$statement->execute();
	
	while ($row = $statement->fetch()) {
		$statement2 = $db->prepare("UPDATE $row[0] SET Disable = 1 WHERE ContactUsername = ?");
		$statement2->execute($array);
	}*/
	header('Location: ../index.html');
?>