<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['customer_name']) && isset($_POST['customer_password'])) {
    if ($db->dbConnect()) {
        if ($db->logIn("customer_tbl", $_POST['customer_name'], $_POST['customer_password'])) {
            echo "Login Success";
        } else echo "Username or Password wrong";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>
