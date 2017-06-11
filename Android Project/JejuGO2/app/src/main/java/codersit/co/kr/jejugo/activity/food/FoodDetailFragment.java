package codersit.co.kr.jejugo.activity.food;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import codersit.co.kr.jejugo.R;


/**
 * Created by BooHee on 2017-06-09.
 */

public class FoodDetailFragment extends Fragment {
    String mUrl;
    String mQueryAndDetail;

    public FoodDetailFragment() {
    }


    @Bind(R.id.wv_fragment_food_detail_webview)
    WebView wv_fragment_food_detail_webview;

    @Bind(R.id.tv_fragment_food_detail_querydetail)
    TextView tv_fragment_food_detail_querydetail;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_fragment_food_detail, container, false);
        ButterKnife.bind(this,view);

        // 번들받아올때 DATA는 STRING 리스트이고
        // 0 : 주소
        // 1 : 쿼리값

        mUrl = getArguments().getStringArrayList("DATA").get(0);
        mQueryAndDetail = getArguments().getStringArrayList("DATA").get(1);


        wv_fragment_food_detail_webview.getSettings().setJavaScriptEnabled(true);
        wv_fragment_food_detail_webview.loadUrl(mUrl);
        wv_fragment_food_detail_webview.setWebViewClient(new FoodDetailFragment.WebViewClientClass());

        tv_fragment_food_detail_querydetail.setText(mQueryAndDetail);

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
