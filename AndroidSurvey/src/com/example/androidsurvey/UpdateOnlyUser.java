package com.example.androidsurvey;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.widget.LinearLayout;

public class UpdateOnlyUser extends Activity {
LinearLayout progressbar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_only_user);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.update_only_user, menu);
		return true;
	}
	
	
	
private class AsyncConnection extends AsyncTask<Void, Void, Void>
	{

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}
	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		
		super.onPostExecute(result);
	}

	

	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		
		return null;
	}
		
	}

}
