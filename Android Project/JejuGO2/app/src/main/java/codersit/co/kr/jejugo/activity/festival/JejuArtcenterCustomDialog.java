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

public class JejuArtcenterCustomDialog extends Dialog {

    String m_title;
    String m_intro;
    String m_runtime;

    @Bind(R.id.artcenter_dialog_Title)
    TextView artcenter_dialog_Title;

    @Bind(R.id.artcenter_dialog_intro)
    TextView artcenter_dialog_intro;

    @Bind(R.id.artcenter_dialog_runtime)
    TextView artcenter_dialog_runtime;

    @Bind(R.id.artcenter_okButton)
    Button artcenter_okButton;


    public JejuArtcenterCustomDialog(@NonNull Context context) {
        super(context);
    }

    public JejuArtcenterCustomDialog(@NonNull Context context,String m_title,String m_intro,String m_runtime) {
        super(context);
        this.m_title = m_title;
        this.m_intro = m_intro;
        this.m_runtime = m_runtime;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog_artcenter);
        ButterKnife.bind(this);

        artcenter_dialog_Title.setText(m_title);
        artcenter_dialog_intro.setText(m_intro);
        artcenter_dialog_runtime.setText(m_runtime);


        artcenter_okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


    }
}
