<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class User extends CI_Controller {
	
	function User_construct()
	{
		parent::_construct();
		$this->load->model('user_model');
	}
	public function index()
	{
		$this->getuser();
	
	}
	public function getuser()

{
	
	$this->load->model('user_model');
	
	$data['record']=$this->user_model->getuserinfo();
	
	$this->load->view('view_user',$data);
}
public function deleteuser($id)
{
	$this->load->model('user_model');
	$this->user_model->getreq($id);
	$this->getuser();
}
public function edituser($id)
{
	$this->load->model('user_model');
	$data['each']=$this->user_model->geteditreq($id);
	$data['id']=$id;
	$this->load->view('view_eachuser',$data);
	
	
	
}
public function loaduserform()
{
	$this->load->view('adduser');
}
public function adduserinfo()
{
	$this->load->model('user_model');
	extract($_POST);
	$pass=md5($password);
	
	$this->user_model->adduser($username,$pass,$c_fname,$c_lname,$email,$telephone);
	$this->getuser();
}
public function updateuser($ids)
{
	$this->load->model('user_model');
	extract($_POST);
	$pass=md5($password);
	$this->user_model->updateachuser($ids,$username,$c_fname,$c_lname,$telephone,$email,$pass);
	$this->getuser();
}
}