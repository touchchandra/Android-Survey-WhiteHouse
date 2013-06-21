package com.example.androidsurvey;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import dao.DAO;
import dao.UserFunctions;

public class SubmitForm extends Activity {
	LinearLayout progress, submit_layout;
	Button submit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_submit_form);

		progress = (LinearLayout) findViewById(R.id.subit_progress);
		submit_layout = (LinearLayout) findViewById(R.id.submit_layout);
		progress.setVisibility(View.GONE);

		submit = (Button) findViewById(R.id.submit_now);
		submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// if (false |
				// !checkconnection.checkInternetConnection(getApplicationContext()))
				// {
//				if (false) {
//					DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
//
//						@Override
//						public void onClick(DialogInterface dialog, int which) {
//							switch (which) {
//							case DialogInterface.BUTTON_POSITIVE:
//								finish();
//								startActivity(new Intent(Settings.ACTION_SETTINGS));
//								break;
//							case DialogInterface.BUTTON_NEGATIVE:
//								finish();
//								break;
//							}
//						}
//					};
//					AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
//					builder.setMessage("Connect to Internet to submit!").setNegativeButton("No", dialogClickListener)
//							.setPositiveButton("Yes", dialogClickListener).show();
//
//				} else {
					MyAsycnTask asy = new MyAsycnTask();
					asy.execute();
//
//				}
//
			}
		});
		Button save_now = (Button) findViewById(R.id.save_now);
		save_now.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
				Intent i = new Intent(getApplicationContext(), Allforms.class);
				startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.submit_form, menu);
		return true;
	}

	private class MyAsycnTask extends AsyncTask<Void, Void, String> {

		@Override
		protected void onPreExecute() {
			submit_layout.setVisibility(View.GONE);
			progress.setVisibility(View.VISIBLE);
		}

		@Override
		protected String doInBackground(Void... params) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String res = "ERROR";
			UserFunctions uf = new UserFunctions();
			for (int i = 0; i < StartForm.form.size(); i++) {
				JSONObject jo = uf.saveUserAnswer(Loginnew.getUserID(getApplicationContext()),
						StartForm.form.get(i).getQues_id(), StartForm.form.get(i).userAnswers);
				try {
					res = jo.getString("res");
					if (res.equalsIgnoreCase("success")) {
						DAO dao = new DAO(getApplicationContext());
						dao.updateFormStatus(StartForm.form_id, 2);
						dao.close();
						res = "UPLOADED SUCCESSFULLY!";
						continue;
					} 

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					res = "ERROR UPLOADING, TRY AGIIN!!";
				}
				break;
			}

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return res;
		}

		@Override
		protected void onPostExecute(String result) {

			progress.setVisibility(View.GONE);

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Intent i = new Intent(getApplicationContext(), Allforms.class);
			startActivity(i);
			finish();

		}
	}

}
