<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>login</title>

</head>

<body>
<style>
#box
{
	width:300px;
	height:200px;
	color:#FFF;
	margin-top:200px;
	font-family:Verdana, Geneva, sans-serifl;
	background:url(<?php echo base_url().'/asset/images/background.png';?>);
}
.inoutform
{
	width:200px;
	height:20px;
}
.butn1
{
	background:#000;
	color:#FFF;
	font-family:Verdana, Geneva, sans-serif;
	height:20px;
	width:60px;
	border:none;
	box-shadow: 0 0 20px #A2A2A2;
	
}
body
{
	background:#333;
}
</style>
<?php $urld =base_url(); ?>
<img src="<?php echo $urld;?>/asset/images/Lock.png" width="50" height="50" />
<div align="center">

<div id="box">
<table>
<form name="ashim" method="post">

<tr>
<td height="65">Username </td> <td><input type="text" name="username" class="inoutform" /></td></tr>
<tr>
<td height="65">Password </td> <td><input type="password" name="password" class="inoutform" /></td></tr>
<tr><td colspan="2"><input type="submit" value="Log In" class="butn1" /></td></tr>
</form>
</table>
</div>
</div>
</body>
</html>