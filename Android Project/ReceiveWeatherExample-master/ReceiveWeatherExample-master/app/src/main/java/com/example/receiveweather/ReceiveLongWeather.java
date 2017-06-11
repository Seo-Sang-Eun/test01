package com.example.receiveweather;

import android.os.AsyncTask;

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
public class ReceiveLongWeather extends AsyncTask<URL, Integer, Object> {


    protected Object doInBackground(URL... urls) {

        ArrayList<LongWeather> longWeathers = new ArrayList<LongWeather>();

        String url = "http://web.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=184";

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = null;

        try {
            response = client.newCall(request).execute();
            parseWeekXML(response.body().string(), longWeathers);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return longWeathers;
    }

    protected void onPostExecute(Object result) {
        iCallback.call(result);

    }

    ICallback iCallback;

    public void setiCallback(ICallback iCallback) {
        this.iCallback = iCallback;
    }



    void parseWeekXML(String xml, ArrayList<LongWeather> longWeathers) {
        try {
            String tagName = "";
            boolean onCity = false;
            boolean onTmEf = false;
            boolean onWf = false;
            boolean onTmn = false;
            boolean onTmx = false;
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

                    if (tagName.equals("city")) {
                        eventType = parser.next();

                        if (parser.getText().equals("제주")) {    // 파싱하고 싶은 지역 이름을 쓴다
                            onCity = true;
                        } else {
                            if (onCity) { // 이미 parsing을 끝냈을 경우
                                break;
                            } else {        // 아직 parsing을 못했을 경우
                                onCity = false;
                            }
                        }
                    }

                    if (tagName.equals("data") && onCity) {
                        longWeathers.add(new LongWeather());
                        onEnd = false;
                        isItemTag1 = true;
                    }
                } else if (eventType == XmlPullParser.TEXT && isItemTag1 && onCity) {
                    if (tagName.equals("tmEf") && !onTmEf) {
                        longWeathers.get(i).setTmEf(parser.getText());
                        onTmEf = true;
                    }
                    if (tagName.equals("wf") && !onWf) {
                        longWeathers.get(i).setWf(parser.getText());
                        onWf = true;
                    }
                    if (tagName.equals("tmn") && !onTmn) {
                        longWeathers.get(i).setTmn(parser.getText());
                        onTmn = true;
                    }
                    if (tagName.equals("tmx") && !onTmx) {
                        longWeathers.get(i).setTmx(parser.getText());
                        onTmx = true;
                    }
                } else if (eventType == XmlPullParser.END_TAG) {
                    if (tagName.equals("reliability") && onEnd == false) {
                        i++;
                        onTmEf = false;
                        onWf = false;
                        onTmn = false;
                        onTmx = false;
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