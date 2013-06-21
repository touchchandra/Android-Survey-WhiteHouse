<?php

class Login_model extends CI_Model 
{
	
	
		function Login_model_construct()
	{
		parent::_Construct();
	}
	
	function check_login($username, $password)
	
	{
		$s_password = md5($password);
		$querystr=("select `id` from `user` where `username`= ? and `password`= ?");
		$result = $this->db->query($querystr, array($username, $s_password));
		if ($result->num_rows=1)
		{
			return $result->row(0)->id;
		}
		else
		{
			
		return FALSE;
			
		}
		 
		
	}
	function check()
	{
		return 1;
		
		
	}
}
