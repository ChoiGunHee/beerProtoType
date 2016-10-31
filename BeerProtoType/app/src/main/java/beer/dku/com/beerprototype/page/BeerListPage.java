package beer.dku.com.beerprototype.page;

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

public class BeerListPage extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String ARG_POSITION = "position";

    private String mParam1;
    private String mParam2;

    private int position;
    private AppCompatActivity mContext;

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

        ListView listView = (ListView) view.findViewById(R.id.beerInfoListView);

        final ArrayList<String>  list = new ArrayList<>();

        if(mParam2.equals("에일")) {
            list.clear();
            list.add("기네스");
            list.add("런던프라이드");
        } else if(mParam2.equals("라거")) {
            list.clear();
            list.add("카스");
            list.add("필즈너우를켈");
            list.add("클라우드");
            list.add("하이네켄");
            list.add("하이트");
        } else {
            list.clear();
            for(int i=0; i<30; i++) {
                list.add("Test");
            }
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), BeerInfoActivity.class);
                intent.putExtra("NAME", list.get(i));
                startActivity(intent);
            }
        });
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
