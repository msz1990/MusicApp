package com.msz.interview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.msz.interview.R;

public class Solution extends Activity implements OnClickListener {
    private TextView textview1;
    private TextView textview2;
    private TextView textview3;
    private TextView textview4;
    private TextView textview5;
    
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);
	    //Method 1 attach an instance of HandleClick class to the Button
	    findViewById(R.id.button1).setOnClickListener(new HandleClick());
	    //Method 2 use the handleClick variable to attach the event listener
	    findViewById(R.id.button2).setOnClickListener(handleClick);
	    //Method 3 anonymous inner class to attach the event listener
	    findViewById(R.id.button3).setOnClickListener(new OnClickListener(){
	    	public void onClick(View arg0) {
  			        // TODO: Display clicked event in textview3
                textview3.setText(button3.getText().toString());
	    	}
	    });

	    //Method 4 Implementation in Activity
	    findViewById(R.id.button4).setOnClickListener(this);
       
       findViewById(R.id.button5).setOnClickListener(this);
       findViewById(R.id.button6).setOnClickListener(this);
       findViewById(R.id.button7).setOnClickListener(this);
      
       textview1=(TextView)findViewById(R.id.textview1);
       textview2=(TextView)findViewById(R.id.textview2);
       textview3=(TextView)findViewById(R.id.textview3);
       textview4=(TextView)findViewById(R.id.textview4);
       textview5=(TextView)findViewById(R.id.textview5);
          
       button1=(Button)findViewById(R.id.button1);
       button2=(Button)findViewById(R.id.button2);
       button3=(Button)findViewById(R.id.button3);
       button4=(Button)findViewById(R.id.button4);
       button5=(Button)findViewById(R.id.button5);
       button6=(Button)findViewById(R.id.button6);
       button7=(Button)findViewById(R.id.button7);
	  }
	  //Method 1 a call implementing onClickListener
	  private class HandleClick implements OnClickListener{
	    public void onClick(View arg0) {
	        // TODO: Display clicked event in textview1
          textview1.setText(button1.getText().toString());
	    }
	  }
	  //Method 2 a variable declared as a type of interface
	  private OnClickListener handleClick = new OnClickListener(){
	    public void onClick(View arg0) {
	        // TODO: Display clicked event in textview2
            textview2.setText(button2.getText().toString());
	        }
	  };
	  //Method 4 Implementation on the onClickListener by the Activity
	  public void onClick(View arg0) {
	        // TODO: Display clicked event in textview4
          if(arg0.getId()==R.id.button4)
              textview4.setText(button4.getText().toString());
          else
              HandleClickByAttribute(arg0); 
          
	  }
	  public void HandleClickByAttribute(View arg0) {
	        // TODO: Display clicked event in textview5
		  int id=arg0.getId();
          switch(id){
            case R.id.button5:
              textview5.setText(button5.getText().toString());
              break;
            case R.id.button6:
              textview5.setText(button6.getText().toString());
              break;
            case R.id.button7:
              textview5.setText(button7.getText().toString());
              break;
            default:
              break;
          }
	  }
}
