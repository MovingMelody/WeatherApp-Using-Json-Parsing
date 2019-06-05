package com.example.htnamus7.weatherapp;
import android.support.constraint.ConstraintLayout;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeAct extends AppCompatActivity {
    MaterialButton findbtn;
    TextView degreec,degreeo,temp,condition;
    ImageView imageView;
    EditText citytext;
    public ConstraintLayout constraintLayout;
    public String city_data = "";
    String baseURL="http://api.openweathermap.org/data/2.5/weather?q=";
    String API="&appid=79e9565b6d45f0a48a3ff121a711792c";
    String myURL="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        constraintLayout = findViewById(R.id.weather);
        findbtn = findViewById(R.id.findbtn);
        imageView = findViewById(R.id.icon);
        citytext = findViewById(R.id.citytext);
        temp = findViewById(R.id.temp);
        condition = findViewById(R.id.condition);
        degreeo=findViewById(R.id.degreeo);


        findbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                city_data = citytext.getText().toString();
                if (city_data.isEmpty()) {
                    Toast.makeText(HomeAct.this,"WTF! Enter city :)",Toast.LENGTH_SHORT).show();
                } else {
                    myURL = baseURL + city_data + API;
                    Log.i("Url", "Url is" + myURL);
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, myURL, null, new Response.Listener <JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            String main, coord = null;
                            String lat = "", lon = "";
                            try {
                                String weather = response.getString("weather");
                                String mWeather = "", icon = "";

                                JSONArray array = new JSONArray(weather);
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject object = array.getJSONObject(i);
                                    mWeather = object.getString("main");
                                    condition.setText(mWeather);
                                    condition.setVisibility(View.VISIBLE);
                                    icon = "w" + object.getString("icon");
                                    Log.i("ICon", "ICOn is" + icon);
                                    imageView.setVisibility(View.VISIBLE);
                                    switch (icon) {
                                        case "w01d":
                                            imageView.setImageResource(R.drawable.w01d);
                                            break;
                                        case "w01n":
                                            imageView.setImageResource(R.drawable.w01n);
                                            break;
                                        case "w02d":
                                            imageView.setImageResource(R.drawable.w02d);
                                            break;
                                        case "w02n":
                                            imageView.setImageResource(R.drawable.w02n);
                                            break;
                                        case "w03d":
                                            imageView.setImageResource(R.drawable.w03d);
                                            break;
                                        case "w03n":
                                            imageView.setImageResource(R.drawable.w03n);
                                            break;
                                        case "w04d":
                                            imageView.setImageResource(R.drawable.w04d);
                                            break;
                                        case "w04n":
                                            imageView.setImageResource(R.drawable.w04n);
                                            break;
                                        case "w09d":
                                            imageView.setImageResource(R.drawable.w09d);
                                            break;
                                        case "w09n":
                                            imageView.setImageResource(R.drawable.w09n);
                                            break;
                                        case "w10d":
                                            imageView.setImageResource(R.drawable.w10d);
                                            break;
                                        case "w10n":
                                            imageView.setImageResource(R.drawable.w10n);
                                            break;
                                        case "w11d":
                                            imageView.setImageResource(R.drawable.w11d);
                                            break;
                                        case "w11n":
                                            imageView.setImageResource(R.drawable.w11n);
                                            break;
                                        case "w13d":
                                            imageView.setImageResource(R.drawable.w13d);
                                            break;
                                        case "w13n":
                                            imageView.setImageResource(R.drawable.w13n);
                                            break;
                                        case "w50d":
                                            imageView.setImageResource(R.drawable.w50d);
                                            break;
                                        case "w50n":
                                            imageView.setImageResource(R.drawable.w50n);
                                            break;
                                    }
                                }
                                main = response.getString("main");
                                Log.i("main", main);
                                JSONObject jsonObject = new JSONObject(main);
                                String temperature = "";
                                temperature = jsonObject.getString("temp");
                                float f = Float.parseFloat(temperature);
                                f = f - 273.5f;
                                double d = Math.ceil(Double.parseDouble(Float.toString(f)));
                                temp.setVisibility(View.VISIBLE);
                                Log.i("Temp", "Temp : " + temperature);
                                temp.setText(Double.toString(d));
                                degreeo.setVisibility(View.VISIBLE);
                            } catch (JSONException e) {
                                Toast.makeText(HomeAct.this,"Something",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(HomeAct.this,"Something Went Wrong:(",Toast.LENGTH_SHORT).show();
                        }
                    });
                    mRequest.getInstance(HomeAct.this).addToRequestQue(jsonObjectRequest);
                }
            }
        });
    }
}