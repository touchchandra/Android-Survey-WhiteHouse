package com.example.androidsurvey;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import dao.DAO;

public class StartForm extends Activity implements OnClickListener {
	public static ArrayList<Question> form;
	public static int form_id;
	private DAO da;
	Button exit, start;
	TextView fname, desc, date, end, tvauthor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_form);

		Intent i = getIntent();
		String nam, des, sdat, edate, author, f_id;

		nam = i.getStringExtra("name");
		des = i.getStringExtra("des");
		author = i.getStringExtra("author");
		sdat = i.getStringExtra("stdate");
		edate = i.getStringExtra("edate");
		f_id = i.getStringExtra("id");
		form_id = Integer.parseInt(f_id);
		fname = (TextView) findViewById(R.id.form_name);

		desc = (TextView) findViewById(R.id.form_info);
		tvauthor = (TextView) findViewById(R.id.form_author);
		date = (TextView) findViewById(R.id.form_date);
		end = (TextView) findViewById(R.id.form_date);
		start = (Button) findViewById(R.id.start_form);
		exit = (Button) findViewById(R.id.exit_form);
		fname.setText(nam);

		desc.setText(des + "   ");
		tvauthor.setText("Author: " + author);
		date.setText("Start Date: " + sdat);
		end.setText("End date: " + edate);

		start.setOnClickListener(this);
		exit.setOnClickListener(this);
		da = new DAO(getApplicationContext());
		Intent intent = getIntent();
		form = new ArrayList<Question>();
		form = da.getQuestion(f_id);
		// AsyncConnection asyn = new AsyncConnection();
		// asyn.execute();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start_form, menu);
		return true;
	}

	// private class AsyncConnection extends AsyncTask<Void, Void, Void> {
	//
	// String ques, status, ans1, ans2, ans3, ans4, ans5;
	// String form_id;
	//
	//
	// @Override
	// protected void onPreExecute() {
	// // progresslayout.setVisibility(View.VISIBLE);
	// }
	//
	// @Override
	// protected Void doInBackground(Void... arg0) {
	// UserFunctions user = new UserFunctions();
	//
	// // Question q = new Question(ques,
	// // Integer.parseInt(status), 5);
	// // q.setAnswers(ans1, ans2, ans3, ans4, ans5);
	//
	// // form.add(q);
	//
	// //form_id = intent.getStringExtra("id");
	//
	//
	// // JSONObject JSob = user.individual(form_id);
	// // try {
	// // JSary = JSob.getJSONArray("data");
	// // for (int i = 0; i < JSary.length(); i++) {
	// // JSONObject newob = JSary.getJSONObject(i);
	// // try {
	//
	// // ques = newob.getString("ques").equalsIgnoreCase("") ? "Empty"
	// // : newob.getString("ques");
	// // status = newob.getString("type").equalsIgnoreCase("") ? "Empty"
	// // : newob.getString("type");
	// // ans1 = newob.getString("ans1").equalsIgnoreCase("") ? "Empty"
	// // : newob.getString("ans1");
	// // ans2 = newob.getString("ans2").equalsIgnoreCase("") ? "Empty"
	// // : newob.getString("ans2");
	// // ans3 = newob.getString("ans3").equalsIgnoreCase("") ? "Empty"
	// // : newob.getString("ans3");
	// // ans4 = newob.getString("ans4").equalsIgnoreCase("") ? "Empty"
	// // : newob.getString("ans4");
	// // ans5 = newob.getString("ans5").equalsIgnoreCase("") ? "Empty"
	// // : newob.getString("ans5");
	//
	//
	//
	// // question
	//
	// // } catch (JSONException e) {
	// // e.printStackTrace();
	// // }
	// //
	// // }
	// // } catch (JSONException e) {
	// // e.printStackTrace();
	// // }
	//
	// return null;
	// }
	//
	// @Override
	// protected void onPostExecute(Void result) {
	//
	// }
	// }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.start_form:
			int index = 0;
			Intent i = null;
			if (StartForm.form.get(index).getType() == SurveyConst.CHECKBOX_Q) {
				i = new Intent(getApplicationContext(), QuesCheckBox.class);
			} else if (StartForm.form.get(index).getType() == SurveyConst.RADIO_Q) {
				i = new Intent(getApplicationContext(), QuesRadio.class);
				
			}
			else if (StartForm.form.get(index).getType() == SurveyConst.EDITTEXT_Q) {
				i = new Intent(getApplicationContext(), QuestEditText.class);
			}
			i.putExtra("index", index);
			startActivity(i);
			finish();
			break;
		case R.id.exit_form:
			finish();
			break;
		default:
			(Toast.makeText(getApplicationContext(), "No Proper ID found", 4000)).show();
		}

	}

}
