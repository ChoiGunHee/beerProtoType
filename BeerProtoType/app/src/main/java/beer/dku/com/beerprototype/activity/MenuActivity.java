package beer.dku.com.beerprototype.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import beer.dku.com.beerprototype.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

    }

    public void login(View view) {
        Intent loginIntent = new Intent(this, BeerInfoActivity.class);
        startActivity(loginIntent);
        finish();
    }
}
