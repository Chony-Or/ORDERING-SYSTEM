<?php
require "DataBase.php";

$product_id = $_POST['product_id'];
$product_name = $_POST['product_name'];
$order_number = $_POST['order_number'];
$customer_id = $_POST['customer_id'];
$quantity = $_POST['quantity'];
$amount = $_POST['amount'];
$is_active = $_POST['is_active'];


$db = new DataBase();
    if ($db->dbConnect()) {
        if ($db->addPendingOrder("pendingorder_tbl", $product_id,$product_name, $order_number, $customer_id, $quantity, $amount , $is_active)) {
            echo "Add Order Success";
        } else echo "Add Order Failed";
    } else echo "Error: Database connection";
?>
