package com.example.androidsurvey;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import dao.UserFunctions;

public class Questions extends ListActivity {

	ArrayList<HashMap<String, String>> List_fillques;
	LinearLayout progresslayout;
    Button radio=null;
    Button checkbox=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_questions);
		List_fillques = new ArrayList<HashMap<String, String>>();
		progresslayout = (LinearLayout) findViewById(R.id.progesslayout_ques);
		radio = (Button) findViewById(R.id.b_radio);
		checkbox = (Button) findViewById(R.id.b_checkbox);
		AsyncConnection getdata = new AsyncConnection();
		getdata.execute();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.questions, menu);
		return true;
	}

	private class AsyncConnection extends AsyncTask<Void, Void, Void> {
		JSONArray JSary;
		String ques, status, ans1, ans2, ans3, ans4, ans5;
		String form_id;

		@Override
		protected void onPreExecute() {
			progresslayout.setVisibility(View.VISIBLE);
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			UserFunctions user = new UserFunctions();
			Bundle intent = getIntent().getExtras();

			form_id = intent.getString("id");

			JSONObject JSob = user.individual(form_id);
			try {
				JSary = JSob.getJSONArray("data");
				List_fillques.clear();
				for (int i = 0; i < JSary.length(); i++) 
				{
					JSONObject newob = JSary.getJSONObject(i);
					try {
						ques = newob.getString("ques").equalsIgnoreCase("") ? "Empty"
								: newob.getString("ques");
						status = newob.getString("type").equalsIgnoreCase("") ? "Empty"
								: newob.getString("type");
						ans1 = newob.getString("ans1").equalsIgnoreCase("") ? "Empty"
								: newob.getString("ans1");
						ans2 = newob.getString("ans2").equalsIgnoreCase("") ? "Empty"
								: newob.getString("ans2");
						ans3 = newob.getString("ans3").equalsIgnoreCase("") ? "Empty"
								: newob.getString("ans3");
						ans4 = newob.getString("ans4").equalsIgnoreCase("") ? "Empty"
								: newob.getString("ans4");
						ans5 = newob.getString("ans5").equalsIgnoreCase("") ? "Empty"
								: newob.getString("ans5");
						
					} catch (JSONException e) {
						e.printStackTrace();
					}
					HashMap<String, String> c_details = new HashMap<String, String>();

					c_details.put("ques", ques);
					c_details.put("type", status);
					// if(!ans1.matches(null))
					// {
					c_details.put("ans1", ans1);
					// }
					// if(!ans2.matches(null))
					// {
					c_details.put("ans2", ans2);
					// }
					// if(!ans3.matches(null))
					// {
					c_details.put("ans3", ans3);
					// }
					// if(!ans4.matches(null))
					// {
					c_details.put("ans4", ans4);
					// }
					// if(!ans5.matches(null))
					// {
					c_details.put("ans5", ans5);
					// }

					List_fillques.add(c_details);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			progresslayout.setVisibility(View.GONE);
			ListAdapter nn = new SimpleAdapter(getApplicationContext(),
					List_fillques, R.layout.listing_ques, new String[] {
							"ques", "type", "ans1", "ans2", "ans3", "ans4",
							"ans5" }, new int[] { R.id.queslist, R.id.sta,
							R.id.a1, R.id.a2, R.id.a3, R.id.a4, R.id.a5 });
			setListAdapter(nn);

			// ListView listview = getListView();
			// listview.setOnItemClickListener(new OnItemClickListener() {
			//
			// @Override
			// public void onItemClick(AdapterView<?> arg0, View arg1,
			// int position, long id) {
			// Log.d(Allforms.class.getName(), "CLick:"+position);

			// TODO Auto-generated method stub
			// String fname,lname,homeno,mobileno,officeno,idl;
			// try {
			// Intent in = new Intent(getApplicationContext(),
			// individual_list.class);
			// JSONObject tempobj = JSary.getJSONObject(position);
			//
			// idl = tempobj.getString("id");
			// in.putExtra("id", idl);
			//
			// if(tempobj.getString("c_fname")!=null){
			// fname = tempobj.getString("c_fname");
			// in.putExtra("fname", fname);
			// }
			// if(tempobj.getString("c_lname")!=null){
			// lname = tempobj.getString("c_lname");
			// in.putExtra("lname", lname);
			// }
			// if(tempobj.getString("n_home")!=null){
			// homeno = tempobj.getString("n_home");
			// in.putExtra("nhome", homeno);
			// }
			// if(tempobj.getString("n_mobile")!=null){
			// mobileno= tempobj.getString("n_mobile");
			// in.putExtra("n_mobile", mobileno);
			// }
			// if(tempobj.getString("n_office")!=null){
			// officeno = tempobj.getString("n_office");
			// in.putExtra("n_office", officeno);
			// }
			//
			// startActivity(in);
			// finish();
			//
			// } catch (JSONException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			//
			//
			// }
			//
			// });
			// }
		}
	}

}
