<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Login extends CI_Controller {
	
	function Login_construct()
	{
		parent::_construct();
		
	}

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
	 * So any other public methods not prefixed with an underscore will
	 * map to /index.php/welcome/<method_name>
	 * @see http://codeigniter.com/user_guide/general/urls.html
	 */
	public function index()
	{
		$this-> loginmain();
	}
	public function loginmain()
	
	{
		$this->form_validation->set_rules('username', 'username', 'required|trim|max_length[80]|xss_clean');
		$this->form_validation->set_rules('password', 'password', 'required|trim|max_length[100]|xss_clean');
		
		if($this->form_validation->run() == FALSE)
		{
		$this->load->view('view_login');
		if($this->session->flashdata('login_error'))
		{
			echo "wrong password";
		}
		}
		else
		{
			
			extract($_POST);
			
			$this->load->model('login_model');
			$user_id=$this->login_model->check_login($username, $password);
			if(!$user_id)
			
			{
			$this->session->set_flashdata('login_error',TRUE);
			redirect('login/loginmain');
			}
			else
			{
				$this->session->set_userdata(array('logged_in'=>TRUE, 'user_id'=>$user_id));
				redirect('home');
			}
			
			
		}
	}
	
	
	function logout()
	{
		$this->session->set_userdata('logged_in', FALSE);
		$this->session->sess_destroy();
		redirect('login/loginmain');
	}
	
}

/* End of file welcome.php */
/* Location: ./application/controllers/welcome.php */
/* End of file welcome.php */
/* Location: ./application/controllers/welcome.php */
