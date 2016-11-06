package beer.dku.com.beerprototype.listener;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import beer.dku.com.beerprototype.database.DatabaseSource;

/**
 * Created by ChoiGunHee on 2016-11-06.
 */

public class BeerWishListInsertLinstener
    implements View.OnClickListener {

    private String bid;
    private Context context;

    public BeerWishListInsertLinstener(Context context, String bid) {
        this.bid = bid;
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        DatabaseSource databaseSource = new DatabaseSource(context, 1);
        databaseSource.insertWishList(bid);
        Toast.makeText(context, "HI", Toast.LENGTH_SHORT).show();
    }
}
