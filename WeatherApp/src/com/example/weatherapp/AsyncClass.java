package com.example.weatherapp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONException;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class AsyncClass extends AsyncTask<String, Void, String> {
    
		MainActivity newactivity;
        public AsyncClass(MainActivity Activity) {
        	newactivity=Activity;
        }
      
        /* 
        @Override
        protected void onPreExecute() {
            dialog.setTitle("Please wait");
            dialog.show();
        }
        */

        /*protected String doInBackground(String myparam) {
        	 
            //out.write(url);   // http://cs-server.usc.edu:26026/HW6_trial.php?location=
           	 //URL weather = new URL(url);
           	 //URLConnection urlconn = weather.openConnection();
        	return "OK";
        }*/
        
        @Override
		protected String doInBackground(String... arg) {
			// TODO Auto-generated method stub
        	String url=arg[0];
        	
        	try {
        	URL weather = new URL(url);
			URLConnection urlconn = weather.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(urlconn.getInputStream()));
         	  String inputLine="", inp=""; //int count=0; //inperr="abcde"; 
         	  while ((inputLine = in.readLine()) != null) {
      	//out.write(inputLine); 
         		  inp=inp+inputLine;
         // count=count+1;
         	  }
      	      in.close();
      	      return inp;
          //out.write(inperr);
       //out.write(inp); 

      	}
        	catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			
			return null;
		}

        @Override
        protected void onPostExecute(String retVal)
        {
            super.onPostExecute(retVal);
            try {
				newactivity.Jsonvalue(retVal);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

		
    }