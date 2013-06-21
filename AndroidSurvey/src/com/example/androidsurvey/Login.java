package com.example.androidsurvey;

import org.json.JSONArray;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import dao.DAO;
import data.Userdata;

public class Login extends Activity {

	public static final String PREF_NAME = "ANDROID_SURVERY";
	public static final String PREF_KEY_STATUS = "login";
	Button b1;
	EditText user;
	EditText pass;
	String username;
	String password;

	public boolean checkValidity(String username, String password) {

		DAO d = new DAO(this);
		Userdata datauser = null;

		datauser = d.checkUser(username, password);
		if (datauser.get_ascesskey().equalsIgnoreCase("ok") || true) {
			Intent i = new Intent(getApplicationContext(), Dashboaard.class);
			startActivity(i);
		}
		return false;

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		b1 = (Button) findViewById(R.id.submit);

		b1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				JSONArray jsar;
				String userget = null, id = null;
				String status = null;
				user = (EditText) findViewById(R.id.userfill);
				pass = (EditText) findViewById(R.id.passfill);
				username = user.getText().toString();
				password = pass.getText().toString();
				//
				// if (checkValidity(username, password)) {
				//
				// }

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
