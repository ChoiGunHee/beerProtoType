package beer.dku.com.beerprototype.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import beer.dku.com.beerprototype.R;
import beer.dku.com.beerprototype.fragment.BeerListFragment;
import beer.dku.com.beerprototype.fragment.TestFragment1;
import beer.dku.com.beerprototype.fragment.TestFragment2;

public class TestActivity extends AppCompatActivity
    implements BeerListFragment.OnFragmentInteractionListener,
                TestFragment1.OnFragmentInteractionListener,
                TestFragment2.OnFragmentInteractionListener {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;
    private ListView mDrawerList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

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
                "DEFAULT", "RED", "BLUE", "MATERIAL GREY"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch (position) {
                    case 0:
                        mDrawerLayout.closeDrawer(Gravity.START);
                        Toast.makeText(TestActivity.this, "wow1", Toast.LENGTH_SHORT).show();
                        switchFragment(0);
                        break;
                    case 1:
                        mDrawerLayout.closeDrawer(Gravity.START);
                        Toast.makeText(TestActivity.this, "wow2", Toast.LENGTH_SHORT).show();
                        switchFragment(1);
                        break;
                    case 2:
                        mDrawerLayout.closeDrawer(Gravity.START);
                        Toast.makeText(TestActivity.this, "wow3", Toast.LENGTH_SHORT).show();
                        switchFragment(2);
                        break;
                    case 3:
                        mDrawerLayout.closeDrawer(Gravity.START);
                        Toast.makeText(TestActivity.this, "wow4", Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        });

        switchFragment(1);
    }

    public void switchFragment(int index) {
        Fragment fragment;

        switch (index) {
            case 0 :
                fragment = new TestFragment1();
                break;
            case 1 :
                fragment = new TestFragment2();
                break;
            case 2 :
                fragment = new BeerListFragment();
                break;
            default :
                fragment = new TestFragment1();
                break;
        }

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
