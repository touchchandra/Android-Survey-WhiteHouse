<? include("config.php");?>

<?php
$SUCCESS = '100';
$ERROR	=  '400';
$NO_NEW_FORMS = '401';
$INVALID_ACTION = '402';




$action = (isset($_GET['action'])?($_GET['action']):"list");
	switch($action){
		case "list":
		
		    $user_id=(isset($GET['id'])?($_GET['action']):"");
			
			$sql = "SELECT * 
					FROM forms g,
						( SELECT form_id, user_id FROM  `form-user` WHERE user_id =7 )ff
					WHERE g.id = ff.form_id " ; 
			$myquery = mysql_query($sql) or die('{"res":"Query error"}');
			$json_array = array();
			while($rows = mysql_fetch_array($myquery)){
				//`id`, `name`, `description`, `user_id`
					
					$row_array['id'] = $rows['id'];
					$row_array['name'] = $rows['name'];
					$row_array['description'] = $rows['description'];
					$row_array['form_start_date'] = $rows['form_start_date'];
					$row_array['form_endate']=$rows['form_endate'];
					$row_array['author'] = $rows['author'];
					array_push($json_array,$row_array);	
			}
			$json_result['res'] = 'success';
			$json_result['data'] = $json_array;
			echo json_encode($json_result);
			break;
		case "view":
			if(!isset($_GET['id'])){
				die('{"res":"id not specified"}');
			}
			$sql = "SELECT * FROM `forms` WHERE `id` = ".$_GET['id'] ; 
			$myquery = mysql_query($sql) or die('{"res":"Query error"}');
			$row_array;
			while($rows = mysql_fetch_array($myquery)){//`id`, `name`, `user_id`, 
				$row_array['id'] = $rows['id'];
					$row_array['name'] = $rows['name'];
					$row_array['description'] = $rows['description'];
					$row_array['user_id'] = $rows['user_id'];			}
			$json_result['res'] = 'success';
			$json_result['data'] = $row_array;
			echo json_encode($json_result);
			break;
		case "add":
			$name = (isset($_GET['name'])?($_GET['name']):"");
			$description= (isset($_GET['description'])?($_GET['description']):"");
			$user_id = (isset($_GET['user_id'])?($_GET['user_id']):"");
		
			
			$sql = "INSERT INTO `forms` (`name`, `description`, `user_id`) VALUES ('".$name."', '".$description."', '".$user_id."')";
			//echo $sql;
			$myquery=mysql_query($sql);
			if($myquery){
				$json_result['res'] = 'success';
				echo json_encode($json_result);
			}
			else{
				$json_result['res'] = 'error';
				echo json_encode($json_result);
			}
			break;
		case "edit":
		if(!isset($_GET['id'])){
				die('{"res":"id not specified"}');
			}
			$id = $_GET['id'];
		$name = (isset($_GET['name'])?($_GET['name']):"");
			$description= (isset($_GET['description'])?($_GET['description']):"");
			$user_id = (isset($_GET['user_id'])?($_GET['user_id']):"");
			$sql = "UPDATE `forms` SET `name`='".$name."', `description`='".$description."', `user_id`='".$user_id."' WHERE `id`=".$id;
			//echo $sql;
			$myquery=mysql_query($sql);
			if($myquery){
				$json_result['res'] = 'success';
				echo json_encode($json_result);
			}
			else{
				$json_result['res'] = 'error';
				echo json_encode($json_result);
			}
			break;
		
		case "search":
			$q = (isset($_GET['q']))?($_GET['q']):"";
			$sql = "SELECT * FROM `forms` WHERE `name` LIKE '%".$q."%' OR `description` LIKE '%".$q."%'   LIMIT 0, 50";
			//echo $sql;
			$myquery = mysql_query($sql) or die('{"res":"Query error"}');
			$json_array = array();
			while($rows = mysql_fetch_array($myquery)){//`id`, `name`, `description`, 
					$row_array['id'] = $rows['id'];
					$row_array['name'] = $rows['name'];
					$row_array['description'] = $rows['description'];
					$row_array['user_id'] = $rows['user_id'];	
					array_push($json_array,$row_array);	
			}
			$json_result['res'] = 'success';
			$json_result['data'] = $json_array;
			echo json_encode($json_result);
			break;
		case "question":
			$id = (isset($_GET['form_id']))?($_GET['form_id']):"";
			
			$sql = "select * from `questions` where `ques_id` in (select `ques_id` from `form_ques` where `form_id`= ".$id.") ";
			//echo $sql;
			$myquery = mysql_query($sql) or die('{"res":"Query error"}');
			$json_array = array();
			/*while($rows1 = mysql_fetch_array($myquery)){
				$sql2="select * from `questions` where `ques_id`='$rows1[ques_id]'";
				$myquery2 = mysql_query($sql2) or die('{"res":"Query error"}');*/
				while($rows2 = mysql_fetch_array($myquery))
				{
				//`id`, `name`, `description`, 
					$row_array['id'] = $rows2['id'];
					$row_array['ques_id'] = $rows2['ques_id'];
					$row_array['ques'] = $rows2['ques'];
					$row_array['ans1'] = $rows2['ans1'];
					$row_array['ans2'] = $rows2['ans2'];
					$row_array['ans3'] = $rows2['ans3'];
					$row_array['ans4'] = $rows2['ans4'];
					$row_array['ans5'] = $rows2['ans5'];
					$row_array['type']=$rows2['type'];
					array_push($json_array,$row_array);	
				
			}
			$json_result['res'] = 'success';
			$json_result['data'] = $json_array;
			echo json_encode($json_result);
			break;
			//_______________________________________________________________________________________________________________
			case "form_ques":
			
			$id = (isset($_GET['form_id']))?($_GET['form_id']):"";
			
			$sql = "select * from `form_ques` where `form_id`='$id'";
			//echo $sql;
			$myquery = mysql_query($sql) or die('{"res":"Query error"}');
			$json_array = array();
			/*while($rows1 = mysql_fetch_array($myquery)){
				$sql2="select * from `questions` where `ques_id`='$rows1[ques_id]'";
				$myquery2 = mysql_query($sql2) or die('{"res":"Query error"}');*/
				while($rows2 = mysql_fetch_array($myquery))
				{
				//`id`, `name`, `description`, 
					$row_array['form_id'] = $rows2['form_id'];
					$row_array['ques_id'] = $rows2['ques_id'];
					
					
					array_push($json_array,$row_array);	
				
			}
			$json_result['res'] = 'success';
			$json_result['data'] = $json_array;
			echo json_encode($json_result);
			break;
			//___________________________________________________________________________________________________
		case "delete":
			if(!isset($_GET['id'])){
				die('{"res":"id not specified"}');
			}
			$sql = "DELETE FROM `forms` WHERE `id` = ".$_GET['id'] ; 
			$myquery = mysql_query($sql) or die('{"res":"Query error"}');
			if($myquery){
				$json_result['res'] = 'success';
				echo json_encode($json_result);
			}
			else
			{
				$json_result['res'] = 'error';
				echo json_encode($json_result);
			}
			break;
		case "saveuserans":
			if(!isset($_GET['id'])){
				die('{"res":"id not specified"}');
			}
			$user_id=$_GET['id'];
			$ques_id=$_GET['ques_id'];
			$ans_id=$_GET['ans_id'];
			//$sql = "DELETE FROM `user_ques_ans` where user_id=".$user_id." and ques_id=".$ques_id;
			//$myquery = mysql_query($sql) or die('{"res":"Query error"}');
			
			//
			$sql = "INSERT INTO `user_ques_ans`(`user_id`,`ques_id`,`ans_id`)values('".$user_id."','".$ques_id."','".$ans_id."')" ; 
			$myquery = mysql_query($sql) or die('{"res":"Query error"}');
			if($myquery){
				$json_result['res'] = 'success';
				echo json_encode($json_result);
			}
			else
			{
				$json_result['res'] = 'error';
				echo json_encode($json_result);
			}
			break;	
		case "getuser":
				$flag=0;
			$row_array=array();
			
			$json_array= array();
			$passworde = (isset($_GET['password'])?($_GET['password']):"");
			$password=md5($passworde);
			$user = (isset($_GET['username'])?($_GET['username']):"");
			
			
			$sqlpo=sprintf("Select * from `user` where `password`= '%s' and `username`='%s' ",mysql_real_escape_string($password),mysql_real_escape_string($user));
			//echo $sqlpo;
			if($sqllog=mysql_query($sqlpo))
			{
				if(mysql_num_rows($sqllog)==1)
				{
					$json_result['res']="success";
					$json_array = array();
					while($res100=mysql_fetch_array($sqllog)){
						$row_array['username']=$res100['username'];	
						$row_array['id']=$res100['id'];
						array_push($json_array,$row_array);	
					}					
					$json_result['res']="success";
					$json_result['data']=$json_array;
					echo json_encode($json_result);
				}else{
					$json_result['res']="fail";
					echo json_encode($json_result);
				}
			}
			else
			{	
				$json_result['res']="fail";
				echo json_encode($json_result);
			}
			
			break;
		case "checknewforms":
			$maxid = $_GET['maxformid'];
			$userid = $_GET['userid'];
			$sql = "SELECT * FROM `FORM-USER` WHERE USER_ID=".$userid." AND FORM_ID > ".$maxid."";
			//echo $sql;
			$result = mysql_query($sql) or die('{"res":"Query error"}');
			if($result){
				$num_rows = mysql_num_rows($result);
				if($num_rows>0)
				{
					$json_result['res'] = $SUCCESS;
					echo json_encode($json_result);
				}
				else
				{
					$json_result['res'] = $NO_NEW_FORMS;
					echo json_encode($json_result);
				}
			}
			else
			{
				$json_result['res'] = $ERROR;
				echo json_encode($json_result);
			}			
			break;
		case "getnewforms":
			$maxid = $_GET['maxformid'];
			$userid = $_GET['userid'];
			$sql = "SELECT * FROM `FORM-USER` WHERE USER_ID=".$userid." AND FORM_ID > ".$maxid."";
			//echo $sql;
			$result = mysql_query($sql) or die('{"res":"Query error"}');
			if($result){
				$num_rows = mysql_num_rows($result);
				if($num_rows>0)
				{
					$sql = "
							SELECT id,name,description,form_start_date,form_endate,author 
							FROM FORMS 
							WHERE ID IN (SELECT FORM_ID FROM `FORM-USER` WHERE USER_ID=".$userid." AND FORM_ID > ".$maxid.")";
					$myquery = mysql_query($sql) or die('{"res":"Query error"}');
					$json_array = array();
					while($rows = mysql_fetch_array($myquery))
					{
						$row_array['id'] = $rows['id'];
						$row_array['name'] = $rows['name'];
						$row_array['description'] = $rows['description'];
						$row_array['form_start_date'] = $rows['form_start_date'];
						$row_array['form_endate']=$rows['form_endate'];
						$row_array['author'] = $rows['author'];
						array_push($json_array,$row_array);	
					}
					$json_result['res'] = $SUCCESS;
					$json_result['data'] = $json_array;
					echo json_encode($json_result);
				}
				else{
					$json_result['res'] = $NO_NEW_FORMS;
					echo json_encode($json_result);
				}
			}
			else
			{
				$json_result['res'] = $ERROR;
				echo json_encode($json_result);
			}
			
			break;
		default:
				$json_result['res'] = $INVALID_ACTION;
				echo json_encode($json_result);
		
		
			
	}?>
