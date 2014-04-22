package com.example.weatherapp;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	//static String abc;
	
	public static JSONObject jsonobj = new JSONObject();
	public static int WEATHER;
	
	
	private static final String DEGREE = "\u00b0";
	private static final String CITY = "city";
	private static final String ZIPCODE = "zipcode";
	private static final String C = "c";
	private static final String F = "f";
	String temp1 = F;
	String type = ZIPCODE;
	
	private TableLayout Table;
	TextView trow11, trow12, trow13, trow14, trow21, trow22, trow23, trow24,
			trow31, trow32, trow33, trow34, trow41, trow42, trow43, trow44,
			trow51, trow52, trow53, trow54, tcity, tregion, tcondition,
			tTemperature, tforecast, tcurrentweather,tweatherforecast;
	ImageView imgview;

	// String
	// Url="http://coloradoroofingspecialists.co/wp-content/uploads/2011/06/blue-sky-1600.jpg";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Table = (TableLayout) findViewById(R.id.TableLayout01);
		Table.setVisibility(View.INVISIBLE);
		tforecast = (TextView) findViewById(R.id.forecast);
		tforecast.setVisibility(View.INVISIBLE);
		tcurrentweather= (TextView) findViewById(R.id.currentweather);
		tcurrentweather.setVisibility(View.INVISIBLE);
		tweatherforecast=(TextView) findViewById(R.id.weatherforecast);
		tweatherforecast.setVisibility(View.INVISIBLE);
		/*
		 * StrictMode.ThreadPolicy policy = new
		 * StrictMode.ThreadPolicy.Builder().permitAll().build();
		 * StrictMode.setThreadPolicy(policy); Bitmap myImage =
		 * getBitmapFromURL(
		 * "http://coloradoroofingspecialists.co/wp-content/uploads/2011/06/blue-sky-1600.jpg"
		 * ); LinearLayout
		 * rLayout=(LinearLayout)findViewById(R.id.linearlayout);
		 * 
		 * //BitmapDrawable(obj) convert Bitmap object into drawable object.
		 * Drawable dr = new BitmapDrawable(myImage);
		 * rLayout.setBackgroundDrawable(dr);
		 */

	}
	
	// ON CLICK OF POST CURRENT WEATHER
	public void currentweather(View v)
	{
		
		AlertDialog.Builder dlgAlert2 = new AlertDialog.Builder(this);
		dlgAlert2.setMessage("Post to Facebook");
		
		dlgAlert2.setPositiveButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		
		dlgAlert2.setNegativeButton("Post Current Weather",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						WEATHER=1;
						Currentweatherpost();
					}
				});
		
		dlgAlert2.create().show();
		
		
	   
	}
	
	public void Currentweatherpost()
	{
		   Intent intent = new Intent(MainActivity.this, LoginUsingActivityActivity.class);
	       startActivity(intent);
	}
	
	
	
	// ON CLICK OF POST WEATHER FORECAST
		public void weatherforecast(View v)
		{
			
			AlertDialog.Builder dlgAlert2 = new AlertDialog.Builder(this);
			dlgAlert2.setMessage("Post to Facebook");
			
			dlgAlert2.setPositiveButton("Cancel",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});
			
			dlgAlert2.setNegativeButton("Post Weather Forecast",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							WEATHER=2;
							weatherforecast();
						}
					});
			
			dlgAlert2.create().show();
			
			
		   
		}
		
		public void weatherforecast()
		{
			   Intent intent = new Intent(MainActivity.this, LoginUsingActivityActivity.class);
		       startActivity(intent);
		}

	
	
	
	
	

	public void cleardata() {
		// TODO Auto-generated method stub
		try {
			if (tcity != null)
				tcity.setVisibility(View.INVISIBLE);
			if (tregion != null)
				tregion.setVisibility(View.INVISIBLE);
			if (imgview != null)
				imgview.setVisibility(View.INVISIBLE);
			if (tcondition != null)
				tcondition.setVisibility(View.INVISIBLE);
			if (tTemperature != null)
				tTemperature.setVisibility(View.INVISIBLE);
			if (Table != null)
				Table.setVisibility(View.INVISIBLE);

			
			tforecast.setVisibility(View.INVISIBLE);
			
			tcurrentweather.setVisibility(View.INVISIBLE);
			tweatherforecast.setVisibility(View.INVISIBLE);
			// System.out.println("INVISIBILITY SET");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			// System.out.println(e.getMessage());
		}

	}

	public Bitmap getBitmapFromURL(String imageUrl) {
		try {
			URL url = new URL(imageUrl);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream input = connection.getInputStream();
			Bitmap myBitmap = BitmapFactory.decodeStream(input);
			return myBitmap;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void radioone(View view) {
		temp1 = F;
	}

	public void radiotwo(View view) {
		temp1 = C;
	}

	public void Jsonvalue(String retVal) throws JSONException {
		/*
		 * AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
		 * dlgAlert.setMessage(retVal); dlgAlert.setPositiveButton("Ok", new
		 * DialogInterface.OnClickListener() { public void
		 * onClick(DialogInterface dialog, int which) { dialog.dismiss(); } });
		 * dlgAlert.create().show();
		 */
		
		
		//abc=retVal;
		
		
		String city, region, country, image, condition, temp, temperature;
		
		if (retVal != null) 
		{
			JSONObject json = new JSONObject(retVal);
			
			jsonobj=json;
			
			JSONObject jsonloc = json.getJSONObject("location");
			city = jsonloc.getString("@city");
//			AlertDialog.Builder dlgAlert1 = new AlertDialog.Builder(this);
//			dlgAlert1.setMessage(city);
//			dlgAlert1.setPositiveButton("Ok",
//					new DialogInterface.OnClickListener() {
//						public void onClick(DialogInterface dialog, int which) {
//							dialog.dismiss();
//						}
//					});
//			dlgAlert1.create().show();

			// City

			tcity = (TextView) findViewById(R.id.city);
			tcity.setText(city);
			tcity.setVisibility(View.VISIBLE);
			tcity.setTextSize(30);

			// Country,Region
			country = jsonloc.getString("@country");
			region = jsonloc.getString("@region");
			tregion = (TextView) findViewById(R.id.region);
			
			if (region.equals(""))
			{
				tregion.setText(country);
			}
			else 
			{
				tregion.setText(region + ", " + country);
			}
			
			tregion.setVisibility(View.VISIBLE);

			// Image
			image = json.getString("img");
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
			Bitmap myImage = getBitmapFromURL(image);
			imgview = (ImageView) findViewById(R.id.imageid);
			Drawable dr = new BitmapDrawable(myImage);
			imgview.setImageDrawable(dr);
			imgview.setVisibility(View.VISIBLE);

			// Condition
			JSONObject jsoncon = json.getJSONObject("condition");
			condition = jsoncon.getString("@text");
			tcondition = (TextView) findViewById(R.id.condition);
			tcondition.setText(condition);
			tcondition.setVisibility(View.VISIBLE);

			// Temperature
			temp = jsoncon.getString("@temp");
			JSONObject jsonunits = json.getJSONObject("units");
			temperature = jsonunits.getString("@temperature");
			tTemperature = (TextView) findViewById(R.id.temperature);
			tTemperature.setText(temp + DEGREE + temperature);
			tTemperature.setVisibility(View.VISIBLE);

			// table

			// String abc;
			tforecast.setVisibility(View.VISIBLE);
			Table.setVisibility(View.VISIBLE);

			/*
			 * TextView trow11, trow12,trow13,trow14,trow21,
			 * trow22,trow23,trow24,trow31,
			 * trow32,trow33,trow34,trow41,trow42,trow43
			 * ,trow44,trow51,trow52,trow53,trow54;
			 */
			JSONArray forecast = json.getJSONArray("forecast");
			// Table row1
			JSONObject day1 = forecast.getJSONObject(0);
			trow11 = (TextView) findViewById(R.id.Dayr1);
			trow11.setText(day1.getString("@day"));
			trow12 = (TextView) findViewById(R.id.Weatherr1);
			trow12.setText(day1.getString("@text"));
			trow13 = (TextView) findViewById(R.id.Highr1);
			trow13.setText(day1.getString("@high") + DEGREE + temperature);
			trow14 = (TextView) findViewById(R.id.Lowr1);
			trow14.setText(day1.getString("@low") + DEGREE + temperature);

			// abc=c.getString("@day");
			// Table row2

			JSONObject day2 = forecast.getJSONObject(1);
			trow21 = (TextView) findViewById(R.id.Dayr2);
			trow21.setText(day2.getString("@day"));
			trow22 = (TextView) findViewById(R.id.Weatherr2);
			trow22.setText(day2.getString("@text"));
			trow23 = (TextView) findViewById(R.id.Highr2);
			trow23.setText(day2.getString("@high") + DEGREE + temperature);
			trow24 = (TextView) findViewById(R.id.Lowr2);
			trow24.setText(day2.getString("@low") + DEGREE + temperature);

			// Table row3

			JSONObject day3 = forecast.getJSONObject(2);
			trow31 = (TextView) findViewById(R.id.Dayr3);
			trow31.setText(day3.getString("@day"));
			trow32 = (TextView) findViewById(R.id.Weatherr3);
			trow32.setText(day3.getString("@text"));
			trow33 = (TextView) findViewById(R.id.Highr3);
			trow33.setText(day3.getString("@high") + DEGREE + temperature);
			trow34 = (TextView) findViewById(R.id.Lowr3);
			trow34.setText(day3.getString("@low") + DEGREE + temperature);

			// Table row4

			JSONObject day4 = forecast.getJSONObject(3);
			trow41 = (TextView) findViewById(R.id.Dayr4);
			trow41.setText(day4.getString("@day"));
			trow42 = (TextView) findViewById(R.id.Weatherr4);
			trow42.setText(day4.getString("@text"));
			trow43 = (TextView) findViewById(R.id.Highr4);
			trow43.setText(day4.getString("@high") + DEGREE + temperature);
			trow44 = (TextView) findViewById(R.id.Lowr4);
			trow44.setText(day4.getString("@low") + DEGREE + temperature);

			// Table row5

			JSONObject day5 = forecast.getJSONObject(4);
			trow51 = (TextView) findViewById(R.id.Dayr5);
			trow51.setText(day5.getString("@day"));
			trow52 = (TextView) findViewById(R.id.Weatherr5);
			trow52.setText(day5.getString("@text"));
			trow53 = (TextView) findViewById(R.id.Highr5);
			trow53.setText(day5.getString("@high") + DEGREE + temperature);
			trow54 = (TextView) findViewById(R.id.Lowr5);
			trow54.setText(day5.getString("@low") + DEGREE + temperature);
			
			
			tcurrentweather.setVisibility(View.VISIBLE);
			tweatherforecast.setVisibility(View.VISIBLE);
			

		}

		else {
			tcity = (TextView) findViewById(R.id.city);
			tcity.setText("Weather information not found");
			tcity.setVisibility(View.VISIBLE);
			tcity.setTextSize(20);
		}

	}

	public boolean isNumeric(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (NumberFormatException e) {
			// s is not numeric
			return false;
		}
	}

	/** Called when the user clicks the Send button */
	public void sendMessage(View view) {

		EditText editText = (EditText) findViewById(R.id.location);

		cleardata();

		String location = editText.getText().toString();
		// Do something in response to button
		if (location.isEmpty()) {
			AlertDialog.Builder dlgAlert1 = new AlertDialog.Builder(this);
			dlgAlert1.setMessage("Please a valid location value");
			dlgAlert1.setPositiveButton("Ok",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});
			dlgAlert1.create().show();
			
			
			
//			Toast toast = Toast.makeText(this, "Please a valid location value",
//					Toast.LENGTH_LONG);
//			toast.setGravity(Gravity.CENTER, 0, 0);
//			toast.show();
			/*
			 * AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
			 * dlgAlert.setMessage("Entered the result of send button");
			 * dlgAlert.setPositiveButton("Ok", new
			 * DialogInterface.OnClickListener() { public void
			 * onClick(DialogInterface dialog, int which) { dialog.dismiss(); }
			 * }); dlgAlert.create().show();
			 */
		}

		else {
			AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
			dlgAlert.setPositiveButton("Ok",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});

			if (isNumeric(location)) {
				if (location.length() != 5) {
					dlgAlert.setMessage("Invalid zipcode: must be five digits, Example: 90089");
					dlgAlert.create().show();
					return;
				} else {
					type = ZIPCODE;
				}
			}

			else if (!(location.contains(","))) {
				dlgAlert.setMessage("Invalid location: must include state or country separated by comma\nExample: Los Angeles,CA");
				dlgAlert.create().show();
				return;
			}

			// if (num.length!=5)
			else {
				type = CITY;

				String loc = location.replaceAll("\\s","");
				String loc2 = loc.replaceAll("\\.","");       //location.replace(" ", ""); location = loc;
				
				location = loc2;
				

			}

			String para = "location=" + location + "&type=" + type
					+ "&tempUnit=" + temp1;
			// String
			// url="http://default-environment-43h8audakr.elasticbeanstalk.com/?location="+location+"&type="+type+"&tempUnit="+tempUnit;
			String url = "http://" + "cs-server.usc.edu:26027"
					+ "/examples/servlet/weathersearch?" + para;
			AsyncClass abc = new AsyncClass(this);
			abc.execute(url);

		}

	}

}
