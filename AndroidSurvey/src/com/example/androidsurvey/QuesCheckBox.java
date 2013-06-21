package com.example.androidsurvey;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import dao.DAO;

public class QuesCheckBox extends Activity {

	int index;
	Question ques;

	TextView question,pageinfoTV;
	CheckBox[] cb;
	int[] cbID = { R.id.checkbox_ans1, R.id.checkbox_ans2, R.id.checkbox_ans3, R.id.checkbox_ans4, R.id.checkbox_ans5,
			R.id.checkbox_ans6 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ques_check_box);

		Intent intent = getIntent();
		index = intent.getIntExtra("index", 0);
		ques = StartForm.form.get(index);

		question = (TextView) findViewById(R.id.checkbox_question);
		pageinfoTV = (TextView)findViewById(R.id.checkbox_pageinfo);
		pageinfoTV.setText(Allforms.getPageInfo(index));
		
		cb = new CheckBox[cbID.length];
		for (int i = 0; i < cbID.length; i++) {
			cb[i] = (CheckBox) findViewById(cbID[i]);
		}

		question.setText((index + 1) + ". " + ques.getQuestion());
		for (int i = 0; i < cb.length; i++) {

			if (i > ques.getAnswers().size() - 1) {

				cb[i].setVisibility(View.GONE);
			} else {
				Log.d(QuesCheckBox.class.getName(), "Question Answer :" + i + ". " + ques.getAnswers().get(i));
				if (!ques.getAnswers().get(i).equalsIgnoreCase("Empty")) {
					cb[i].setText(ques.getAnswers().get(i));
				} else {
					cb[i].setVisibility(View.GONE);
				}
			}
		}

		if (ques.getUserAnswer().size() > 0) {
			for (int i = 0; i < ques.getUserAnswer().size(); i++) {
				Log.d(QuesCheckBox.class.getName(), "Length :" + ques.getUserAnswer().size());
				cb[Integer.parseInt(ques.getUserAnswer().get(i))].setChecked(true);
			}
		}

		nextButtonEvent();
	}

	public void nextButtonEvent() {

		// chkIos = (CheckBox) findViewById(R.id.chkIos);
		// chkAndroid = (CheckBox) findViewById(R.id.chkAndroid);
		// chkWindows = (CheckBox) findViewById(R.id.chkWindows);
		// btnDisplay = (Button) findViewById(R.id.btnDisplay);

		Button nextBtn = (Button) findViewById(R.id.checkbox_next);
		Button prevBtn = (Button) findViewById(R.id.checkbox_previous);

		if (index == 0) {
			prevBtn.setVisibility(View.INVISIBLE);
		}

		nextBtn.setOnClickListener(new OnClickListener() {

			// Run when button is clicked
			@Override
			public void onClick(View v) {
				String result = "";
				for (int i = 0; i < cb.length; i++) {
					if (cb[i].isChecked()) {
						String ansid = StartForm.form.get(index).getIndexByAnswer(cb[i].getText() + "") + "";
						result += result.length() > 0 ? "," + ansid : ansid;
						Log.d(QuesCheckBox.class.getName(), "CheckBox User Answer : " + result);
					}
				}
				StartForm.form.get(index).setUserAnser(result);

				if (result.length() > 0) {
					DAO dao = new DAO(getApplicationContext());
					dao.saveUserAnswer(ques.getQues_id(), result);
					dao.close();
				}
				finish();
				Allforms.getQuestionActivity(getApplicationContext(), index, 1);
			}
		});

		prevBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
				Allforms.getQuestionActivity(getApplicationContext(), index, -1);

			}
		});

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode != KeyEvent.KEYCODE_BACK)
			return super.onKeyDown(keyCode, event);

		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case DialogInterface.BUTTON_POSITIVE:
					// Yes button clicked
					finish();
					break;

				case DialogInterface.BUTTON_NEGATIVE:
					// No button clicked
					break;
				}
			}
		};

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Save And Exit This Form?").setPositiveButton("Yes", dialogClickListener)
				.setNegativeButton("No", dialogClickListener).show();

		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.ques_check_box, menu);
		return true;
	}

}
