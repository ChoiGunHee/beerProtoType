package beer.dku.com.beerprototype.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import beer.dku.com.beerprototype.asynctasks.BeerDataRequestTask;
import beer.dku.com.beerprototype.asynctasks.LoginTask;
import beer.dku.com.beerprototype.dao.BeerInfo;
import beer.dku.com.beerprototype.database.DatabaseSource;
import beer.dku.com.beerprototype.servercommunication.ProxyObject;

import beer.dku.com.beerprototype.R;

public class MenuActivity extends AppCompatActivity {

    public final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    public final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 2;

    private EditText emailEdt;
    private EditText passwordEdt;
    private ProgressDialog mDlg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        /*
        testImg = (ImageView) findViewById(R.id.testImg);
        int id = getResources().getIdentifier("beer.dku.com.beerprototype:drawable/" + "_7brauindiapaleale", null, null);
        testImg.setImageDrawable(getResources().getDrawable(id, null));
        */
        emailEdt = (EditText) findViewById(R.id.email_EditText);
        passwordEdt = (EditText) findViewById(R.id.pw_EditText);

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {

            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_CONTACTS);

            } else {
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            }
        } else {

        }

        initData();
    }

    private void initData() {
        BeerDataRequestTask task = new BeerDataRequestTask(handler);
        task.start();
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
        String email = emailEdt.getText().toString();
        String password = passwordEdt.getText().toString();

        mDlg = new ProgressDialog(this);
        mDlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDlg.setMessage("로그인 중 입니다.");
        mDlg.show();

        LoginTask loginTask  = new LoginTask(handler, email, password);
        loginTask.start();
    }

    public void signup(View view) {
        Intent signupIntent = new Intent(this, SignupActivity.class);
        startActivity(signupIntent);
    }

    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle;

            switch (msg.what) {
                case LoginTask.LOGIN_FLAG :
                    bundle = msg.getData();
                    boolean result = bundle.getBoolean("result");
                    mDlg.dismiss();
                    if (result) {
                        Toast.makeText(MenuActivity.this, "안녕하세요 " + ProxyObject.getInstance().getUserName() + "님", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(MenuActivity.this, "다시 한번 확인해주세요.", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case BeerDataRequestTask.BEER_DATA_REQUEST :
                    bundle = msg.getData();
                    ArrayList beerInfos = bundle.getParcelableArrayList("beerInfos");
                    DatabaseSource databaseSource = new DatabaseSource(MenuActivity.this, 1);
                    databaseSource.insertBeerInfos(beerInfos);
                    Log.d("beer1WWW", databaseSource.selectBeerInfos().toString());
                    break;
            }

        }
    };
}
