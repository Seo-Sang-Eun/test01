package codersit.co.kr.jejugo.activity.festival;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import codersit.co.kr.jejugo.R;

/**
 * Created by admin on 2017-06-11.
 */

public class JejuArtcenterSearchFragment extends Fragment {

    String mUrl;
    String mQueryAndDetail;


    public JejuArtcenterSearchFragment() {
    }


    @Bind(R.id.artcenter_webview)
    WebView wv_artcenter;

    @Bind(R.id.tv_artcenter)
    TextView tv_artcenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.jeju_artcenter_fragment_webview, container, false);
        ButterKnife.bind(this,view);

        // 번들받아올때 DATA는 STRING 리스트이고
        // 0 : 주소
        // 1 : 쿼리값

        mUrl = getArguments().getStringArrayList("DATA").get(0);
        mQueryAndDetail = getArguments().getStringArrayList("DATA").get(1);


        wv_artcenter.getSettings().setJavaScriptEnabled(true);
        wv_artcenter.loadUrl(mUrl);
        wv_artcenter.setWebViewClient(new WebViewClientClass());

        tv_artcenter.setText(mQueryAndDetail);

        return view;

    }
    private class WebViewClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }



}