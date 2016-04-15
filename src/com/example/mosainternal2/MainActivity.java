package com.example.mosainternal2;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener {

	JSONArray jsoncode;
	JSONArray jsonBookName;
	JSONArray jsonAuthor;
	JSONArray jsonyearOfPublishing;
	
	int[] code = {1,2,3};
	String[] BookName ={"","",""};
	String[] Author={"","",""};
	int[] yearOfPublishing= {1,2,3};
	public String internetData=null;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		new Asyncc().execute();
		
		
	}
	
	public class Asyncc extends AsyncTask<Void,Void,String>{

		@Override
		protected String doInBackground(Void... params) {
			
			try{
				URL url = new URL("http://10.0.2.2/jjson.php");
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setDoInput(true);
				con.setDoOutput(true);
				con.setRequestMethod("POST");
				
				con.connect();
				
				InputStream is= con.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader bf = new BufferedReader(isr);
				
				String line=null;
				while((line = bf.readLine())!=null){
					internetData=line;
				}
				
				
				
				JSONObject jo = new JSONObject(internetData);
				jsoncode = new JSONObject(internetData).getJSONArray("code");
				jsonBookName = new JSONObject(internetData).getJSONArray("BookName");
				jsonAuthor = new JSONObject(internetData).getJSONArray("Author");
				jsonyearOfPublishing = new JSONObject(internetData).getJSONArray("yearOfPublishing");
				
				int i=0;
				while(i<jsoncode.length()){
					code[i]=jsoncode.getJSONObject(i).getInt("code");
					BookName[i] = jsonBookName.getJSONObject(i).getString("BookName");
					Author[i] = jsonAuthor.getJSONObject(i).getString("Author");
					yearOfPublishing[i] = jsonyearOfPublishing.getJSONObject(i).getInt("yearOfPublishing");

					i++;
				}
				
				
			}
			
			catch(Exception e){
				e.printStackTrace();
			}
			
			 
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			
			ListView lv = (ListView)findViewById(R.id.listView1);
			
			Adapteer ad = new Adapteer(MainActivity.this, R.layout.item, code,BookName,Author,yearOfPublishing);
			lv.setAdapter(ad);
			
			lv.setOnItemClickListener(MainActivity.this);
			
		}
		
		
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Toast.makeText(this, position+"", Toast.LENGTH_SHORT).show();
		
		
	}


	
}
