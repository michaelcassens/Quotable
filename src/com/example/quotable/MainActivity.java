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
		//String IPADDRESS = "10.17.7.231:8080";
		String IPADDRESS = "192.168.1.32:8080";
		Spinner S = (Spinner)findViewById(R.id.categorySpinner);
		Spinner A = (Spinner)findViewById(R.id.authorSpinner);
		ProgressBar B = (ProgressBar)findViewById(R.id.marker_progress);
		TextView T = (TextView) findViewById(R.id.quote);
		new CategoryTask(this,S, "getCategories", "categoryID", "category", null, IPADDRESS).execute();
		
		new CategoryTask(this,A, "getAuthors", "authorID", "author", B, IPADDRESS).execute();
		
		new CategoryTask(this,T, "getRandomQuote", "quoteID", "quote", null, IPADDRESS).execute();
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
