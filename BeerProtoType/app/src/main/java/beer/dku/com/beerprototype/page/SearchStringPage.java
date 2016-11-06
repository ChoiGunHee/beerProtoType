package beer.dku.com.beerprototype.page;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import beer.dku.com.beerprototype.R;
import beer.dku.com.beerprototype.customview.BeerInfoListViewAdapter;
import beer.dku.com.beerprototype.dao.BeerInfo;
import beer.dku.com.beerprototype.database.DatabaseSource;
import beer.dku.com.beerprototype.listener.BeerInfoDetailListener;

public class SearchStringPage extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String ARG_POSITION = "position";

    private String mParam1;
    private String mParam2;

    private int position;

    private AppCompatActivity mContext;
    private DatabaseSource databaseSource;
    private ListView listView;
    private ArrayList<BeerInfo> datas;
    private BeerInfoListViewAdapter adapter;
    private AutoCompleteTextView autoCompleteTextView;

    public SearchStringPage() {
        // Required empty public constructor
    }

    public static SearchStringPage newInstance(String param1, String param2, int position) {
        SearchStringPage fragment = new SearchStringPage();
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
        View view = inflater.inflate(R.layout.page_search_string, container, false);

        position = getArguments().getInt(ARG_POSITION);

        databaseSource = new DatabaseSource(getContext(), 1);
        autoCompleteTextView = (AutoCompleteTextView) view.findViewById(R.id.searchString_autoCompleteTextView);
        ArrayList<String> autoList = databaseSource.selectBeerInfos_all_name();
        Log.d("beer11111", autoList.toString());
        final ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, autoList);
        autoCompleteTextView.setAdapter(arrayAdapter);

        listView = (ListView) view.findViewById(R.id.searchStringListView);
        datas = new ArrayList<BeerInfo>();
        adapter = new BeerInfoListViewAdapter(getContext(), datas);
        listView.setAdapter(adapter);
        Button searchButton = (Button) view.findViewById(R.id.search_string_btn) ;
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datas = databaseSource.searchBeerName(autoCompleteTextView.getText().toString());
                adapter = new BeerInfoListViewAdapter(getContext(), datas);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "dd", Toast.LENGTH_SHORT).show();
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
