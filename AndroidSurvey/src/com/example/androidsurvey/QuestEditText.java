package com.example.androidsurvey;

import java.util.ArrayList;

import dao.DAO;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuestEditText extends Activity {

	int index;
	Question ques;

	TextView question, pageinfoTV;
	EditText[] rb;
	int[] rbID = { R.id.editText_question };


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quest_edit_text);

		Intent intent = getIntent();
		index = intent.getIntExtra("index", 0);
		
		ques = StartForm.form.get(index);
		
		question = (TextView) findViewById(R.id.Text_question);
		pageinfoTV = (TextView) findViewById(R.id.radio_pageinfo);
		pageinfoTV.setText(Allforms.getPageInfo(index));

//		rb = new EditText[rbID.length];
//		for (int i = 0; i < rbID.length; i++) {
//			rb[i] = (EditText) findViewById(rbID[i]);
//		}

		question.setText((index + 1) + ". " + ques.getQuestion());
     Log.d(QuesRadio.class.getName(), "User Anser " + ques.getUserAnswer().get(0));
//		for (int i = 0; i < rb.length; i++) {
//
//			if (i > ques.getAnswers().size() - 1) {
//
//				rb[i].setVisibility(View.GONE);
//			} else {
//				Log.d(QuesCheckBox.class.getName(), "Question Answer :" + i + ". " + ques.getAnswers().get(i));
//				if (!ques.getAnswers().get(i).equalsIgnoreCase("Empty")) {
//					rb[i].setText(ques.getAnswers().get(i));
//				} else {
//					rb[i].setVisibility(View.GONE);
//				}
//			}
//		}

//		ArrayList<String> userAnswer = ques.getUserAnswer();
//		for (int i = 0; i < userAnswer.size(); i++) {
//			Log.d(QuesRadio.class.getName(), "Answer By User " + userAnswer.get(i));
//			rb[Integer.parseInt(userAnswer.get(i))].setChecked(true);
//	}

		addListenerOnButton();

	}

	public void addListenerOnButton() {

		 
		 
		
		Button nextBtn = (Button) findViewById(R.id.radio_next);
		Button prevBtn = (Button) findViewById(R.id.radio_previous);

		if (index == 0) {
			prevBtn.setVisibility(View.INVISIBLE);
		}

		nextBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// get selected radio button from radioGroup
				//int selectedId = radioSexGroup.getCheckedRadioButtonId();
				
				//String tee=t1.getText().toString();

				// find the radiobutton by returned id
				//RadioButton radioSexButton = (RadioButton) findViewById(selectedId);

				//Toast.makeText(getApplicationContext(), "Selected : " + radioSexButton.getText(), Toast.LENGTH_SHORT).show();

				// Move to next quesion
				 EditText t1= (EditText)findViewById(R.id.editText_question);
				
				 String tee=t1.getText().toString();
				//String  anserid = (StartForm.form.get(index)).getIndexByAnswer(t1.getText() + "");
				StartForm.form.get(index).setUserAnser(tee);
				//Log.d(QuesRadio.class.getName(), anserid + " >> Save Answer : " + StartForm.form.get(index).getAnswers().get(0));

				DAO dao = new DAO(getApplicationContext());
				dao.saveUserAnswer(ques.getQues_id(), tee);
				dao.close();
				finish();
				Allforms.getQuestionActivity(getApplicationContext(), index, 1);

			}

		});

//		prevBtn.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				finish();
//				Allforms.getQuestionActivity(getApplicationContext(), index, -1);
//
//			}
//		});

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
