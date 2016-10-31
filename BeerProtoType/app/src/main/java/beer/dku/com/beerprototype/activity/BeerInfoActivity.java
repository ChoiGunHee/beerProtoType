package beer.dku.com.beerprototype.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import beer.dku.com.beerprototype.R;

public class BeerInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_info);

        Intent intent = getIntent();
        TextView textView = (TextView) findViewById(R.id.textView);

        textView.setText(intent.getStringExtra("NAME"));
    }

}
