package com.example.receiveweather;

import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by P200 on 2017-06-07.
 */

public class ReceiveShortWeather extends AsyncTask<URL, Integer, Object> {


    protected Object doInBackground(URL... urls) {

        ArrayList<ShortWeather> shortWeathers = new ArrayList<ShortWeather>();

        String url = "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=5011031000";

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = null;

        try {
            response = client.newCall(request).execute();
            parseXML(response.body().string(),shortWeathers);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return shortWeathers;
    }

    protected void onPostExecute(Object result) {
        iCallback.call(result);

        Log.i("ASDASD",((ArrayList<ShortWeather>)result).get(0).getTemp());
        Log.i("ASDASD",((ArrayList<ShortWeather>)result).get(0).getTmx());
        Log.i("ASDASD",((ArrayList<ShortWeather>)result).get(0).getTmn());

    }

    ICallback iCallback;

    public void setiCallback(ICallback iCallback) {
        this.iCallback = iCallback;
    }


    void parseXML(String xml, ArrayList<ShortWeather> shortWeathers) {
        try {
            String tagName = "";
            boolean onHour = false;
            boolean onDay = false;
            boolean onTem = false;
            boolean onWfKor = false;
            boolean onPop = false;
            boolean onEnd = false;
            boolean isItemTag1 = false;
            int i = 0;

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();

            parser.setInput(new StringReader(xml));

            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    tagName = parser.getName();
                    if (tagName.equals("data")) {
                        shortWeathers.add(new ShortWeather());
                        onEnd = false;
                        isItemTag1 = true;
                    }
                } else if (eventType == XmlPullParser.TEXT && isItemTag1) {
                    if (tagName.equals("hour") && !onHour) {
                        shortWeathers.get(i).setHour(parser.getText());
                        onHour = true;
                    }
                    if (tagName.equals("day") && !onDay) {
                        shortWeathers.get(i).setDay(parser.getText());
                        onDay = true;
                    }
                    if (tagName.equals("temp") && !onTem) {
                        shortWeathers.get(i).setTemp(parser.getText());
                        onTem = true;
                    }
                    if (tagName.equals("wfKor") && !onWfKor) {
                        shortWeathers.get(i).setWfKor(parser.getText());
                        onWfKor = true;
                    }
                    if (tagName.equals("pop") && !onPop) {
                        shortWeathers.get(i).setPop(parser.getText());
                        onPop = true;
                    }
                } else if (eventType == XmlPullParser.END_TAG) {
                    if (tagName.equals("s06") && onEnd == false) {
                        i++;
                        onHour = false;
                        onDay = false;
                        onTem = false;
                        onWfKor = false;
                        onPop = false;
                        isItemTag1 = false;
                        onEnd = true;
                    }
                }

                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}