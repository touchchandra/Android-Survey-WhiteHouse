package com.example.library;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

public class UserFunctions {
	
private final JSONParser jsonParser;
	
	//private static String URL = "http://katibajyo.com/api/contacto.php";
	//private static String URL = "http://127.1.1.1/contacto/contacto.php";
	private static String URL = "http://10.0.2.2:80/survey/form_api.php";
	

	private static String add_tag = "add";
	private static String delete_tag = "delete";
	private static String view_tag = "view";
	private static String list_tag = "list";
	private static String edit_tag = "edit";
	public UserFunctions()
	{
		jsonParser = new JSONParser();
	}
public JSONObject alllist(String id){
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("action", list_tag));
		params.add(new BasicNameValuePair("id", id));
		JSONObject json = jsonParser.getJSONFromUrl(URL, params);
		return json;
	}
public JSONObject individual(String f_id){
	
	List<NameValuePair> params = new ArrayList<NameValuePair>();
	params.add(new BasicNameValuePair("action", "question"));
	params.add(new BasicNameValuePair("form_id", f_id));
	
	JSONObject json = jsonParser.getJSONFromUrl(URL, params);
	return json;
}
public JSONObject getUser(String user,String pass)
{
	List<NameValuePair> params = new ArrayList<NameValuePair>();
	params.add(new BasicNameValuePair("action","getuser"));
	params.add(new BasicNameValuePair("username", user));
	params.add(new BasicNameValuePair("password", pass));
	
	JSONObject json=jsonParser.getJSONFromUrl(URL, params);
	return json;
}
}
