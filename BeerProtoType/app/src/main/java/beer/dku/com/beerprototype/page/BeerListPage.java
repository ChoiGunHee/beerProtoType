package beer.dku.com.beerprototype.page;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import beer.dku.com.beerprototype.R;
import beer.dku.com.beerprototype.activity.BeerInfoActivity;
import beer.dku.com.beerprototype.customview.BeerInfoListViewAdapter;
import beer.dku.com.beerprototype.dao.BeerInfo;
import beer.dku.com.beerprototype.database.DatabaseSource;

public class BeerListPage extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String ARG_POSITION = "position";

    private String mParam1;
    private String mParam2;

    private int position;
    private AppCompatActivity mContext;

    public static final int RATINGRQUSTCODE = 1;
    private ListView listView;
    private DatabaseSource databaseSource;
    private ArrayList<BeerInfo> list;
    private BeerInfoListViewAdapter adapter;

    public BeerListPage() {
        // Required empty public constructor
    }

    public static BeerListPage newInstance(String param1, String param2, int position) {
        BeerListPage fragment = new BeerListPage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putInt(ARG_POSITION, position);
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
        position = getArguments().getInt(ARG_POSITION);
        View view = inflater.inflate(R.layout.page_beer_list, container, false);

        listView = (ListView) view.findViewById(R.id.beerInfoListView);
        databaseSource = new DatabaseSource(getContext(), 1);
        list = databaseSource.selectBeerInfos_style(mParam2);

        adapter = new BeerInfoListViewAdapter(getContext(), list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(mContext, BeerInfoActivity.class);
                Bundle data = new Bundle();
                BeerInfo info = (BeerInfo) adapterView.getAdapter().getItem(i);
                data.putSerializable("beerInfo", info);
                intent.putExtra("beerInfo", data);
                intent.putExtra("index", i);
                startActivityForResult(intent, RATINGRQUSTCODE);
            }
        });

        adapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RATINGRQUSTCODE) {
            if(requestCode == Activity.RESULT_OK) {
                int index = data.getIntExtra("index", 0);

            }
        }
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
