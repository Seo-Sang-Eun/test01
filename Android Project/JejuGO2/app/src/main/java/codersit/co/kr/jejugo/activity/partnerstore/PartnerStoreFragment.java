package codersit.co.kr.jejugo.activity.partnerstore;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import codersit.co.kr.jejugo.R;
import codersit.co.kr.jejugo.activity.MainActivity;
import codersit.co.kr.jejugo.util.PartnerStoreManager;


/**
 * Created by BooHee on 2017-06-10.
 */

public class PartnerStoreFragment extends Fragment{
    @Bind(R.id.partnerstore_view)
    ListView partnerstore_view;


    @Bind(R.id.partner_search_text)
    EditText partner_search_text;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_fragment_partnerstore, container, false);
        ButterKnife.bind(this,view);
        PartnerStoreManager.InitData();
        final PartnerStoreAdapter storeAdapter = new PartnerStoreAdapter((MainActivity)getActivity(), PartnerStoreManager.dtoStoreList);

        partner_search_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String text = partner_search_text.getText().toString().toLowerCase(Locale.getDefault());
                storeAdapter.filter(text);
            }
        });


        Log.i("2222","222");

        partnerstore_view.setAdapter(storeAdapter);
        partnerstore_view.setTextFilterEnabled(true);

        return view;
    }
}
