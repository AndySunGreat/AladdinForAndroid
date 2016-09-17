package com.prgguru.example;

import android.app.Activity;
import android.os.Bundle;
/***
 * Home Screen Activity
 * 
 * @author AndySun
 *
 */

public class HomeActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
	}
}
