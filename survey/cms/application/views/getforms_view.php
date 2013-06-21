<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>adminpanel</title>
<link href="css/style.css" rel="stylesheet" media="screen" />
</head>
<style>
*{
	margin:0px;
	padding:0px;
}
#wrapper
{
	width:1000px;
	
	
}
#topmenu
{
	height:40px;
	width:1000px;
	background:url(<?php echo base_url();?>/asset/images/topbar.png);
	
	
}
#topmenuleft
{
	color:#FFF;
	font-family:Verdana, Geneva, sans-serif;
	font-size:16px;
	padding-top:10px;
	float:left;
	width:500px;
	text-align:left;
	padding-left:10px;
	
	
}
#topmenuright
{
	
	font-size:16px;
	padding-top:10px;
	text-align:right;
	
	width:990px;
	
	
}
#topmenuright a
{
	text-decoration:none;
	color:#FFF;
	font-family:Verdana, Geneva, sans-serif;
	font-size:16px;
	text-align:right;
	
	
}
#topmenuright a:hover
{
	text-decoration:underline;
}
.clear
{
	clear:both;
}
#boddy
{
	height:250px;
	width:1000px;
}
#box1
{
	float:left;
	display:block;
	width:200px;
	height:100px;
	background:#066;
	margin-right:20px;
	margin-top:20px;
	margin-left:30px;
	
}
</style>
<body>
<div align="center">
<div id="wrapper">
<div id="topmenu"><div id="topmenuleft">WELCOME: ADMIN </div><div id="topmenuright"><a href="<?php echo base_url();?>index.php/login/logout">LOGOUT</a><div class="clear"></div></div></div>
<div id="boddy" align="left">
<div id="box1"><a href="<?php echo base_url();?>/index.php/user/getuser"><img src="<?php echo base_url();?>/asset/images/manageuser.png" /></a></div>
<div id="box1"><img src="<?php echo base_url();?>/asset/images/manageforms.png" /></div>
<div id="box1"><img src="<?php echo base_url();?>/asset/images/managequestions.png" /></div>
<div id="box1"><a href="<?php echo base_url();?>/index.php/genanswer/selform"><img src="<?php echo base_url();?>/asset/images/genres.png" /></a></div>

</div>
<hr />
<style>
.fwhite
{
	color:#FFF;
}
</style>
<form method="post" action="<?php echo base_url();?>index.php/genanswer/getquesid">
Please Select the Forms	:
  <select name="formname" >  
 <?php 
 foreach($fall->result() as $row)
 { 

?>

<br/>
<option value="<?php echo $row->id;?>" > <?php echo $row->name;?></option>


  <? } ?>
  </select>
  <input type="submit" name="submin" value="go" />
</table>
<form>
</div>
</body>
</html>