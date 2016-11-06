package beer.dku.com.beerprototype.listener;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import beer.dku.com.beerprototype.activity.BeerInfoActivity;
import beer.dku.com.beerprototype.adapter.BeerListViewPagerAdapter;
import beer.dku.com.beerprototype.customview.BeerInfoListViewAdapter;
import beer.dku.com.beerprototype.dao.BeerInfo;

/**
 * Created by ChoiGunHee on 2016-11-06.
 */

public class BeerInfoDetailListener
    implements View.OnClickListener {

    private Context mContext;
    private BeerInfo info;

    public BeerInfoDetailListener(Context mContext, BeerInfo info) {
        this.mContext = mContext;
        this.info = info;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(mContext, BeerInfoActivity.class);
        Bundle data = new Bundle();
        data.putSerializable("beerInfo", info);
        intent.putExtra("beerInfo", data);
        mContext.startActivity(intent);
    }
}
