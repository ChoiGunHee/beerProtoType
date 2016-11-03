package beer.dku.com.beerprototype.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import beer.dku.com.beerprototype.R;

public class BeerInfoActivity extends AppCompatActivity {

    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_info);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar_beerInfo);
        ratingBar.setRating(0);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                ratingBar.setRating(v);
            }
        });
        Intent intent = getIntent();
        TextView textView = (TextView) findViewById(R.id.textView);

        textView.setText(intent.getStringExtra("NAME"));
    }

}