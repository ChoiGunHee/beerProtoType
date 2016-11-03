package beer.dku.com.beerprototype.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import beer.dku.com.beerprototype.R;
import beer.dku.com.beerprototype.asynctasks.SighUpTask;
import beer.dku.com.beerprototype.servercommunication.ProxyObject;
import cz.msebera.android.httpclient.Header;

public class SignupActivity extends AppCompatActivity {

    private EditText nameEdt;
    private EditText emailEdt;
    private EditText passEdt;
    private ProgressDialog mDlg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        nameEdt = (EditText) findViewById(R.id.nameEdt);
        emailEdt = (EditText) findViewById(R.id.emailEdt);
        passEdt = (EditText) findViewById(R.id.passwordEdt);
    }

    public void sendSignUp(View view) {
        String name = nameEdt.getText().toString();
        String email = emailEdt.getText().toString();
        String password = passEdt.getText().toString();

        mDlg = new ProgressDialog(this);
        mDlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDlg.setMessage("로그인 중 입니다.");
        mDlg.show();

        SighUpTask task = new SighUpTask(handler, name, email, password);
        task.start();
    }

    public void cancel(View view) {
        finish();
    }

    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            boolean result = bundle.getBoolean("result");
            mDlg.dismiss();
            if (result) {
                Toast.makeText(SignupActivity.this, "로그인 해주세요.", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(SignupActivity.this, "죄송합니다. 입력부분을 확인해주세요.", Toast.LENGTH_SHORT).show();
            }
        }
    };
}