package beer.dku.com.beerprototype.customview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import beer.dku.com.beerprototype.R;
import beer.dku.com.beerprototype.dao.BeerInfo;

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
    public Object getItem(int i) {
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
            beerInfoHolder = new BeerInfoHolder();
            Log.d("list", "1");
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.holder_beerinfo, null);

            beerInfoHolder.setBeerNameView( (TextView) view.findViewById(R.id.beerNameView) );
            beerInfoHolder.setCountryView( (TextView) view.findViewById(R.id.countryView) );
            beerInfoHolder.setDegreeView( (TextView) view.findViewById(R.id.degreeView) );
            beerInfoHolder.setImg_url_View( (TextView) view.findViewById(R.id.img_url_View) );
            beerInfoHolder.setCategoryView( (TextView) view.findViewById(R.id.categoryView) );
            beerInfoHolder.setEvaluationView( (TextView) view.findViewById(R.id.evaluationView) );
            beerInfoHolder.setDescriptionView( (TextView) view.findViewById(R.id.descriptionView) );
            Log.d("list", "2");
            view.setTag(beerInfoHolder);
        } else {
            beerInfoHolder = (BeerInfoHolder) view.getTag();
        }

        BeerInfo beerInfo = beerInfoList.get(i);
        Log.d("list", beerInfo.toString());
        beerInfoHolder.getBeerNameView().setText(beerInfo.getBeerName());
        beerInfoHolder.getCountryView().setText(beerInfo.getCountry());
        beerInfoHolder.getDegreeView().setText(beerInfo.getDegree()+"");
        beerInfoHolder.getImg_url_View().setText(beerInfo.getImg_url());
        beerInfoHolder.getCategoryView().setText(beerInfo.getCategory());
        beerInfoHolder.getEvaluationView().setText(beerInfo.getEvaluation());
        beerInfoHolder.getDescriptionView().setText(beerInfo.getDescriptional());

        return view;
    }
}
