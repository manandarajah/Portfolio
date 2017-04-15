<?php
session_start();
include "GSNDB.php";

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$username = $_SESSION["username"];
$fullname = "";
$post = "";
$postEncoded = "";
$url = "";

$statement = $db->prepare("select firstname,lastname from gsnaccounts where username = ?");
$array = array($username);
$statement->execute($array);
    
if ($row = $statement->fetch()) {
    $fullname = "$row[0] $row[1]";
}

if ($fullname != "") {
    $post = "<a id=\"feedpost\"><strong>$fullname</strong> posted on ".date("d:m:Y")." ".date("h:i:sa");
}

if (isset($_POST["feedtext"])) {
    $post .= "<br>".$_POST["feedtext"]."<br>";
}

if (isset($_POST["feedfile"])) {
    $url = $_POST["feedfile"];
    $post .= "<img src=$url style='display: inline';><br>";
}
$post .= "</a>";
$postEncoded = base64_encode($post);
$statement = $db->prepare("insert into feedtable values ($username, $postEncoded,NOW())");
$statement->execute();
?>