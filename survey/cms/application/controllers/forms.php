<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class User extends CI_Controller {
	
function User_construct()
	{
		parent::_construct();
		$this->load->model('user_model');
	}
	
	public function getforms($userid)
	{
		$this->load->model('forms_model');
		$data=$this->form_model->getforms($user_id);
		$this->load->view('view_showforms');
		
		
	}
	public function getal()
	{
		extract($_POST);
		$this->load->model('form_model');
	}
	
}