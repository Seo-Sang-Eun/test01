package codersit.co.kr.jejugo.activity.coupon;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import codersit.co.kr.jejugo.R;
import codersit.co.kr.jejugo.util.SaveDataManager;

/**
 * Created by BooHee on 2017-06-12.
 */

public class CouponDialog extends Dialog {

    String m_name;
    String m_date;
    String m_couponNum;

    @Bind(R.id.dialog_coupon_name)
    TextView dialogName;

    @Bind(R.id.dialog_coupon_date)
    TextView dialogDate;

    @Bind(R.id.dialog_coupon_num)
    TextView dialogCouponNum;

    @Bind(R.id.dialog_coupon_image)
    ImageView dialogImage;

    @Bind(R.id.dialog_coupon_useButton)
    Button dialogUseBtn;

    @Bind(R.id.dialog_coupon_cancelButton)
    Button dialogCancelBtn;

    int position;
    Context m_context;

    public CouponDialog(@NonNull Context context)
    {
        super(context);
    }

    public CouponDialog(@NonNull Context context, String m_name,String m_date, int position)
    {
        super(context);
        this.m_name = m_name;
        this.m_date = m_date;
        this.m_couponNum = String.valueOf(position) + "번 쿠폰";

        this.position = position;
        this.m_context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog_coupon);
        ButterKnife.bind(this);

        dialogName.setText(m_name);
        dialogDate.setText(m_date);
        dialogCouponNum.setText(m_couponNum);

        if(position >= 1 && position < 4)
            dialogImage.setImageDrawable(m_context.getResources().getDrawable(R.drawable.icon_book));
        else if(position >= 4 && position < 7)
            dialogImage.setImageDrawable(m_context.getResources().getDrawable(R.drawable.icon_book));
        else if(position >= 7 && position < 10)
            dialogImage.setImageDrawable(m_context.getResources().getDrawable(R.drawable.icon_book));



        dialogCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        dialogUseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveDataManager saveDataManager = new SaveDataManager(m_context.getApplicationContext());
                saveDataManager.putData("stampInfo"+position,"false");
                dismiss();
            }
        });

    }

}
