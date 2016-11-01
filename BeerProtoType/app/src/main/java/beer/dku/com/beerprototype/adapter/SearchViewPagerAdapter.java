package beer.dku.com.beerprototype.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import beer.dku.com.beerprototype.page.SearchImagePage;
import beer.dku.com.beerprototype.page.SearchStringPage;

/**
 * Created by ChoGunHee on 2016-11-02.
 */

public class SearchViewPagerAdapter extends FragmentPagerAdapter {

    private int page_count;
    private String[] titles;

    public SearchViewPagerAdapter(FragmentManager fm, String [] titles) {
        super(fm);
        page_count = titles.length;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0 :
                return SearchStringPage.newInstance("StringSearch", titles[position], position);
            case 1 :
                return SearchImagePage.newInstance("ImageSearch", titles[position], position);
            default:
                return SearchStringPage.newInstance("StringSearch", titles[position], position);
        }
    }

    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return page_count;
    }
}
