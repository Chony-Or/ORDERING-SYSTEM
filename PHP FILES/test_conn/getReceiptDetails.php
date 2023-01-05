<?php
include('db_connect.php');

$stmt = $conn->prepare("SELECT product_id, product_name, order_number, quantity, amount FROM pendingorder_tbl WHERE customer_id = '" . $_POST['customer_id'] ."'");

$stmt ->execute();
$stmt ->bind_result($product_id, $product_name, $order_number, $quantity, $amount);

$products = array();

while ($stmt ->fetch())
{ 
	$temp = array();
	
	$temp['product_id'] = $product_id;
	$temp['product_name'] = $product_name;
	$temp['order_number'] = $order_number;
	$temp['quantity'] = $quantity;
	$temp['amount'] = $amount;

	array_push($products,$temp);
}
	
echo json_encode($products);
?>