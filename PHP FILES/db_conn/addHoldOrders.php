<?php
require "DataBase.php";

$product_id = $_POST['product_id'];
$customer_id = $_POST['customer_id'];
$product_name = $_POST['product_name'];
$quantity = $_POST['quantity'];
$amount = $_POST['amount'];
$product_picture = $_POST['product_picture'];


$db = new DataBase();
    if ($db->dbConnect()) {
        if ($db->addOrder("holdorder_tbl", $product_id, $customer_id, $product_name, $quantity, $amount , $product_picture)) {
            echo "Add Order Success";
        } else echo "Add Order Failed";
    } else echo "Error: Database connection";
?>
