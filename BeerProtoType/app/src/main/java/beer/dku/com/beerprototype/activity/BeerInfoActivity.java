package beer.dku.com.beerprototype.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import beer.dku.com.beerprototype.R;
import beer.dku.com.beerprototype.dao.BeerInfo;
import beer.dku.com.beerprototype.database.DatabaseSource;

public class BeerInfoActivity extends AppCompatActivity {

    private RatingBar ratingBar;
    Intent resultIntent = new Intent();
    private BeerInfo info;
    private ImageView imageView;
    private TextView krNameView;
    private TextView enNameView;

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
                resultIntent.putExtra("rating", v);
            }
        });

        imageView = (ImageView) findViewById(R.id.beerInfoActivityImgView);
        krNameView = (TextView) findViewById(R.id.krNameView);
        enNameView = (TextView) findViewById(R.id.enNameView);

        Intent intent = getIntent();

        Bundle data = (Bundle) intent.getExtras().get("beerInfo");
        info = (BeerInfo) data.get("beerInfo");
        krNameView.setText(info.getKrname());
        enNameView.setText(info.getEnname());

        String imgName = info.getBeerimg_url().split("\\.")[0];
        if(imgName != null) {
            int id = getResources().getIdentifier("beer.dku.com.beerprototype:drawable/" + imgName, null, null);
            if(id != 0x0) {
                imageView.setImageDrawable(getResources().getDrawable(id, null));
            } else {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.missimage));
            }
        }

        ratingBar.setRating(info.getBeerRating());

        resultIntent.putExtra("index", intent.getIntExtra("index", 0));

    }

    @Override
    protected void onPause() {
        setResult(Activity.RESULT_OK, resultIntent);
        DatabaseSource databaseSource = new DatabaseSource(this, 1);
        databaseSource.insertBeerRating(info.getBID(), (int) ratingBar.getRating());
        super.onPause();
    }
}