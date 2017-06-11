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

public class JejuCultureStreetCustomDialog extends Dialog {

    String m_introduce;
    String m_telephone;
    String m_ceo;
    String m_title;

    @Bind(R.id.jeju_cul_street_dialog_Title)
    TextView dialog_Title;

    @Bind(R.id.jeju_cul_street_introduce)
    TextView dialog_intro;

    @Bind(R.id.jeju_cul_street_ceo)
    TextView dialog_ceo;

    @Bind(R.id.jeju_cul_street_telephone)
    TextView dialog_tel;

    @Bind(R.id.jeju_cul_okButton)
    Button okButton;

    public JejuCultureStreetCustomDialog(@NonNull Context context)
    {
        super(context);
    }

    public JejuCultureStreetCustomDialog(@NonNull Context context, String m_title, String m_introduce, String m_telephone, String m_ceo )
    {
        super(context);
        this.m_title = m_title;
        this.m_introduce = m_introduce;
        this.m_ceo = m_ceo;
        this.m_telephone = m_telephone;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog_jejuculture_street);
        ButterKnife.bind(this);

        dialog_Title.setText(m_title);
        dialog_intro.setText(m_introduce);
        dialog_ceo.setText(m_ceo);
        dialog_tel.setText(m_telephone);


        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

}
