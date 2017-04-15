<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//if (isset($_POST["attach"])) {
	/*$statement = $db->prepare("SELECT contactuser2 FROM gsncontacts where contactuser1 = ?");
	$statement->execute($array);
	
	while ($row = $statement->fetch()) {
		$idN = "$row[0]$username";
		$idR = "$username$row[0]";
		
		if (isset($_url[$idN])) {
			$id = $idN;
		}
		
		else if (isset($_url[$idR])) {
			$id = $idR;
		}*/
	
		 if (isset($_FILES['file'])) {
			$selected_file = $_FILES['file'];
			$ext = explode('.', $selected_file["name"]);
			$ext = strtolower(end($ext));
			
			if ($ext === "jpg" 
				|| $ext === "png" 
				|| $ext === "gif" 
				|| $ext === "tif"
				|| $ext === "tiff"
				|| $ext === "jpeg"
				|| $ext === "rif"
				|| $ext === "bmp"
				|| $ext === "pbm"
				|| $ext === "pgm"
				|| $ext === "ppm"
				|| $ext === "pnm"
				|| $ext === "webp"
				|| $ext === "hdr"
				|| $ext === "bpg") {
				$new_file = uniqid().".$ext";
				$dir = "../Uploads/$new_file";
				
				if (move_uploaded_file($selected_file["tmp_name"], $dir)) {
                                    echo $dir;
                                }
                        }
                }
        //}
//}
?>