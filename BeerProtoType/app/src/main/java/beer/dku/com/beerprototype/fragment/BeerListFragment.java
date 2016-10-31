package beer.dku.com.beerprototype.fragment;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import beer.dku.com.beerprototype.R;
import beer.dku.com.beerprototype.adapter.BeerListViewPagerAdapter;
import beer.dku.com.beerprototype.material.SlidingTabLayout;

public class BeerListFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    ViewPager pager;
    private String[] titles = new String[]{"라거", "에일", "IPA", "흑맥주"};

    SlidingTabLayout slidingTabLayout;

    private AppCompatActivity mContext;

    public BeerListFragment() {
        // Required empty public constructor
    }

    public static BeerListFragment newInstance(String param1, String param2) {
        BeerListFragment fragment = new BeerListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_beer_list, container, false);

        pager = (ViewPager) view.findViewById(R.id.viewpager);
        slidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
        pager.setAdapter(new BeerListViewPagerAdapter(mContext.getSupportFragmentManager(), titles));

        slidingTabLayout.setViewPager(pager);
        slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return Color.WHITE;
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        mContext = (AppCompatActivity) context;

        super.onAttach(context);
        if (context instanceof BeerListFragment.OnFragmentInteractionListener) {
            mListener = (BeerListFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
