package com.example.androidsurvey;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	public static final String PREF_NAME = "ANDROID_SURVERY";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Typeface ty = Typeface.createFromAsset(getAssets(), "fonts/BEWARE__.TTF");
		((TextView) findViewById(R.id.logo1)).setTypeface(ty);
		((TextView) findViewById(R.id.logo2)).setTypeface(ty);
		StartAnimations();
		try {
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					// startActivity(new Intent(SplashScreen.this, Main.class));
					// finish();
					if (Loginnew.userAlreadyExists(getApplicationContext())) {
						
						finish();
						startActivity(new Intent(getApplicationContext(), Dashboaard.class));

					} else {
						
						finish();
						startActivity(new Intent(getApplicationContext(), Loginnew.class));
					}

				}
			}, 6000);
		} catch (Exception e) {
			Log.d(MainActivity.class.getName(), "Loading Error");
		}

	}

	@Override
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
		Window window = getWindow();
		window.setFormat(PixelFormat.RGBA_8888);
	}

	private void StartAnimations() {
		Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
		anim.reset();
		LinearLayout l = (LinearLayout) findViewById(R.id.lin_lay);
		l.clearAnimation();
		l.startAnimation(anim);

		anim = AnimationUtils.loadAnimation(this, R.anim.translate);
		anim.reset();
		LinearLayout iv = (LinearLayout) findViewById(R.id.slide);
		iv.clearAnimation();
		iv.startAnimation(anim);

	}

}
