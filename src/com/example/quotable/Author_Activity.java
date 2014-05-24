package com.example.quotable;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;


public class Author_Activity  extends Activity  {

	String IPADDRESS = "192.168.56.1:8080";
	Spinner S;
	Spinner A;
	ProgressBar B;
	TextView T;
	int currentNumber = 0;
	int authorID = 0;
	int num1 = 0;
	ArrayList<MyObject> myList = null;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.author_main);
		S = (Spinner) findViewById(R.id.authorSpinner);
		B = (ProgressBar) findViewById(R.id.marker_progress);
		T = (TextView) findViewById(R.id.quote);

		new CategoryTask(this, S, "getAuthors", "authorID", "author",
				"", -1, B, IPADDRESS).execute();

		T.setMovementMethod(new ScrollingMovementMethod());
	
		
		//	RelativeLayout.LayoutParams lp = linLayout.getLayoutParams();
	
		//Button left = (Button)findViewById(R.id.leftButton);
		//left.setLayoutParams(params)
		
	}

public void fbClick(View v) {
		
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_TEXT, T.getText()+"");
		startActivity(Intent.createChooser(intent, "Share with"));
	}
	
	public void authorClick(View v) {

		currentNumber = 0;
		authorID = (int) S.getSelectedItemId();
		authorID++;
//T.setText("HI" + authorID);
		try {
			myList = new fillQuotes(this, T, "getQuotesByAuthor", "quoteID", "quote",
					"authorID", authorID, null, IPADDRESS, currentNumber)
					.execute().get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//int test = myList.size();
		//T.setText("HI" + authorID);
	
	//	if(myList != null)
			T.setText(myList.get(currentNumber).getKEY_SETNAME() + "\n--" + myList.get(currentNumber).getAuthor());
	}
	
	public void leftQuoteClick(View v)
	{
		
		
		if(currentNumber > 0)
		{
			currentNumber--;
		}

		T.setText(myList.get(currentNumber).getKEY_SETNAME() + "\n--" + myList.get(currentNumber).getAuthor());
		
	}
	
	public void rightQuoteClick(View v)
	{
		currentNumber++;
		if(currentNumber >= myList.size()-1)
		{
			currentNumber = myList.size()-1;
		}
//T.setText("current: " + currentNumber);
		T.setText(myList.get(currentNumber).getKEY_SETNAME() + "\n--" + myList.get(currentNumber).getAuthor());
		
	}

}
