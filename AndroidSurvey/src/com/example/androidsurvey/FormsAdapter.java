package com.example.androidsurvey;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import data.Formsdata;

public class FormsAdapter extends ArrayAdapter<Formsdata> {

	private final Context context;
	private final List<Formsdata> li;

	public FormsAdapter(Context context, List<Formsdata> li) {
		super(context, R.layout.activity_allforms, li);
		this.context = context;
		this.li = li;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView1 = inflater.inflate(R.layout.activity_allforms, parent, false);
		TextView formName = (TextView) rowView1.findViewById(R.id.form_name);
		TextView formAuthor = (TextView) rowView1.findViewById(R.id.datestart);
		TextView formdate = (TextView) rowView1.findViewById(R.id.author);
		View status = rowView1.findViewById(R.id.form_status);

		if (li.get(position).getStatus() == 0) {
			status.setBackgroundColor(Color.GREEN);
		} else if (li.get(position).getStatus() == 1) {
			status.setBackgroundColor(Color.BLUE);
		}else if(li.get(position).getStatus() == 2){
			status.setBackgroundColor(Color.RED);
		}

		formName.setText(li.get(position).getName());
		formAuthor.setText("Author: " + li.get(position).getAuthor());
		formdate.setText("Date : " + li.get(position).getForm_start_date());

		return rowView1;

		// TODO Auto-generated constructor stub
	}

}
