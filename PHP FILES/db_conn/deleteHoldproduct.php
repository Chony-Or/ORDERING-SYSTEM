<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['product_id'])) {
    if ($db->dbConnect()) {
        if ($db->deleteProduct("holdorder_tbl", $_POST['product_id'])) {
            echo "Delete Success";
        } else echo "Delete Unsuccessful";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>
