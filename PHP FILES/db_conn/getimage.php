<?php
require "DataBase.php"
$db = new DataBase();

$sql = "select id from images";
$result = array();

 if ($db->dbConnect()) {
	 
        $res = mysqli_query($db->dbConnect(),$sql);
		
		
		
    } else echo "Error: Database connection";



