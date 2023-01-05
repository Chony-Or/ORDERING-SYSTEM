<?php
include('db_connect.php');

$name = $_POST['customer_name'];

if (isset($_POST['customer_name'])) {

	$stmt = $conn->prepare("SELECT customer_id, customer_name, customer_contactNo, customer_address, customerClass_id FROM customer_tbl WHERE customer_name = '".$_POST['customer_name']."'");

	$stmt ->execute();
	$stmt ->bind_result($customer_id, $customer_name, $customer_contactNo, $customer_address, $customerClass_id);

	$customer_data = array();

	while ($stmt ->fetch())
	{
		$temp = array();
		
		$temp['customer_id'] = $customer_id;
		$temp['customer_name'] = $customer_name;
		$temp['customer_contactNo'] = $customer_contactNo;
		$temp['customer_address'] = $customer_address;
		$temp['customerClass_id'] = $customerClass_id;

		array_push($customer_data,$temp);
	}
	echo json_encode($customer_data);
}
?>