<?php
    try {
        $db = new PDO('mysql:host=localhost;dbname=gsndb', "root", "root"); //connects to database
        $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    }
    
    catch (Exception $e) {
        die('Could not connect to DB: ' . $e->getMessage());
    }

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
?>
