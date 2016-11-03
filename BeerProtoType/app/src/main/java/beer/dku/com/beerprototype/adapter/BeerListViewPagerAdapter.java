package beer.dku.com.beerprototype.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import beer.dku.com.beerprototype.page.BeerListPage;

/**
 * Created by ChoiGunHee on 2016-11-01.
 */

public class BeerListViewPagerAdapter extends FragmentStatePagerAdapter {

    private int page_count;
    private String[] titles;

    public BeerListViewPagerAdapter(FragmentManager fm, String [] titles) {
        super(fm);
        page_count = titles.length;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return BeerListPage.newInstance("BeerList", titles[position], position);
    }

    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return page_count;
    }
}
