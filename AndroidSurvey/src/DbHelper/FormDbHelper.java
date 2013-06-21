package DbHelper;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class FormDbHelper extends SQLiteOpenHelper {

	public static int VER = 1;
	Context context;

	public FormDbHelper(Context context) {
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

		String createForms = "create table forms(_id integer primary key, name text,description text,	"
				+ "form_start_date text,	form_endate text,	author text)";
		try {
			db.execSQL(createForms);
		} catch (SQLException sl) {
			(Toast.makeText(context, "Task Incomplete", Toast.LENGTH_LONG)).show();
			sl.printStackTrace();
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		String deleteForms = "drop table if exist forms";
		try {
			db.execSQL(deleteForms);
			onCreate(db);
		} catch (SQLException sl) {
			(Toast.makeText(context, "Task Incomplete", Toast.LENGTH_LONG)).show();
			sl.printStackTrace();
		}

	}

}
