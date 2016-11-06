package beer.dku.com.beerprototype.customview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import beer.dku.com.beerprototype.R;
import beer.dku.com.beerprototype.activity.BeerInfoActivity;
import beer.dku.com.beerprototype.dao.BeerInfo;
import beer.dku.com.beerprototype.database.DatabaseSource;
import beer.dku.com.beerprototype.listener.BeerInfoDetailListener;
import beer.dku.com.beerprototype.listener.BeerWishListInsertLinstener;

/**
 * Created by ChoiGunHee on 2016-10-14.
 */

public class BeerInfoListViewAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<BeerInfo> beerInfoList;

    public BeerInfoListViewAdapter(Context mContext) {
        super();
        this.mContext = mContext;
    }

    public BeerInfoListViewAdapter(Context mContext, ArrayList<BeerInfo> beerInfoList) {
        super();
        this.mContext = mContext;
        this.beerInfoList = beerInfoList;
    }

    @Override
    public int getCount() {
        return beerInfoList.size();
    }

    @Override
    public BeerInfo getItem(int i) {
        return beerInfoList.get(i);
    }

    @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                BeerInfoHolder beerInfoHolder;

                if(view == null) {
                    LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = inflater.inflate(R.layout.holder_beerlistview, null);

                    beerInfoHolder = new BeerInfoHolder(
                            (ImageView) view.findViewById(R.id.beerImg),
                            (ImageView) view.findViewById(R.id.favoriteImg),
                            (TextView) view.findViewById(R.id.beer_kor_name),
                            (TextView) view.findViewById(R.id.beer_eng_name),
                            (TextView) view.findViewById(R.id.styleName),
                            (TextView) view.findViewById(R.id.priceName),
                            (TextView) view.findViewById(R.id.abvName),
                            (TextView) view.findViewById(R.id.ratingName)
                    );

                    view.setTag(beerInfoHolder);
                } else {
                    beerInfoHolder = (BeerInfoHolder) view.getTag();
                }

                BeerInfo beerInfo = beerInfoList.get(i);
                Log.d("beerTest", "beer.dku.com.beerprototype:drawable/" + beerInfo.getBeerimg_url());
                //Log.d("beerTest", beerInfo.getBeerimg_url().split(".").toString());
                String imgName = beerInfo.getBeerimg_url().split("\\.")[0];
                if(imgName != null) {
                    int id = view.getResources().getIdentifier("beer.dku.com.beerprototype:drawable/" + imgName, null, null);
                    if(id != 0x0) {
                        beerInfoHolder.getBeerImgView().setImageDrawable(view.getResources().getDrawable(id, null));
                    } else {
                        beerInfoHolder.getBeerImgView().setImageDrawable(view.getResources().getDrawable(R.drawable.missimage));
                    }
                }

                beerInfoHolder.getBeerImgView().setOnClickListener(new BeerInfoDetailListener(view.getContext(), beerInfoList.get(i)));

                beerInfoHolder.getFavoriteImgView().setOnClickListener(new BeerWishListInsertLinstener(view.getContext(), beerInfo.getBID()));
                beerInfoHolder.getKorNameView().setText(beerInfo.getKrname());
                beerInfoHolder.getEngNameView().setText(beerInfo.getEnname());
                beerInfoHolder.getStyleNameView().setText(beerInfo.getStyle());
                beerInfoHolder.getPriceNameView().setText(beerInfo.getPrice());
                beerInfoHolder.getAbvNameView().setText(beerInfo.getAbv());
                beerInfoHolder.getRatingView().setText(beerInfo.getBeerRating()+"");

        return view;
    }
}
