package dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.example.library.JSONParser;

public class UserFunctions {

	private final JSONParser jsonParser;

	// private static String URL = "http://katibajyo.com/api/contacto.php";
	// private static String URL = "http://127.1.1.1/contacto/contacto.php";
	private static String URL = "http://192.168.33.50:80/survey/form_api.php";
	//private static String URL = "http://192.168.2.3:80/survey/form_api.php";
	//private static String URL = "http://192.168.1.4:80/survey/form_api.php";
	//private static String URL = "http://192.168.0.102:80/survey/form_api.php";
	//private static String URL = "http://192.168.206.54:80/survey/form_api.php";

	private static String add_tag = "add";
	private static String delete_tag = "delete";
	private static String view_tag = "view";
	private static String list_tag = "list";
	private static String edit_tag = "edit";
	private static String getNewForms_tag = "getnewforms";
	private static String saveuserans_tag = "saveuserans";

	public UserFunctions() {
		jsonParser = new JSONParser();
	}

	public JSONObject alllist(String id) {

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("action", list_tag));
		params.add(new BasicNameValuePair("id", id));
		JSONObject json = jsonParser.getJSONFromUrl(URL, params);
		return json;
	}

	public JSONObject getnewforms(String userid, int maxFormId) {

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("action", getNewForms_tag));
		params.add(new BasicNameValuePair("userid", userid));
		params.add(new BasicNameValuePair("maxformid", maxFormId + ""));

		JSONObject json = jsonParser.getJSONFromUrl(URL, params);
		return json;
	}

	public JSONObject individual(String f_id) {

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("action", "question"));
		params.add(new BasicNameValuePair("form_id", f_id));

		JSONObject json = jsonParser.getJSONFromUrl(URL, params);
		return json;
	}

	public JSONObject from_ques(String f_id) {

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("action", "form_ques"));
		params.add(new BasicNameValuePair("form_id", f_id));

		JSONObject json = jsonParser.getJSONFromUrl(URL, params);
		return json;
	}

	public JSONObject getUser(String user, String pass) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("action", "getuser"));
		params.add(new BasicNameValuePair("username", user));
		params.add(new BasicNameValuePair("password", pass));

		JSONObject json = jsonParser.getJSONFromUrl(URL, params);
		return json;
	}

	public JSONObject getNewForms(String userid, int maxFormId) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("action", getNewForms_tag));
		params.add(new BasicNameValuePair("userid", userid));
		params.add(new BasicNameValuePair("maxformid", maxFormId + ""));

		JSONObject json = jsonParser.getJSONFromUrl(URL, params);
		return json;
	}

	public JSONObject saveUserAnswer(String userid, int ques_id, String ans_id) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("action", "saveuserans"));
		params.add(new BasicNameValuePair("id", userid));
		params.add(new BasicNameValuePair("ques_id", ques_id + ""));
		params.add(new BasicNameValuePair("ans_id", ans_id));

		JSONObject json = jsonParser.getJSONFromUrl(URL, params);
		return json;
	}
}
