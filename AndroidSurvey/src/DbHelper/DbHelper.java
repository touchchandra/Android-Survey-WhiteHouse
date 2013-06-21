package DbHelper;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DbHelper extends SQLiteOpenHelper {

	public static int VER = 1;
	Context context;

	public DbHelper(Context context) {
		super(context, "survey", null, VER);
		this.context = context;
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite
	 * .SQLiteDatabase answers -id question_id answer ans_id form-user -user_id
	 * form_id status forms -id name description form_start_date form_endate
	 * author form_ques -form_id ques_id status questions -id ques type ques_id
	 * ans1 ans2 ans3 ans4 ans5 ques_ans -ques_id,correct ans user -id username
	 * password telephone email c_fname c_lname
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		String createForms = "create table forms (_id integer , name text,description text,	"
				+ "form_start_date text,	form_endate text,	author text, status integer)";

		 String createQuestions =
		 "create table questions(form_id integer ,	ques text,	type integer,	ques_id integer,"
		 + "ans1 text,	ans2 text,	ans3 text,	ans4 text,	ans5 text, userans text) ";
		
		// String createAnswers =
		// "create table form-user(user_id int ,	form_id int,	status int )";
		//
		 String createFormQues =
		 "create table form_ques(ques_id integer,	form_id integer)";
		//
		// String createFormQuestion =
		// "create table ques_ans(ques_id int,correctans text)";
		//
		// String createQuesAns =
		// "create table answers(_id int primary key,	question_id int,	answer text,	ans_id int)";

		try {
			// db.execSQL(createUser);
			 db.execSQL(createQuestions);
			// db.execSQL(createQuesAns);
			db.execSQL(createForms);
			// db.execSQL(createAnswers);
			 db.execSQL(createFormQues);
			// db.execSQL(createFormUser);
		} catch (SQLException sl) {
			(Toast.makeText(context, "Task Incomplete", Toast.LENGTH_LONG))
					.show();
			sl.printStackTrace();
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
//		String deleteUser = "drop table if exist users ";
		String deleteForms = "drop table if exist forms";
		String deleteQuestions = "drop table if exist questions";
//		String deleteAnswers = "drop table if exist answers";
//		String deleteFormUser = "drop table if exist form-user";
//		String deleteFormQuestion = "drop table if exist form_ques";
//		String deleteQuesAns = "drop table if exist ques_ans";
		try {
//			db.execSQL(deleteUser);
			db.execSQL(deleteQuestions);
//			db.execSQL(deleteQuesAns);
			db.execSQL(deleteForms);
//			db.execSQL(deleteAnswers);
//			db.execSQL(deleteFormQuestion);
//			db.execSQL(deleteFormUser);
			onCreate(db);
		} catch (SQLException sl) {
			//(Toast.makeText(context, "Task Incomplete", Toast.LENGTH_LONG))
				//	.show();
			sl.printStackTrace();
		}

	}
	
	

}
