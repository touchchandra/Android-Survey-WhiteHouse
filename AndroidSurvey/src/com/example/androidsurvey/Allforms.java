package com.example.androidsurvey;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import dao.DAO;
import data.Formsdata;

public class Allforms extends ListActivity {

	private DAO dao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_allforms);
		dao = new DAO(this);
		setListAdapter(new FormsAdapter(this, dao.getForms()));
		dao.close();

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Formsdata d = (Formsdata) getListAdapter().getItem(position);
		String Form_name = d.getName();
		String Form_author = d.getAuthor();
		String Form_start_date = d.getForm_start_date();
		String Form_endate = d.getForm_endate();
		String Form_des = d.getDescription();
		Integer form_i = d.getId();
		String f = form_i.toString();
		Intent start = new Intent(getApplicationContext(), StartForm.class);
		start.putExtra("name", Form_name);
		start.putExtra("des", Form_des);
		start.putExtra("author", Form_author);
		start.putExtra("stdate", Form_start_date);
		start.putExtra("edate", Form_endate);
		start.putExtra("id", f);
		finish();
		startActivity(start);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.allforms, menu);

		return true;
	}

	public static void getQuestionActivity(Context c, int index, int offset) {
		if (index + offset < StartForm.form.size()) {
			Intent i = null;
			if (StartForm.form.get(index + offset).getType() == SurveyConst.CHECKBOX_Q) {
				i = new Intent(c.getApplicationContext(), QuesCheckBox.class);
			} else if (StartForm.form.get(index + offset).getType() == SurveyConst.RADIO_Q) {
				i = new Intent(c.getApplicationContext(), QuesRadio.class);
				
			}
			 else if (StartForm.form.get(index + offset).getType() == SurveyConst.EDITTEXT_Q) {
				i = new Intent(c.getApplicationContext(), QuestEditText.class);
			 }
			i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			i.putExtra("index", index + offset);
			c.startActivity(i);

		} else {
			Intent i = new Intent(c, SubmitForm.class);
			i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			c.startActivity(i);
		}
		DAO dao = new DAO(c);
		dao.updateFormStatus(StartForm.form_id, 1);
		dao.close();
	}

	public static String getPageInfo(int index) {
		return (index + 1) + " of " + StartForm.form.size();
	}

}
