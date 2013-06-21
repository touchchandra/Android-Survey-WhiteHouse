package com.example.androidsurvey;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Dashboaard extends Activity {
	public static final String PREF_NAME = "ANDROID_SURVERY";

	Button b2, updatebtn;
	TextView t1;
	String id = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashboaard);
		
		
		t1 = (TextView) findViewById(R.id.user_update);
		b2 = (Button) findViewById(R.id.allforms);
		updatebtn = (Button) findViewById(R.id.updateforms);

		String username = Loginnew.getUserName(getApplicationContext());
		t1.setText(username);
		b2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				id = Loginnew.getUserID(getApplicationContext());
				Intent j = new Intent(getApplicationContext(), Allforms.class);
				j.putExtra("id", id);
				startActivity(j);
			}
		});

		updatebtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getApplicationContext(), Updateform.class);
				startActivity(i);
			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dashboaard, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {
		case R.id.action_logout:

			Loginnew.logout(getApplicationContext());
			Intent j = new Intent(getApplicationContext(), Loginnew.class);
			startActivity(j);
			finish();
		}
		return super.onMenuItemSelected(featureId, item);
	}

}
