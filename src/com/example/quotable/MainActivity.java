package com.example.quotable;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {

	String IPADDRESS = "192.168.56.1:8080";
	Spinner S;
	Spinner A;
	ProgressBar B;
	TextView T;
	ArrayList<MyObject> myList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		 DrawView drawview = new DrawView(this);
		    setContentView(drawview);
		setContentView(R.layout.activity_main);

	//	RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)relativeLayout.getLayoutParams();
	//	params.setMargins(80, 0, 0, 0); //left, top, right, bottom
	//	params.height = 60; //set height dynamically
	//	params.width = 200; // set width dynamically
	//	relativeLayout.setLayoutParams(params);
		
		// S = (Spinner)findViewById(R.id.categorySpinner);
		// A = (Spinner)findViewById(R.id.authorSpinner);
		// B = (ProgressBar)findViewById(R.id.marker_progress);
		T = (TextView) findViewById(R.id.quote);

		// new CategoryTask(this,S, "getCategories", "categoryID",
		// "category","", -1, null, IPADDRESS).execute();

		// new CategoryTask(this,A, "getAuthors", "authorID", "author","", -1,
		// B, IPADDRESS).execute();

		new CategoryTask(this, T, "getRandomQuote", "quoteID", "quote", "", -1,
				null, IPADDRESS, myList).execute();

		T.setMovementMethod(new ScrollingMovementMethod());
		
	}

	public void fbClick(View v) {
		
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_TEXT, T.getText()+"");
		startActivity(Intent.createChooser(intent, "Share with"));
	}
	
	public void categoryClick(View v) {
		
			startActivity(new Intent(this, Category_Activity.class));
			// finish();
			// this.closeContextMenu();
			return;
			// case R.id.button2:
			// DO something
			// break;
		

	}

	
	public void authorClick(View v) {
		
		startActivity(new Intent(this, Author_Activity.class));
		// finish();
		// this.closeContextMenu();
		return;
		// case R.id.button2:
		// DO something
		// break;
	

}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
