package codersit.co.kr.jejugo.activity.festival;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
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

public class SeogwipoCultureDialog extends Dialog {

    String LOG = "SeogwipoCultureDialog";

    String m_content;
    String m_title;

    @Bind(R.id.seo_dialog_Title)
    TextView dialog_Title;

    @Bind(R.id.seo_dialog_content)
    TextView dialog_content;

    @Bind(R.id.seo_okButton)
    Button okButton;

    public SeogwipoCultureDialog(@NonNull Context context)
    {
        super(context);
    }

    public SeogwipoCultureDialog(@NonNull Context context,  String m_content, String m_title)
    {
        super(context);
        this.m_content = m_content;
        this.m_title = m_title;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog_seogwipo);
        ButterKnife.bind(this);

        Log.i(LOG,m_title );
        Log.i(LOG,m_content);

        dialog_Title.setText(m_title);
        dialog_content.setText(m_content);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

}
