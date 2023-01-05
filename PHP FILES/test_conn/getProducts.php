<?php
include('db_connect.php');

$stmt = $conn->prepare("SELECT product_id, product_name, product_price, product_stock, product_code, product_picture FROM product_tbl");

$stmt ->execute();
$stmt ->bind_result($product_id, $product_name, $product_price, $product_stock, $product_code, $product_picture);

$products = array();

while ($stmt ->fetch())
{
	$temp = array();
	
	$temp['product_id'] = $product_id;
	$temp['product_name'] = $product_name;
	$temp['product_price'] = $product_price;
	$temp['product_stock'] = $product_stock;
	$temp['product_code'] = $product_code;
	$temp['product_picture'] = $product_picture;

	array_push($products,$temp);
}
	
echo json_encode($products);

?>