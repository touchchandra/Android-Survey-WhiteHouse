<?php

class Form_model extends CI_Model 
{

	function Form_model_construct()
	{
		parent::_Construct();
	}
	
	
		public function getforms()
		{
			$querystr="select * from  `forms` ";
			$result=$this->db->query($querystr);
		return $result;
		}
		/*public function getques($id)
		 {
		$qry="SELECT * 
FROM questions a, (

SELECT * 
FROM form_ques
WHERE form_id ='$id'
)b
WHERE a.ques_id = b.ques_id";
		$result=$this->db->query($qry);
		return $result;
		 }
		 
		 public function genansco($id)
		 {
			 $sqlstr="select `ans_id` from `user_ques_ans` where `ques_id`='$id'";
			 $result=$this->db->query($sqlstr);
			 return $result;
		 }*/
}