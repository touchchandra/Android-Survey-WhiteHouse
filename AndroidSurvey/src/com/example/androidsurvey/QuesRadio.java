package com.example.androidsurvey;

import java.util.ArrayList;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import dao.DAO;

public class QuesRadio extends Activity {
	int index;
	Question ques;

	TextView question, pageinfoTV;
	RadioButton[] rb;
	int[] rbID = { R.id.radio_ans1, R.id.radio_ans2, R.id.radio_ans3, R.id.radio_ans4, R.id.radio_ans5, R.id.radio_ans6 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ques_radio);

		Intent intent = getIntent();
		index = intent.getIntExtra("index", 0);
		ques = StartForm.form.get(index);

		question = (TextView) findViewById(R.id.radio_question);
		pageinfoTV = (TextView) findViewById(R.id.radio_pageinfo);
		pageinfoTV.setText(Allforms.getPageInfo(index));

		rb = new RadioButton[rbID.length];
		for (int i = 0; i < rbID.length; i++) {
			rb[i] = (RadioButton) findViewById(rbID[i]);
		}

		question.setText((index + 1) + ". " + ques.getQuestion());
		Log.d(QuesRadio.class.getName(), "User Anser " + ques.getUserAnswer().get(0));
		for (int i = 0; i < rb.length; i++) {

			if (i > ques.getAnswers().size() - 1) {

				rb[i].setVisibility(View.GONE);
			} else {
				Log.d(QuesCheckBox.class.getName(), "Question Answer :" + i + ". " + ques.getAnswers().get(i));
				if (!ques.getAnswers().get(i).equalsIgnoreCase("Empty")) {
					rb[i].setText(ques.getAnswers().get(i));
				} else {
					rb[i].setVisibility(View.GONE);
				}
			}
		}

		ArrayList<String> userAnswer = ques.getUserAnswer();
		for (int i = 0; i < userAnswer.size(); i++) {
			Log.d(QuesRadio.class.getName(), "Answer By User " + userAnswer.get(i));
			rb[Integer.parseInt(userAnswer.get(i))].setChecked(true);
		}

		addListenerOnButton();

	}

	public void addListenerOnButton() {

		final RadioGroup radioSexGroup = (RadioGroup) findViewById(R.id.radio_group);

		Button nextBtn = (Button) findViewById(R.id.radio_next);
		Button prevBtn = (Button) findViewById(R.id.radio_previous);

		if (index == 0) {
			prevBtn.setVisibility(View.INVISIBLE);
		}

		nextBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// get selected radio button from radioGroup
				int selectedId = radioSexGroup.getCheckedRadioButtonId();

				// find the radiobutton by returned id
				RadioButton radioSexButton = (RadioButton) findViewById(selectedId);

				//Toast.makeText(getApplicationContext(), "Selected : " + radioSexButton.getText(), Toast.LENGTH_SHORT).show();

				// Move to next quesion

				int anserid = (StartForm.form.get(index)).getIndexByAnswer(radioSexButton.getText() + "");
				StartForm.form.get(index).setUserAnser(anserid + "");
				Log.d(QuesRadio.class.getName(), anserid + " >> Save Answer : " + StartForm.form.get(index).getAnswers().get(0));

				DAO dao = new DAO(getApplicationContext());
				dao.saveUserAnswer(ques.getQues_id(), anserid + "");
				dao.close();
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
		// getMenuInflater().inflate(R.menu.ques_radio, menu);
		return true;
	}

	

}
