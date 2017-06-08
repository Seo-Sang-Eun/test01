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

public class LongWeatherActivity extends AppCompatActivity {

    TextView textView_longWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_weather);

        textView_longWeather = (TextView)findViewById(R.id.longWeather);

        ReceiveLongWeather receiveLongWeather = new ReceiveLongWeather();
        receiveLongWeather.setiCallback(iCallback);
        receiveLongWeather.execute();
    }

    ICallback iCallback = new ICallback() {
        @Override
        public void call(Object o) {

        }
    };


}
