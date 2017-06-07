package com.example.receiveweather;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;

public class ShortWeatherActivity extends AppCompatActivity {

    TextView textView_shortWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_short_weather);

        textView_shortWeather = (TextView)findViewById(R.id.shortWeather);

        ReceiveShortWeather  receiveShortWeather = new ReceiveShortWeather();
        receiveShortWeather.setiCallback(iCallback);
        receiveShortWeather.execute();
    }


    ICallback iCallback = new ICallback() {
        @Override
        public void call(Object o) {

            textView_shortWeather.setText(((ArrayList<ShortWeather>)o).get(0).getTmn());

        }
    };

}
