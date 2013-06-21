package dao;

import java.util.ArrayList;
import java.util.List;

import DbHelper.DbHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.androidsurvey.Question;

import data.Formsdata;
import data.Userdata;

public class DAO {
	private final SQLiteDatabase db;

	private final DbHelper dbHelper;

	public DAO(Context context) {

		dbHelper = new DbHelper(context);
		db = dbHelper.getWritableDatabase();
	}

	public void close() {
		db.close();
	}

	// fetching
	// Questions_________________________________________________________________________________
	public ArrayList<Question> getQuestion(String f_id) {
		ArrayList<Question> qli = new ArrayList<Question>();
		// String []tablecol= new
		// String[]{"_id","ques","type","ques_id","ans1","ans2","ans3","ans4","ans5"};
		// Cursor cursor1=db.query("forms", tablecol, null, null, null, null,
		// null);
		// Cursor cursor1 =
		// db.rawQuery("SELECT * FROM  questions a,(select * from form_ques where form_id="
		// + f_id
		// + ")b where a.ques_id=b.ques_id", null);
		Cursor cursor1 = db.rawQuery(
				"SELECT form_id,ques,type,ques_id,ans1,ans2,ans3,ans4,ans5,userans FROM  questions where form_id='" + f_id + "'",
				null);
		cursor1.moveToFirst();
		while (!cursor1.isAfterLast()) {
			Question aq = new Question(cursor1.getString(1), cursor1.getInt(2), cursor1.getInt(3), 5);
			aq.setAnswers(cursor1.getString(4), cursor1.getString(5), cursor1.getString(6), cursor1.getString(7),
					cursor1.getString(8));
			aq.setUserAnser(cursor1.getString(9));
			qli.add(aq);
			cursor1.moveToNext();
		}
		db.close();
		return qli;
	}

	// fetching
	// forms_________________________________________________________________________________________
	public List<Formsdata> getForms() {
		List<Formsdata> li = new ArrayList<Formsdata>();

		String columname[] = new String[] { "_id", "name", "description", "form_start_date", "form_endate", "author", "status" };
		Cursor cursor = db.query("forms", columname, null, null, null, null, " status asc");
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Formsdata fd = new Formsdata();

			fd.setId(cursor.getInt(0));
			fd.setName(cursor.getString(1));
			fd.setDescription(cursor.getString(2));
			fd.setForm_start_date(cursor.getString(3));
			fd.setForm_endate(cursor.getString(4));
			fd.setAuthor(cursor.getString(5));
			fd.setStatus(cursor.getInt(6));

			li.add(fd);
			cursor.moveToNext();

		}

		db.close();
		return li;
	}

	// Adding user
	// table______________________________________________________________________________________________________________
	public void addUser(int id, String username, int telephone, String email, String password, String c_fname, String c_lname) {
		ContentValues values = new ContentValues();
		values.put("c_fname", c_fname);
		values.put("id", id);
		values.put("c_lname", c_lname);
		values.put("username", username);
		values.put("password", password);
		values.put("telephone", telephone);
		values.put("email", email);
		db.insert("user", null, values);
		close();
	}

	// Adding
	// form-user_______________________________________________________________________________________________________________
	public void addFormUser(int user_id, int form_id, int status) {
		ContentValues values = new ContentValues();
		values.put("user_id", user_id);
		values.put("form_id", form_id);
		values.put("status", status);

		db.insert("user", null, values);
		close();
	}

	// Adding
	// fomrs____________________________________________________________________________________________________________________
	public void addForms(int id, String name, String description, String form_start_date, String form_endate, String author) {
		ContentValues values = new ContentValues();
		values.put("name", name);
		values.put("_id", id);
		values.put("description", description);
		values.put("form_start_date", form_start_date);
		values.put("form_endate", form_endate);
		values.put("author", author);
		db.insert("forms", null, values);

	}

	// Adding form
	// Questions_____________________________________________________________________________________________________________
	public void addFormQuestion(int form_id, int ques_id) {
		ContentValues values = new ContentValues();
		values.put("form_id", form_id);
		values.put("ques_id", ques_id);
		Log.d(DAO.class.getName(), "Form_id:" + form_id + " Ques:" + ques_id + "");
		db.insert("form_ques", null, values);
		db.close();
	}

	// Adding
	// Questions___________________________________________________________________________________________________________________
	public void addQuestions(int id, String ques, String ans1, String ans2, String ans3, String ans4, String ans5,
			String userans, int type, int ques_id) {
		ContentValues values = new ContentValues();
		values.put("ques", ques);
		values.put("form_id", id);
		values.put("ques_id", ques_id);
		values.put("ans1", ans1);
		values.put("ans2", ans2);
		values.put("ans3", ans3);
		values.put("ans4", ans4);
		values.put("ans5", ans5);
		values.put("userans", userans);
		values.put("type", type);

		db.insert("questions", null, values);

	}

	// add
	// QuestionAnswers____________________________________________________________________________________________________
	public void addQuestionAnswer(int ques_id, String Correct_ans) {
		ContentValues values = new ContentValues();
		values.put("ques_id", ques_id);
		values.put(Correct_ans, Correct_ans);

		db.insert("user", null, values);
		close();
	}

	// addAnswers
	// _________________________________________________________________________________________________________________
	public void addAnswers(int id, String answers, int ques_id, int ans_id) {
		ContentValues values = new ContentValues();
		values.put(answers, answers);
		values.put("id", id);
		values.put("ques_id", ques_id);
		values.put("ans_id", ans_id);

		db.insert("user", null, values);
		close();
	}

	// check
	// uservalidity_______________________________________________________________________________________________________________
	public Userdata checkUser(String username, String password) {
		Userdata data = new Userdata();
		// List<Userdata> userli = new ArrayList<Userdata>();
		String[] columns = new String[] { "id", "username" };
		Cursor cursor = db.query("user", columns, "username like" + "'username'", null, null, null, null);
		cursor.moveToFirst();
		int count = cursor.getCount();
		if (count != 1) {

			data.set_ascesskey("sidelag");
			return data;
		} else if (count == 1) {

			while (!cursor.isAfterLast()) {
				if (password.matches(cursor.getString(2))) {
					data.set_id(cursor.getInt(0));
					data.setUsername(cursor.getString(1));
					data.setC_fname(cursor.getString(5));
					data.setC_lname(cursor.getString(6));
					data.setEmail(cursor.getString(4));
					data.setTelephone(cursor.getInt(3));
					data.set_ascesskey("ok");
					// userli.add(data);

					return data;
				}

			}

		} else {

			data.set_ascesskey("sidelag");
			// userli.add(data);
			return data;
		}
		data.set_ascesskey("sidelag");
		// userli.add(data);
		return data;
	}

	public int getMaxFormID() {
		int id = 0;

		String query = "SELECT MAX(_id) AS max_id FROM forms";
		Cursor cursor = db.rawQuery(query, null);

		if (cursor.moveToFirst()) {
			do {
				id = cursor.getInt(0);
			} while (cursor.moveToNext());
		}
		close();
		return id;

	}

	public void saveUserAnswer(int index, String ans) {
		ContentValues values = new ContentValues();
		values.put("userans", ans);
		db.update("questions", values, "ques_id=" + index, null);
	}

	public void updateFormStatus(int formID, int status) {
		ContentValues values = new ContentValues();
		values.put("status", status + "");
		Log.d(DAO.class.getName(),"Updating satatu form : "+formID+" status :"+status);
		db.update("forms", values, "_id=" + formID, null);
	}

}
