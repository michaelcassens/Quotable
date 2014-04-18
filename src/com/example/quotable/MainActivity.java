package com.example.quotable;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		Spinner S = (Spinner)findViewById(R.id.categorySpinner);
		Spinner A = (Spinner)findViewById(R.id.authorSpinner);
		ProgressBar B = (ProgressBar)findViewById(R.id.marker_progress);
		//TextView T = (TextView) findViewById(R.id.testText);
	//	new CategoryTask(this,S, "getCategories", "categoryID", "category", null).execute();
		
//		new CategoryTask(this,A, "getAuthors", "authorID", "author", B).execute();
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
