<?php
 session_start();
 include "GSNDB.php";
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
    $idN;
    $idR;
    $url = "";
    $count = 0;
    $username = $_SESSION["username"];
    $fullname = "";
    $post = "";
    $posts = "";
    $canSendPost = false;
    
    $statement = $db->prepare("select firstname,lastname from gsnaccounts where username = ?");
    $array = array($username);
    $statement->execute($array);
    
    if ($row = $statement->fetch()) {
        $fullname = "$row[0] $row[1]";
    }
    
     $statement = $db->prepare("SELECT * FROM gsncontacts where contactuser1 = ? or contactuser2 = ?"); //gets contact username
     $array = array($username,$username);
     $statement->execute($array);
     
     while ($row = $statement->fetch()) {
         $col = 0;
         
         if ($row[1] === $username) {
             $col = 2;
         }
         
         else if ($row[2] === $username) {
             $col = 1;
         }
         
         if ($col != 0) {
            $idN = "$row[$col]$username"; //stores contact and username together
            $idR = "$username$row[$col]"; //stores contactn and username together in reverse
        }
         $id = "";
         
         if (isset($_POST[$idN."text"])) {
             $id = $idN;
         }
         
         else if (isset($_POST[$idR."text"])) {
             $id = $idR;
         }
         
         $post = "<a id=\"post\" class=\"$id\"><strong>$fullname: </strong>";
         
        if (isset($_POST[$id."text"])) {
            $text = $_POST[$id."text"];
            $post .= $text;
            $canSendPost = true;
        }
        $post .= "<br>";
         
         if ($_POST["canvas$count"."img"] !== "") {
            $url = $_POST["canvas$count"."img"];
            $post .= "<img src=$url style='display: inline';><br>";
            $canSendPost = true;
         }
         $post .= "</a>";
         
         if ($canSendPost) {
             $fileString = "../TXT/$id.txt";
             
             if (filesize($fileString) > 0) {
                $post = ".".base64_encode($post);
             }
             else {
                $post = base64_encode($post);
             }
             $file = fopen($fileString,"a+");
             fwrite($file, $post);
             fclose($file);
             /*$file = fopen($fileString,"r+");
             $postData = fread($file,filesize($fileString));
             $postList = explode($postData,".");
             
             for ($i = 0; i < count($postList); $i++) {
                $posts .= base64_decode($postList[$i]);
             }*/
         }
         $count++;
    }

?>