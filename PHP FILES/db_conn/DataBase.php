<?php
require "DataBaseConfig.php";

class DataBase
{
    public $connect;
    public $data;
    private $sql;
    protected $servername;
    protected $username;
    protected $password;
    protected $databasename;

    public function __construct()
    {
        $this->connect = null;
        $this->data = null;
        $this->sql = null;
        $dbc = new DataBaseConfig();
        $this->servername = $dbc->servername;
        $this->username = $dbc->username;
        $this->password = $dbc->password;
        $this->databasename = $dbc->databasename;
    }

    function dbConnect()
    {
        $this->connect = mysqli_connect($this->servername, $this->username, $this->password, $this->databasename);
        return $this->connect;
    }

    function prepareData($data)
    {
        return mysqli_real_escape_string($this->connect, stripslashes(htmlspecialchars($data)));
    }

    function logIn($table, $customer_name, $customer_password)
    {
        $customer_name = $this->prepareData($customer_name);
        $customer_password = $this->prepareData($customer_password);
        $this->sql = "select * from " . $table . " where customer_name = '" . $customer_name . "'";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbusername = $row['customer_name']; //$row is the row in database table
            $dbpassword = $row['customer_password'];
            if ($dbusername == $customer_name && $customer_password == $dbpassword) {
                $login = true;
            } else $login = false;
        } else $login = false;

        return $login;
    }

    function signUp($table, $fullname, $email, $username, $password)
    {
        $fullname = $this->prepareData($fullname);
        $username = $this->prepareData($username);
        $password = $this->prepareData($password);
        $email = $this->prepareData($email);
        $password = password_hash($password, PASSWORD_DEFAULT);
        $this->sql =
            "INSERT INTO " . $table . " (fullname, username, password, email) VALUES ('" . $fullname . "','" . $username . "','" . $password . "','" . $email . "')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }
	
	 function addOrder($table, $product_id, $customer_id, $product_name, $quantity, $amount , $product_picture)
    {
        $product_id = $this->prepareData($product_id);
        $customer_id = $this->prepareData($customer_id);
        $product_name = $this->prepareData($product_name);
		$quantity = $this->prepareData($quantity);
		$amount = $this->prepareData($amount);
		$product_picture = $this->prepareData($product_picture);
		
		
		
		$this->sql = "select * from " . $table . " where product_id = '" . $product_id . "'";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
		
        if (mysqli_num_rows($result) != 0) {
            $this->sql2 =
            "UPDATE " . $table . " SET customer_id = '" . $customer_id . "', product_name = '" . $product_name . "', quantity = '" . $quantity . "', amount = '" . $amount . "' WHERE product_id = '" . $product_id . "'";
			if (mysqli_query($this->connect, $this->sql2)) {
				return true;
			} else return false;
			
        } else {
			$this->sql2 =
            "INSERT INTO " . $table . " (product_id, customer_id, product_name, quantity, amount , product_picture) VALUES ('" . $product_id . "','" . $customer_id . "','" . $product_name . "','" . $quantity . "','" . $amount . "','" . $product_picture . "')";
			if (mysqli_query($this->connect, $this->sql2)) {
				return true;
			} else return false;
			
		}
    }
	
	function addPendingOrder($table, $product_id,$product_name, $order_number ,$customer_id, $quantity, $amount , $is_active)
    {
        $product_id = $this->prepareData($product_id);
		$product_name = $this->prepareData($product_name);
		$order_number = $this->prepareData($order_number);
        $customer_id = $this->prepareData($customer_id);
		$quantity = $this->prepareData($quantity);
		$amount = $this->prepareData($amount);
		$is_active = $this->prepareData($is_active);
		
	
		$this->sql2 =
		"INSERT INTO " . $table . " (product_id, product_name, order_number, customer_id, quantity, amount , is_active) VALUES ('" . $product_id . "','" . $product_name . "','" . $order_number . "','" . $customer_id . "','" . $quantity . "','" . $amount . "','" . $is_active . "')";
		if (mysqli_query($this->connect, $this->sql2)) {
			return true;
		} else return false;
		
    }
	
	 function deleteProduct($table, $product_id) 
    {
        $this->sql =
             "DELETE from " . $table . " where product_id = '" . $product_id . "'";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }

}

?>
