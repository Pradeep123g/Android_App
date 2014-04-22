/**
 * Copyright 2010-present Facebook.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.weatherapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.LoggingBehavior;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.Settings;
import com.facebook.widget.WebDialog;
import com.facebook.widget.WebDialog.OnCompleteListener;

public class LoginUsingActivityActivity extends Activity 
{
    //private static final String URL_PREFIX_FRIENDS = "https://graph.facebook.com/me/friends?access_token=";

    //private TextView textInstructionsOrLink;
    private Button buttonLoginLogout;
    private Session.StatusCallback statusCallback = new SessionStatusCallback();

    //JSONObject jsonobject = new JSONObject();
    //jsonobject=MainActivity.jsonobj;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        buttonLoginLogout = (Button)findViewById(R.id.buttonLoginLogout);
        //textInstructionsOrLink = (TextView)findViewById(R.id.instructionsOrLink);
        
        
        
        Settings.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);

        Session session = Session.getActiveSession();
        if (session == null) {
            if (savedInstanceState != null) {
                session = Session.restoreSession(this, null, statusCallback, savedInstanceState);
            }
            if (session == null) {
                session = new Session(this);
            }
            Session.setActiveSession(session);
            if (session.getState().equals(SessionState.CREATED_TOKEN_LOADED)) {
                session.openForRead(new Session.OpenRequest(this).setCallback(statusCallback));
            }
        }

        updateView();
    }

    @Override
    public void onStart() {
        super.onStart();
        Session.getActiveSession().addCallback(statusCallback);
    }

    @Override
    public void onStop() {
        super.onStop();
        Session.getActiveSession().removeCallback(statusCallback);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Session session = Session.getActiveSession();
        Session.saveSession(session, outState);
    }
    
    
    

	private void publishFeedDialogCurrent() {
	    Bundle params = new Bundle();

	    
	    JSONObject jsonloc;
	    
		try {
			jsonloc = MainActivity.jsonobj.getJSONObject("location");
			JSONObject jsoncon = MainActivity.jsonobj.getJSONObject("condition");
			JSONObject jsonunits = MainActivity.jsonobj.getJSONObject("units");
			
			JSONObject json1 = new JSONObject();
			JSONObject json2 = new JSONObject();
			json1.put("text", "here");
			json1.put("href", MainActivity.jsonobj.getString("link"));
			json2.put("Look at details", json1);


	    params.putString("name", jsonloc.getString("@city")+","+jsonloc.getString("@region")+","+jsonloc.getString("@country"));
	    params.putString("caption", "The current condition for "+ jsonloc.getString("@city")+" is "+jsoncon.getString("@text"));
	    params.putString("description", "Temperature is "+jsoncon.getString("@temp")+"\u00b0"+jsonunits.getString("@temperature"));
	    params.putString("link", MainActivity.jsonobj.getString("feed")); 
	    params.putString("picture", MainActivity.jsonobj.getString("img"));
	    params.putString("properties", json2.toString());
	    
	    

	    WebDialog feedDialog = (
	        new WebDialog.FeedDialogBuilder(LoginUsingActivityActivity.this,
	            Session.getActiveSession(),
	            params))
	        .setOnCompleteListener(new OnCompleteListener() {

	            @Override
	            public void onComplete(Bundle values,
	                FacebookException error) {
	                if (error == null) {
	                    // When the story is posted, echo the success
	                    // and the post Id.
	                    final String postId = values.getString("post_id");
	                    if (postId != null) {
	                        Toast.makeText(LoginUsingActivityActivity.this,
	                            "Posted story, id: "+postId,
	                            Toast.LENGTH_SHORT).show();
	                        finish(); return;
	                    } else {
	                        // User clicked the Cancel button
	                        Toast.makeText(LoginUsingActivityActivity.this.getApplicationContext(), 
	                            "Publish cancelled", 
	                            Toast.LENGTH_SHORT).show();
	                        finish(); return;
	                    }
	                } else if (error instanceof FacebookOperationCanceledException) {
	                    // User clicked the "x" button
	                    Toast.makeText(LoginUsingActivityActivity.this.getApplicationContext(), 
	                        "Publish cancelled", 
	                        Toast.LENGTH_SHORT).show();
	                        finish(); return;
	                } else {
	                    // Generic, ex: network error
	                    Toast.makeText(LoginUsingActivityActivity.this.getApplicationContext(), 
	                        "Error posting story", 
	                        Toast.LENGTH_SHORT).show();
	                        finish(); return;
	                }
	            }		

	        })
	        .build();
	    feedDialog.show();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	private void publishFeedDialogforecast() {
	    Bundle params = new Bundle();

	    
	    JSONObject jsonloc;
	    
		try {
		
			jsonloc = MainActivity.jsonobj.getJSONObject("location");
			JSONObject jsoncon = MainActivity.jsonobj.getJSONObject("condition");
			JSONObject jsonunits = MainActivity.jsonobj.getJSONObject("units");
			
			JSONArray forecast = MainActivity.jsonobj.getJSONArray("forecast");
			JSONObject day1 = forecast.getJSONObject(0);
			JSONObject day2 = forecast.getJSONObject(1);
			JSONObject day3 = forecast.getJSONObject(2);
			JSONObject day4 = forecast.getJSONObject(3);
			JSONObject day5 = forecast.getJSONObject(4);
			
			JSONObject json1 = new JSONObject();
			JSONObject json2 = new JSONObject();
			json1.put("text", "here");
			json1.put("href", MainActivity.jsonobj.getString("link"));
			json2.put("Look at details", json1);
			
			
			
			String dayone=day1.getString("@day")+":"+day1.getString("@text")+","+day1.getString("@high")+"/"+day1.getString("@low")+"\u00b0"+jsonunits.getString("@temperature")+";";
			String daytwo=day2.getString("@day")+":"+day2.getString("@text")+","+day2.getString("@high")+"/"+day2.getString("@low")+"\u00b0"+jsonunits.getString("@temperature")+";" ;
			String daythree=day3.getString("@day")+":"+day3.getString("@text")+","+day3.getString("@high")+"/"+day3.getString("@low")+"\u00b0"+jsonunits.getString("@temperature")+";" ;
			String dayfour=day4.getString("@day")+":"+day4.getString("@text")+","+day4.getString("@high")+"/"+day4.getString("@low")+"\u00b0"+jsonunits.getString("@temperature")+";" ;
			String dayfive=day5.getString("@day")+":"+day5.getString("@text")+","+day5.getString("@high")+"/"+day5.getString("@low")+"\u00b0"+jsonunits.getString("@temperature")+";" ;
			
			


	    params.putString("name", jsonloc.getString("@city")+","+jsonloc.getString("@region")+","+jsonloc.getString("@country"));
	    params.putString("caption", "Weather Forecast for "+ jsonloc.getString("@city"));
	    params.putString("description", dayone+daytwo+daythree+dayfour+dayfive);
	    params.putString("link", MainActivity.jsonobj.getString("feed")); 
	    params.putString("picture","http://www-scf.usc.edu/~csci571/2013Fall/hw8/weather.jpg");
	    params.putString("properties", json2.toString());
	    
	    

	    WebDialog feedDialog = (
	        new WebDialog.FeedDialogBuilder(LoginUsingActivityActivity.this,
	            Session.getActiveSession(),
	            params))
	        .setOnCompleteListener(new OnCompleteListener() {

	            @Override
	            public void onComplete(Bundle values,
	                FacebookException error) {
	                if (error == null) {
	                    // When the story is posted, echo the success
	                    // and the post Id.
	                    final String postId = values.getString("post_id");
	                    if (postId != null) {
	                        Toast.makeText(LoginUsingActivityActivity.this,
	                            "Posted story, id: "+postId,
	                            Toast.LENGTH_SHORT).show();
	                        	finish(); return;
	                    } else {
	                        // User clicked the Cancel button
	                        Toast.makeText(LoginUsingActivityActivity.this.getApplicationContext(), 
	                            "Publish cancelled", 
	                            Toast.LENGTH_SHORT).show();
	                            //finish(); return;
	                    }
	                } else if (error instanceof FacebookOperationCanceledException) {
	                    // User clicked the "x" button
	                    Toast.makeText(LoginUsingActivityActivity.this.getApplicationContext(), 
	                        "Publish cancelled", 
	                        Toast.LENGTH_SHORT).show();
	                    		//finish(); return;
	                } else {
	                    // Generic, ex: network error
	                    Toast.makeText(LoginUsingActivityActivity.this.getApplicationContext(), 
	                        "Error posting story", 
	                        Toast.LENGTH_SHORT).show();
	                           // finish(); return;
	                }
	            }		

	        })
	        .build();
	    feedDialog.show();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
    

    private void updateView() 
    {
    	int weather=MainActivity.WEATHER;
    	//int forecast=MainActivity.WEATHER_FORECAST;
    	
        Session session = Session.getActiveSession();
        if (session.isOpened()) 
        {
            //textInstructionsOrLink.setText(URL_PREFIX_FRIENDS + session.getAccessToken());
        	if(weather==1)
        	{
        		weather=2;
        		publishFeedDialogCurrent();
        	}
        	
        	else
        	{
        		if(weather==2)
            	{
        			weather=1;
            	   publishFeedDialogforecast();
            	}
        	}
        	
        	
        	
            buttonLoginLogout.setText(R.string.logout);
            buttonLoginLogout.setOnClickListener(new OnClickListener() {
                public void onClick(View view) { onClickLogout(); }
            });
        } 
        else 
        {
            //textInstructionsOrLink.setText(R.string.instructions);
            buttonLoginLogout.setText(R.string.login);
            buttonLoginLogout.setOnClickListener(new OnClickListener() 
            {
                public void onClick(View view) 
                { 
                	onClickLogin(); 
                }
            });
        }
    }

    private void onClickLogin() {
        Session session = Session.getActiveSession();
        if (!session.isOpened() && !session.isClosed()) {
            session.openForRead(new Session.OpenRequest(this).setCallback(statusCallback));
        } else {
            Session.openActiveSession(this, true, statusCallback);
        }
    }

    private void onClickLogout() {
        Session session = Session.getActiveSession();
        if (!session.isClosed()) {
            session.closeAndClearTokenInformation();
        }
        
    }

    private class SessionStatusCallback implements Session.StatusCallback {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
            updateView();
        }
    }
}
