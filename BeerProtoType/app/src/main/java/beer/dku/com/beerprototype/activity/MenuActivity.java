package beer.dku.com.beerprototype.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import beer.dku.com.beerprototype.R;
import cz.msebera.android.httpclient.Header;

public class MenuActivity extends AppCompatActivity {

    public final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    public final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 2;

    private EditText emailEdt;
    private EditText passwordEdt;
    private String jauth;
    private String name;
    private Toast failureToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        emailEdt = (EditText) findViewById(R.id.email_EditText);
        passwordEdt = (EditText) findViewById(R.id.pw_EditText);

        failureToast = Toast.makeText(this, "다시 한번 확인해주세요.", Toast.LENGTH_SHORT);

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {

            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_CONTACTS);

            } else {
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            }
        } else {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE :

                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Toast.makeText(this, "권한을 동의하셔야 이용가능합니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }
                return;
        }
    }

    public void login(View view) {
        AsyncHttpClient client = new AsyncHttpClient();

        String email = emailEdt.getText().toString();
        String password = passwordEdt.getText().toString();

        RequestParams params = new RequestParams();
        params.put("email", email);
        params.put("password", password);

        client.post("http://jejusien.herokuapp.com/users/login", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String responseString = new String(responseBody);
                Log.d("beer", responseString.toString());

                try {
                    JSONObject responseRoot = new JSONObject(responseString);
                    jauth = responseRoot.getString("jauth");
                    JSONObject infoJsonObject = responseRoot.getJSONObject("jinfo");
                    name = infoJsonObject.getString("username");
                    Toast.makeText(MenuActivity.this, "안녕하세요 " + name +"님", Toast.LENGTH_SHORT).show();
                    Intent loginIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(loginIntent);
                    MenuActivity.this.finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                failureToast.show();
            }
        });


    }

    public void signup(View view) {
        Intent signupIntent = new Intent(this, SignupActivity.class);
        startActivity(signupIntent);
    }
}
