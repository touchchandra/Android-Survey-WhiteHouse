<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Genanswer extends CI_Controller {
	
	
function User_construct()
	{
		parent::_construct();
		
		
	}
	public function index()
	{
		
	
	}

public function selform()

{
	$this->load->model('genanswer_model');
	$data['fall']=$this->genanswer_model->getforms();
	$this->load->view('getforms_view',$data);
}
	
public  function  getquesid()
{
	extract($_POST);
	$formkonam=$formname;
	$this->load->model('genanswer_model');
	
	$data['vq']=$this->genanswer_model->getques($formkonam);
	 $this->load->view('view_eachquestiob',$data);
	
}

 public function genres($id,$qno,$ans1,$ans2,$ans3,$ans4,$ans5,$qt)
{
	$this->load->model('genanswer_model');
	$data['cont']=$this->genanswer_model->genansco($id);
	
	$data['question']=$qt;
	$data['quesno']=$qno;
	$data['ans1']=$ans1;
	$data['ans2']=$ans2;
	$data['ans3']=$ans3;
	$data['ans4']=$ans4;
	$data['ans5']=$ans5;
	
	$this->load->view('view_result',$data);
	
}





}
	