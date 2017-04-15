<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
    $chatid = $_POST["chatid"];
    $fileString = "../TXT/$chatid.txt";
    $fileSize = filesize($fileString);
    $file = fopen($fileString,"a+");
    
    if ($fileSize > 0) {
        $postData = fread($file, $fileSize);
        $posts = "";
        
        if (strpos($postData, ".")) {
            $postArray = explode(".",$postData);
            
            for ($i = 0; $i < strlen($postArray); $i++) {
                $posts += base64_decode($postArray[$i]);
            }
        }
        
        else {
            $posts = base64_decode($postData);
        }
        echo $posts;
    }
?>