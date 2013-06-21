<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Home extends CI_Controller {

	/**
	 * Index Page for this controller.
	 *
	 * Maps to the following URL
	 * 		http://example.com/index.php/welcome
	 *	- or -  
	 * 		http://example.com/index.php/welcome/index
	 *	- or -
	 * Since this controller is set as the default controller in 
	 * config/routes.php, it's displayed at http://example.com/
	 *
	 * So any  other public methods not prefixed with an underscore will
	 * map to /index.php/welcome/<method_name>
	 * @see http://codeigniter.com/user_guide/general/urls.html
	
		*/
		
	 function Home()
		{
			parent::__Construct();
			$this->load->model('siteset_model');
			
			
		}
		
		public function index()
		{
			
			if($this->session->userdata('logged_in'))
			{
				
				$this->load->view('view_home');
				
			
			
			}
			
		}
		
		public function get_data()
		{
			
			if($this->session->userdata('logged_in'))
			{
			
			if(!extract($_POST))
			{
			
			$data =$this->siteset_model->display_content();
				 
			 	$this->load->view('view_home',$data);
			 
			}
			else
			{
			
			 if($this->siteset_model->updateSite($username,$twit,$youtube))
			 {
				 
				 $data =$this->siteset_model->display_content();
				 
			 	$this->load->view('view_home',$data);
			 }
			 
				
			}
			}
		}
		public function adduser()
		{
			
		}
		public function get_category()
		{
			
			if($this->session->userdata('logged_in'))
			{
			$this->get_data();
			if(!extract($_POST))
			{
			
			$data =$this->siteset_model->display_category();
				 
			 	$this->load->view('view_home',$data);
			 
			}
			else
			{
			
			 if($this->siteset_model->addCategory($categ,$parent))
			 {
				 
				 $data =$this->siteset_model->display_category();
				 
			 	$this->load->view('view_home',$data);
			 }
			 
				
			}
			}
		}
			
			
			
			
			
		
			
		
		
		
}