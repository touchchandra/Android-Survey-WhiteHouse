<?php

class User_model extends CI_Model 
{
	
	
		function Login_model_construct()
	{
		parent::_Construct();
	}

	function getuserinfo()
	{
		$querystring="Select * from `user` ";
		$result=$this->db->query($querystring);
		return $result;
	}
public function getreq($id)
{
	$querystr="delete from `user` where `id`='$id'";
	$this->db->query($querystr);
}
public function geteditreq($id)
{
	$querystr="select * from `user` where `id`='$id'";
	$result=$this->db->query($querystr);
	return $result;
}
public function updateachuser($ids,$username,$c_fname,$c_lname,$telephone,$email,$pass)
{
	$querystrg="update  `user` set  `username`='$username', `c_fname`='$c_fname', `c_lname`='$c_lname',`telephone`='$telephone', `passsword`='$pass',`email`='$email' where `id`='$ids'";
	$this->db->query($querystrg);
}
public function adduser($username,$password,$c_fname,$c_lname,$email,$telephone)
{
	$querystr="insert into `user` (`username`,`password`,`c_fname`,`c_lname`,`telephone`,`email`) values('$username','$password','$c_fname', '$c_lname','$telephone','$email')";
$this->db->query($querystr);
}


}