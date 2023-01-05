<?php
include('db_connect.php');

$stmt = $conn->prepare("SELECT product_id, customer_id, product_name, quantity, amount , product_picture FROM holdorder_tbl WHERE customer_id = '" . $_POST['customer_id'] ."'");

$stmt ->execute();
$stmt ->bind_result($product_id, $customer_id, $product_name, $quantity, $amount , $product_picture);

$products = array();

while ($stmt ->fetch())
{
	$temp = array();
	
	$temp['product_id'] = $product_id;
	$temp['customer_id'] = $customer_id;
	$temp['product_name'] = $product_name;
	$temp['quantity'] = $quantity;
	$temp['amount'] = $amount;
	$temp['product_picture'] = $product_picture;

	array_push($products,$temp);
}
	
echo json_encode($products);
?>