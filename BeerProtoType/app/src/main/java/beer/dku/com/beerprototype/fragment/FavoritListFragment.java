package beer.dku.com.beerprototype.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import beer.dku.com.beerprototype.R;
import beer.dku.com.beerprototype.activity.BeerInfoActivity;
import beer.dku.com.beerprototype.customview.BeerInfoListViewAdapter;
import beer.dku.com.beerprototype.dao.BeerInfo;
import beer.dku.com.beerprototype.database.DatabaseSource;

public class FavoritListFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private AppCompatActivity mContext;

    public FavoritListFragment() {
        // Required empty public constructor
    }

    public static FavoritListFragment newInstance(String param1, String param2) {
        FavoritListFragment fragment = new FavoritListFragment();
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
        View view = inflater.inflate(R.layout.fragment_my_favorit_list, container, false);

        final ListView listView = (ListView) view.findViewById(R.id.favoritList);
        DatabaseSource databaseSource = new DatabaseSource(mContext, 1);

        //ArrayList<BeerInfo> datas = databaseSource.selectBeerInfos();
        //ArrayList<BeerInfo> datas = databaseSource.selectWishListBeerInfos();
        ArrayList<BeerInfo> datas = databaseSource.selectBeerRaintBeerInfos();
        BeerInfoListViewAdapter adapter = new BeerInfoListViewAdapter(getContext(), datas);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), BeerInfoActivity.class);
                Bundle data = new Bundle();
                BeerInfo info = (BeerInfo) adapterView.getAdapter().getItem(i);
                data.putSerializable("beerInfo", info);
                intent.putExtra("beerInfo", data);
            }
        });

        adapter.notifyDataSetChanged();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        mContext = (AppCompatActivity) context;
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
