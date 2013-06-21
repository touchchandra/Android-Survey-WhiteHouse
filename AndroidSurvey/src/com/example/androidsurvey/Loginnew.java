package com.example.androidsurvey;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import dao.UserFunctions;

public class Loginnew extends Activity {

	public static final String PREF_NAME = "ANDROID_SURVERY";
	public static final String PREF_KEY_STATUS = "login";
	public static final String PREF_KEY_USERNAME = "username";
	public static final String PREF_KEY_USERID = "userid";
	Button loginBtn;
	EditText user;
	EditText pass;
	String username;
	String password;
	final String LOG_TAG = Loginnew.class.getName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		loginBtn = (Button) findViewById(R.id.submit);
		setTitle("Login Android Survery");
		
		loginBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				MyAsyncTask async = new MyAsyncTask();
				async.execute();

			}
		});

	}

	

	public void saveUser(String username, String id) {
		SharedPreferences pref = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
		Editor edit = pref.edit();
		edit.putBoolean(PREF_KEY_STATUS, true);
		edit.putString(PREF_KEY_USERNAME, username);
		edit.putString(PREF_KEY_USERID, id);
		edit.commit();
	}

	public void logOut() {
		SharedPreferences pref = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
		Editor edit = pref.edit();
		edit.putBoolean(PREF_KEY_STATUS, false);
		edit.remove(PREF_KEY_USERNAME);
		edit.remove(PREF_KEY_USERID);
		edit.commit();
	}

	public static String getUserName(Context c) {
		SharedPreferences pref = c.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
		return pref.getString(PREF_KEY_USERNAME, "dummy");
	}

	public static String getUserID(Context c) {
		SharedPreferences pref = c.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
		return pref.getString(PREF_KEY_USERID, "99999");
	}

	public static boolean userAlreadyExists(Context c) {

		SharedPreferences pref = c.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
		return pref.getBoolean(PREF_KEY_STATUS, false);
	}

	private class MyAsyncTask extends AsyncTask<Void, Void, Boolean> {

		@Override
		protected Boolean doInBackground(Void... params) {
			JSONArray jsar;
			String userget = null, id = null;
			String status = null;
			user = (EditText) findViewById(R.id.userfill);
			pass = (EditText) findViewById(R.id.passfill);
			username = user.getText().toString();
			password = pass.getText().toString();

			if (userAlreadyExists(getApplicationContext())) {
				return true;
			} else {

				UserFunctions chuser = new UserFunctions();
				JSONObject json = chuser.getUser(username, password);
				String res = "";
				try {

					res = json.getString("res");
					if (res.equalsIgnoreCase("success")) {

						jsar = json.getJSONArray("data");

						JSONObject newob = jsar.getJSONObject(0);
						userget = newob.getString("username");
						id = newob.getString("id");

						Log.d(LOG_TAG, "Status : " + status + " Username : " + userget + " Passwored :" + password);

						saveUser(userget, id);
						return true;
					} else
						Log.d(LOG_TAG, "Logging Failed");
					return false;

				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}

			}

		}

		@Override
		protected void onPostExecute(Boolean res) {
			if (res == true) {
				Intent i = new Intent(getApplicationContext(), Dashboaard.class);
				i.putExtra("userid", getUserID(getApplicationContext()));
				finish();
				startActivity(i);
			} else {
				(Toast.makeText(getApplicationContext(), "Wrong Username or Password", 2000)).show();
			}

			return;
		}

	}
	
	public static void logout(Context c){

		SharedPreferences pref = c.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
		Editor e = pref.edit();
		e.remove(PREF_KEY_STATUS);
		e.remove(PREF_KEY_STATUS);
		e.remove(PREF_KEY_STATUS);
		e.commit();
	}

}
