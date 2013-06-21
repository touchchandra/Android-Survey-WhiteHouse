/**
 * 
 */
package com.example.androidsurvey;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author Ashim
 *
 */
public class WelcomeScreen extends Activity{

	Button update;
	Button login;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		setContentView(R.layout.activity_welcomescreen);
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		login=(Button)findViewById(R.id.login);
		update=(Button)findViewById(R.id.updateur);
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent i=new Intent(getApplicationContext(),Loginnew.class);
				
				startActivity(i);
				// TODO Auto-generated method stub
				
			}
		});
		update.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent j=new Intent(getApplicationContext(),UpdateOnlyUser.class);
				startActivity(j);
				// TODO Auto-generated method stub
				
			}
		});
		
		
	}

	
	

	

}
