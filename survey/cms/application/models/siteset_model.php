<?php 
class Siteset_model extends CI_Model 
{
	function Siteset_model_construct()
	{
		parent::_Construct();
	}
	
	
 public function updateSite($facebook,$twitter,$youtv)
 {
	
	$querystr=("update  `sitesetting` set`facebook`= ?, `twitter`= ?, `youtube`= ? where `id`=1");
	if($this->db->query($querystr, array($facebook,$twitter,$youtv)))
	{
		return true;
		
		
	}
	else
	{
		return "Error Updating Database";
	}
 
 
 
	
 }
 
 
 public function addCategory($name,$parent)
 {
	
	$querystr=("insert into `category` (`name`, `parent`) values ( ? , ? )");
	if($this->db->query($querystr, array($name,$parent)))
	{
		return true;
		
		
	}
	else
	{
		return "Error Updating Database";
	}
 
 
 
	
 }

public function display_content()
{
	$querystr=("select * from `sitesetting` where `id`=1");
	if($res=$this->db->query($querystr))
	{
		return $res->row_array();
	}
	else 
	{
		return "Error selecting database";
	}
}
public function display_category()
{
	$querystr=("select * from `category` where `parent`=0");
	if($res=$this->db->query($querystr))
	{
		return $res->row_array();
	}
	else 
	{
		return "Error selecting database";
	}
}






















}