package codersit.co.kr.jejugo.activity.coupon;

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
 * Created by BooHee on 2017-06-12.
 */

public class CouponDialog extends Dialog {

    private String name;
    private String startDate;
    private String endDate;
    private String couponNo;

    @Bind(R.id.coupon_name_view)
    TextView coupon_name_view;
    @Bind(R.id.startdate_view)
    TextView startdate_view;
    @Bind(R.id.enddate_view)
    TextView enddate_view;
    @Bind(R.id.coupon_no_view)
    TextView coupon_no_view;
    @Bind(R.id.exitbtn)
    Button exitbtn;

    public CouponDialog(@NonNull Context context) {
        super(context);
    }

    public CouponDialog(@NonNull Context context, String name, String startDate, String couponNo)
    {
        super(context);
        this.name = name;
        this.startDate = startDate;
        this.endDate = "쿠폰 발행일로부터 3일 이내";
        this.couponNo = couponNo;
    }

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog_coupon);

        ButterKnife.bind(this);

        coupon_name_view.setText(name);
        startdate_view.setText(startDate);
        enddate_view.setText(endDate);
        coupon_no_view.setText(couponNo);

        exitbtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                dismiss();
            }
        });

        //사용하기 버튼 기능 구현
    }

}
