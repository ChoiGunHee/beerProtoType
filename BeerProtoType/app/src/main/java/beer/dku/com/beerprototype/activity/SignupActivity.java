package beer.dku.com.beerprototype.activity;

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
import cz.msebera.android.httpclient.Header;

public class SignupActivity extends AppCompatActivity {

    private EditText nameEdt;
    private EditText emailEdt;
    private EditText passEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        nameEdt = (EditText) findViewById(R.id.nameEdt);
        emailEdt = (EditText) findViewById(R.id.emailEdt);
        passEdt = (EditText) findViewById(R.id.passwordEdt);
    }

    public void sendSignUp(View view) {
        AsyncHttpClient client = new AsyncHttpClient();
        String name = nameEdt.getText().toString();
        String email = emailEdt.getText().toString();
        String password = passEdt.getText().toString();
        RequestParams params = new RequestParams();
        params.put("username", name);
        params.put("email", email);
        params.put("password", password);

        client.post("http://jejusien.herokuapp.com/users/regist", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String responsString = new String(responseBody);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                String responsString = new String(responseBody);
            }
        });
    }

    public void cancel(View view) {
        finish();
    }
}