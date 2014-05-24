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

public class Category_Activity extends Activity {

	String IPADDRESS = "192.168.56.1:8080";
	Spinner S;
	Spinner A;
	ProgressBar B;
	TextView T;
	int currentNumber = 0;
	int categoryID = 0;
	int num1 = 0;
	ArrayList<MyObject> myList = null;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.categorymain);
		S = (Spinner) findViewById(R.id.categorySpinner);
		B = (ProgressBar) findViewById(R.id.marker_progress);
		T = (TextView) findViewById(R.id.quote);

		new CategoryTask(this, S, "getCategories", "categoryID", "category",
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
	
	public void categoryClick(View v) {

		currentNumber = 0;
		categoryID = (int) S.getSelectedItemId();
		categoryID++;

		try {
			myList = new fillQuotes(this, T, "getQuotesByCategory", "quoteID", "quote",
					"categoryID", categoryID, null, IPADDRESS, currentNumber)
					.execute().get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
		T.setText(myList.get(currentNumber).getKEY_SETNAME() + "\n--" + myList.get(currentNumber).getAuthor());
		
	}

}
