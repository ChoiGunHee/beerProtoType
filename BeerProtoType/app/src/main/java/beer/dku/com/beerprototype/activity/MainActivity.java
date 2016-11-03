package beer.dku.com.beerprototype.activity;

import android.support.v4.app.FragmentManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import beer.dku.com.beerprototype.R;
import beer.dku.com.beerprototype.fragment.BeerListFragment;
import beer.dku.com.beerprototype.fragment.FavoritListFragment;
import beer.dku.com.beerprototype.fragment.HomeFragment;
import beer.dku.com.beerprototype.fragment.MyInfoFragment;
import beer.dku.com.beerprototype.fragment.SearchFragment;
import beer.dku.com.beerprototype.fragment.ShopFragment;

public class MainActivity extends AppCompatActivity
    implements  BeerListFragment.OnFragmentInteractionListener,
                SearchFragment.OnFragmentInteractionListener,
                ShopFragment.OnFragmentInteractionListener {

    public static final String HOME_FLAG = "HOME";
    public static final String MYINFO_FLAG = "MYINFO";
    public static final String FAVORITELIST_FLAG = "FAVORITLIST";
    public static final String BEERLIST_FLAG = "BEERLIST";
    public static final String SEARCH_FLAG = "SEARCH";
    public static final String SHOP_FLAG = "SHOP";

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private ListView mDrawerList;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.navdrawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_ab_drawer);
        }

        drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(drawerToggle);
        String[] values = new String[]{
                "HOME", "내 정보", "위시 리스트", "맥주리스트", "맥주검색", "제주지앵 매장안내"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
        mDrawerList.setAdapter(adapter);

        mDrawerList.setBackgroundColor(getResources().getColor(R.color.red));
        toolbar.setBackgroundColor(getResources().getColor(R.color.red));
        //slidingTabLayout.setBackgroundColor(getResources().getColor(R.color.red));

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch (position) {
                    case 0:
                        switchFragment(HOME_FLAG);
                        toolbar.setTitle("HOME");
                        break;
                    case 1:
                        switchFragment(MYINFO_FLAG);
                        toolbar.setTitle("내 정보");
                        break;
                    case 2:
                        switchFragment(FAVORITELIST_FLAG);
                        toolbar.setTitle("위시리스트");
                        break;
                    case 3:
                        switchFragment(BEERLIST_FLAG);
                        toolbar.setTitle("맥주 리스트");
                        break;
                    case 4:
                        switchFragment(SEARCH_FLAG);
                        toolbar.setTitle("맥주 검색");
                        break;
                    case 5:
                        switchFragment(SHOP_FLAG);
                        toolbar.setTitle("매장안내");
                        break;
                }
                mDrawerLayout.closeDrawer(Gravity.START);

            }
        });

        switchFragment(HOME_FLAG);
    }

    public void switchFragment(String flag) {
        Fragment fragment;

        switch (flag) {
            case HOME_FLAG :
                fragment = HomeFragment.newInstance(HOME_FLAG, HOME_FLAG);
                break;

            case MYINFO_FLAG :
                fragment = MyInfoFragment.newInstance(MYINFO_FLAG, MYINFO_FLAG);
                break;

            case FAVORITELIST_FLAG :
                fragment = FavoritListFragment.newInstance(FAVORITELIST_FLAG, FAVORITELIST_FLAG);
                break;

            case BEERLIST_FLAG :
                fragment = BeerListFragment.newInstance(BEERLIST_FLAG, BEERLIST_FLAG);
                break;

            case SEARCH_FLAG :
                fragment = SearchFragment.newInstance(SEARCH_FLAG, SEARCH_FLAG);
                break;

            case SHOP_FLAG :
                fragment = ShopFragment.newInstance(SHOP_FLAG, SHOP_FLAG);
                break;

            default :
                fragment = HomeFragment.newInstance(HOME_FLAG, HOME_FLAG);
                break;
        }

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(Gravity.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
