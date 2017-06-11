package codersit.co.kr.jejugo.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import codersit.co.kr.jejugo.R;
import codersit.co.kr.jejugo.util.SaveDataManager;

import static android.support.v7.appcompat.R.styleable.AlertDialog;

public class JoinActivity extends AppCompatActivity {


    @Bind(R.id.tv_join_id_check)
    TextView tv_join_id_check;  //ID 중복 체크 버튼

    @Bind(R.id.et_join_id)
    EditText et_join_id; //회원가입 id

    @Bind(R.id.et_join_pw)
    EditText et_join_pw; //회원가입 pw

    @Bind(R.id.et_join_pw_check)
    EditText et_join_pw_check; //회원가입 pw check

    SaveDataManager saveDataManager;
    private Activity activity = this;

    String usableId = "";   //사용할 ID 저장
    boolean idOverlapCheck = false;  //id 중복체크 했는지 확인

    private AlertDialog mDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        ButterKnife.bind(this);
        saveDataManager = new SaveDataManager(this.getApplicationContext());

    }

    //회원가입
    @OnClick(R.id.tv_join_join)
    void onClick_tv_login_login()
    {
        String pw = et_join_pw.getText().toString();
        String pw_ck = et_join_pw_check.getText().toString();
        String id = et_join_id.getText().toString();

        if(!usableId.equals(id) || idOverlapCheck == false) {   //아이디 중복 체크 안했음.
            showDialogIdOverlapCheck();
        }
        else {//아이디 중복 체크 했는지 확인
            if(pw.equals("") || pw == null) {   //패스워드 입력 확인
                showDialogPwEmptyCheck();
            }
            else {
                //Toast.makeText(activity, pw + " / " + pw_ck , Toast.LENGTH_SHORT).show();

                if(!pw.equals(pw_ck)) { //패스워드와 패스워드 체크 edit의 값이 다를 경우
                    showDialogPwCheck();
                }
                else {
                    //패스워드 정규식 패턴 검증
                    if(pw.length() > 7 && pw.length() < 17) {   //pw의 길이 체크, 영문과 숫자만 사용했는지 check
                        //패스워드 정규 패턴에 맞음.
                        saveDataManager.putData("id_"+id, pw);
                        Toast.makeText(activity, "회원가입 성공! "+id+"님, 로그인을 해주세요." , Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                        finish();
                    }
                    else {  //패스워드 패턴에 맞지 않음.
                        showDialogImpossiblePwCheck();
                    }
                }
            }
        }

    }

    //아이디 체크
    @OnClick(R.id.tv_join_id_check)
    void onClick_tv_join_id_check()
    {
        String id = et_join_id.getText().toString();

        if(id.equals("") || id == null) {   //아이디 입력 안했을 때
            showDialogIdEmptyCheck();
        }
        else {  //아이디 입력 했음
            if(id.length()>4 && id.length() < 21 ) { //아이디 정규 패턴 검증
                if(saveDataManager.getData("id_"+id)==null) {   //db에 사용하고 있는 id없음.
                    mDialog = showDialogIdCheck();
                    mDialog.show();
                }
                else {
                    showDialogImpossibleIdCheck();
                }
            }
            else {
                //Toast.makeText(activity, id + " / " + id.length() + " / " + Pattern.matches("^[a-z0-9]*$", id)  , Toast.LENGTH_SHORT).show();
                showDialogImpossiblePtIdCheck();
            }
        }
    }

    private AlertDialog showDialogIdCheck() {
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setTitle("아이디 중복체크");
        ab.setMessage("사용가능한 아이디입니다. 이 아이디를 사용하시겠습니까?");

        ab.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                setDismiss(mDialog, true);
            }
        });

        ab.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                setDismiss(mDialog, false);
            }
        });

        return ab.create();
    }

    /**
     * 다이얼로그 종료
     * @param dialog
     */
    private void setDismiss(Dialog dialog, boolean check){
        String id = et_join_id.getText().toString();
        if(check) {     //입력한 아이디 사용한다고 했음.
            idOverlapCheck = true;
            usableId = id;
        }
        else {  //입력한 아이디 사용하지 않음.
            idOverlapCheck = false;
            et_join_id.setText("");
        }

        if(dialog != null && dialog.isShowing())
            dialog.dismiss();
    }

    void showDialogPwCheck() {
        // 다이얼로그 바디
        AlertDialog.Builder alert_confirm = new AlertDialog.Builder(this);
        // 메세지
        alert_confirm.setTitle("패스워드 중복체크");
        alert_confirm.setMessage("패스워드가 다릅니다.");
        // 확인 버튼 리스너
        alert_confirm.setPositiveButton("확인", null);
        // 다이얼로그 생성
        AlertDialog alert = alert_confirm.create();

        // 다이얼로그 보기
        alert.show();
    }

    void showDialogImpossiblePwCheck() {
        // 다이얼로그 바디
        AlertDialog.Builder alert_confirm = new AlertDialog.Builder(this);
        // 메세지
        alert_confirm.setTitle("패스워드 사용불가");
        alert_confirm.setMessage("8~16자의 비밀번호를 사용하세요.");
        // 확인 버튼 리스너
        alert_confirm.setPositiveButton("확인", null);
        // 다이얼로그 생성
        AlertDialog alert = alert_confirm.create();

        // 다이얼로그 보기
        alert.show();
    }

    void showDialogSuccessJoin() {
        // 다이얼로그 바디
        AlertDialog.Builder alert_confirm = new AlertDialog.Builder(this);
        // 메세지
        alert_confirm.setTitle("회원가입 완료");
        alert_confirm.setMessage("회원가입에 성공하였습니다. 로그인해주세요.");
        // 확인 버튼 리스너
        alert_confirm.setPositiveButton("확인", null);
        // 다이얼로그 생성
        AlertDialog alert = alert_confirm.create();

        // 다이얼로그 보기
        alert.show();
    }

    void showDialogImpossiblePtIdCheck() {
        // 다이얼로그 바디
        AlertDialog.Builder alert_confirm = new AlertDialog.Builder(this);
        // 메세지
        alert_confirm.setTitle("아이디 사용불가");
        alert_confirm.setMessage("5~20자의 영문 소문자, 숫자만 사용 가능합니다.");
        // 확인 버튼 리스너
        alert_confirm.setPositiveButton("확인", null);
        // 다이얼로그 생성
        AlertDialog alert = alert_confirm.create();

        // 다이얼로그 보기
        alert.show();
    }

    void showDialogImpossibleIdCheck() {
        // 다이얼로그 바디
        AlertDialog.Builder alert_confirm = new AlertDialog.Builder(this);
        // 메세지
        alert_confirm.setTitle("아이디 사용불가");
        alert_confirm.setMessage("이미 사용중이거나 탈퇴한 아이디입니다.");
        // 확인 버튼 리스너
        alert_confirm.setPositiveButton("확인", null);
        // 다이얼로그 생성
        AlertDialog alert = alert_confirm.create();

        // 다이얼로그 보기
        alert.show();
    }

    void showDialogPwEmptyCheck() {
        // 다이얼로그 바디
        AlertDialog.Builder alert_confirm = new AlertDialog.Builder(this);
        // 메세지
        alert_confirm.setTitle("패스워드 입력");
        alert_confirm.setMessage("패스워드를 입력해주세요.");
        // 확인 버튼 리스너
        alert_confirm.setPositiveButton("확인", null);
        // 다이얼로그 생성
        AlertDialog alert = alert_confirm.create();

        // 다이얼로그 보기
        alert.show();
    }

    void showDialogIdEmptyCheck() {
        // 다이얼로그 바디
        AlertDialog.Builder alert_confirm = new AlertDialog.Builder(this);
        // 메세지
        alert_confirm.setTitle("아이디 입력");
        alert_confirm.setMessage("아이디를 입력하세요.");
        // 확인 버튼 리스너
        alert_confirm.setPositiveButton("확인", null);
        // 다이얼로그 생성
        AlertDialog alert = alert_confirm.create();

        // 다이얼로그 보기
        alert.show();
    }

    void showDialogIdOverlapCheck() {
        // 다이얼로그 바디
        AlertDialog.Builder alert_confirm = new AlertDialog.Builder(this);
        // 메세지
        alert_confirm.setTitle("아이디 중복체크");
        alert_confirm.setMessage("아이디를 중복체크를 해주세요.");
        // 확인 버튼 리스너
        alert_confirm.setPositiveButton("확인", null);
        // 다이얼로그 생성
        AlertDialog alert = alert_confirm.create();

        // 다이얼로그 보기
        alert.show();
    }

}
