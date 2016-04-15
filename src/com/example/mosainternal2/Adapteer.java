package com.example.mosainternal2;


import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Adapteer extends ArrayAdapter{
	
	Activity cc;
	int res;
	
	int[] code;
	String[] BookName;
	String[] Author;
	int[] yearOfPublishing;
	

	public Adapteer(Activity context, int resource, int [] a, String[] objects, String [] b, int[] c) {
		super(context, resource, objects);
		
		this.cc=context;
		this.res=resource;
		this.code=a;
		this.BookName=objects;
		this.Author=b;
		this.yearOfPublishing=	c;	

		Log.e("con", "in view1");
		
	}

	

	@Override
	public View getView(int position, View v, ViewGroup parent) {

		Log.e("hi", "in view");
		LayoutInflater inflater=cc.getLayoutInflater();
        View rowView=inflater.inflate(res, null,true);
        
		TextView tv = (TextView)rowView.findViewById(R.id.textView1);
		tv.setText(code[position]+""); 
	
		TextView tt =(TextView)rowView.findViewById(R.id.textView2);
		tt.setText(BookName[position]+"");
		
		TextView tm =(TextView)rowView.findViewById(R.id.textView3);
		tm.setText(Author[position]+"years");
		
		TextView ts =(TextView)rowView.findViewById(R.id.textView4);
		ts.setText(yearOfPublishing[position]+"");

		return rowView;
	}
	

	
	

}
