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

<h1><?php echo $quesno .".";?><?php echo str_replace("%20"," ",$question)."?";?></h1><br/>
<?php 
 $i=0;
 
foreach($cont->result() as $rows)
{
	$i=$i+1;
	$j=0;
	echo "Provided answer(s) from respondent <b> ".$i."</b><br/>";
	$answer= $rows->ans_id;
	 $arr1=explode(",",$answer);
	 $flag=true;
	 for($j;$j<sizeof($arr1);$j++)
	 {
		 $flag=false;
		 if($arr1[$j]==0)
		 {
			 if($arr1[$j]!="Empty")
			 {
			 echo str_replace("%20"," ", $ans1);
			 
			 }
		 }
		
			 
		   if($arr1[$j]==1)
		 {
			  if($arr1[$j]!="Empty")
			 {
			 echo ",".str_replace("%20"," ", $ans2);
			 $flag=true;
			 }
		 }
		 
		  if($arr1[$j]==2)
		 {
			 if($arr1[$j]!="Empty")
			 {
			 echo ",".str_replace("%20"," ", $ans3);
			 $flag=true;
			 }
		 }
		  
		 if($arr1[$j]==3)
		 {
			 if($arr1[$j]!="Empty")
			 {
			 echo ",".str_replace("%20"," ", $ans4);
			 $flag=true;
			 }
		 }
		 
		 if($arr1[$j]==4)
		 {
			  if($arr1[$j]!="Empty")
			 {
			 echo ",".str_replace("%20"," ", $ans5);
			 $flag=true;
			 }
		 }
		if($flag!=true)
		{
			echo $arr1[$j];
		}
		  
			
								
							
							
						
						
					
				
			
			
		 
	 }
	 
	echo "<hr/>";
}?>
</div>
</body>
</html>