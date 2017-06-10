package codersit.co.kr.jejugo.activity.festival;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import codersit.co.kr.jejugo.R;

/**
 * Created by admin on 2017-06-10.
 */

public class JejuFestivalDialog extends Dialog {
    String m_title;
    String m_info;
    String m_host;

    @Bind(R.id.jejufest_dialog_Title)
    TextView dialog_title;

    @Bind(R.id.jejufest_dialog_info)
    TextView dialog_info;

    @Bind(R.id.jejufest_host)
    TextView dialog_host;

    @Bind(R.id.jejufest_okButton)
    Button okButton;

    public JejuFestivalDialog(@NonNull Context context)
    {
        super(context);
    }

    public JejuFestivalDialog(@NonNull Context context, String m_title, String m_info, String m_host )
    {
        super(context);
        this.m_title = m_title;
        this.m_info = m_info;
        this.m_host = m_host;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog_jejufest);
        ButterKnife.bind(this);

        dialog_title.setText(m_title);
        dialog_info.setText(m_info);
        dialog_host.setText(m_host);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

}
