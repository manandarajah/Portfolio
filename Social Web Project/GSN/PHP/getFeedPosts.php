<?php
session_start();
include "GSNDB.php";
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$username = $_SESSION["username"];
$postEncoded = "";
$posts = "";

$statement = $db->prepare("select * from gsncontacts where contactuser1 = ? or contactuser2 = ?");
$array = array($username,$username);
$statement->execute($array);

while ($row = $statement->fetch()) {
    $contact = "";
    
    if ($row[1] != $username) {
        $contact = $row[1];
    }
    
    else {
        $contact = $row[2];
    }
    $statement2 = $db->prepare("select feed from feedtable where username = ? or username = ? group by feeddate desc");
    $array2 = array($username,$contact);
    $statement2->execute($array2);
    
    while ($row2 = $statement2->fetch()) {
        $posts .= base64_decode($row[0]);
    }
}

if ($posts != "") {
    echo $posts;
}
?>