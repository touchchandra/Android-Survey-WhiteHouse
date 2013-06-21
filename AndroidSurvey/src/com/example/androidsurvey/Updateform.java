package com.example.androidsurvey;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import dao.DAO;
import dao.UserFunctions;
import data.Formsdata;

public class Updateform extends Activity {

	Formsdata datauser = null;
	LinearLayout progressbar;
	JSONArray jsary;
	String flag = " ";
	String userID;
	boolean NEW_FORMS = true;
	String count = "";
	TextView updatecount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_updateform);
		
		count = "";
		JSONArray jsary;
		progressbar = (LinearLayout) findViewById(R.id.progessbar);

		userID = Loginnew.getUserID(getApplicationContext());
		Log.d(Updatedata.class.getName(), "User ID :" + userID);

		Updatedata upd = new Updatedata();
		upd.execute();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.updateform, menu);
		return true;
	}

	private class Updatedata extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... arg0) {

			loadForm();
			// loadQuestion();

			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			

			// // TODO Auto-generated method stub
			progressbar.setVisibility(View.GONE);
			if (!NEW_FORMS) {

				(Toast.makeText(getApplicationContext(), "No New Forms Availiable", 10000)).show();
				finish();
				// Intent i = new Intent(getApplicationContext(),
				// Allforms.class);
				// startActivity(i);
			} else {

				(Toast.makeText(getApplicationContext(), count + " New Forms Downloaded", 10000)).show();
				finish();
			}
			//

			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {

			progressbar.setVisibility(View.VISIBLE);
			super.onPreExecute();
		}

	}

	public void loadForm() {
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DAO fDao = new DAO(getApplicationContext());
		int maxid = fDao.getMaxFormID();
		Log.d(Updateform.class.getName(), "Max Form id " + maxid + "");

		UserFunctions u = new UserFunctions();
		JSONObject job = u.getnewforms(userID, maxid);
		String res = "";
		try {
			res = job.getString("res");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			res = SurveyConst.ERROR;
		}

		if (res.equalsIgnoreCase(SurveyConst.SUCCESS)) {

			DAO d = new DAO(getApplicationContext());
			try {
				jsary = job.getJSONArray("data");
				for (int i = 0; i < jsary.length(); i++) {
					JSONObject temp = jsary.getJSONObject(i);

					String form_id = temp.getString("id");
					String formname = temp.getString("name");
					String author = temp.getString("author");
					String description = temp.getString("description");
					String start_date = temp.getString("form_start_date");
					String end_date = temp.getString("form_endate");

					d.addForms(Integer.parseInt(form_id), formname, description, start_date, end_date, author);

					loadQuestion(form_id);

				}
				count = jsary.length() + "";
				d.close();

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {

			}

			d.close();

		} else if (res.equalsIgnoreCase(SurveyConst.NO_NEW_FORMS)) {
			Log.d(Updateform.class.getName(), "No New Updates");
			NEW_FORMS = false;
			count = "0";
		}

	}

	public void loadQuestion(String formid) {
		DAO d2 = new DAO(this);
		UserFunctions u2 = new UserFunctions();
		JSONObject job2 = u2.individual(formid);
		try {
			JSONArray jsary2 = job2.getJSONArray("data");
			for (int j = 0; j < jsary2.length(); j++) {
				JSONObject newob = jsary2.getJSONObject(j);
				String ques = newob.getString("ques").equalsIgnoreCase("") ? "Empty" : newob.getString("ques");
				String idqs = formid.equalsIgnoreCase("") ? "Empty" : formid;
				String status = newob.getString("type").equalsIgnoreCase("") ? "Empty" : newob.getString("type");
				String ques_id = newob.getString("ques_id").equalsIgnoreCase("") ? "Empty" : newob.getString("ques_id");
				String ans1 = newob.getString("ans1").equalsIgnoreCase("") ? "Empty" : newob.getString("ans1");
				String ans2 = newob.getString("ans2").equalsIgnoreCase("") ? "Empty" : newob.getString("ans2");
				String ans3 = newob.getString("ans3").equalsIgnoreCase("") ? "Empty" : newob.getString("ans3");
				String ans4 = newob.getString("ans4").equalsIgnoreCase("") ? "Empty" : newob.getString("ans4");
				String ans5 = newob.getString("ans5").equalsIgnoreCase("") ? "Empty" : newob.getString("ans5");

				Integer idq = Integer.parseInt(idqs);
				Integer ques_ids = Integer.parseInt(ques_id);
				Integer types = Integer.parseInt(status);

				d2.addQuestions(Integer.parseInt(formid), ques, ans1, ans2, ans3, ans4, ans5, "1", types, ques_ids);

			}
			d2.close();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
