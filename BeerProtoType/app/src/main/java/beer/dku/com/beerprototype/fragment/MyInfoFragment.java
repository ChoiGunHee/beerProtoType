package beer.dku.com.beerprototype.fragment;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.models.BarModel;

import java.util.ArrayList;

import beer.dku.com.beerprototype.R;
import beer.dku.com.beerprototype.activity.MainActivity;
import beer.dku.com.beerprototype.dao.BeerInfo;
import beer.dku.com.beerprototype.database.DatabaseSource;

public class MyInfoFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private MainActivity mContext;

    public MyInfoFragment() {
        // Required empty public constructor
    }

    public static MyInfoFragment newInstance(String param1, String param2) {
        MyInfoFragment fragment = new MyInfoFragment();
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
        View view = inflater.inflate(R.layout.fragment_my_info, container, false);

        BarChart mBarChart = (BarChart) view.findViewById(R.id.barchart);
        DatabaseSource databaseSource = new DatabaseSource(getContext(), 1);

        ArrayList<BeerInfo> datas = databaseSource.selectBeerRaintBeerInfos();

        mBarChart.addBar(new BarModel("라거", getStyle_Beer_Number("라거", datas), 0xFFF7FE2E));
        mBarChart.addBar(new BarModel("에일", getStyle_Beer_Number("에일", datas), 0xFFDBA901));
        mBarChart.addBar(new BarModel("IPA", getStyle_Beer_Number("IPA", datas),  0xFFB43104));
        mBarChart.addBar(new BarModel("필스너", getStyle_Beer_Number("필스너", datas), 0xFF3B0B0B));
        mBarChart.addBar(new BarModel("Wish", 1.0f, 0xFF3B0B0B));

        mBarChart.startAnimation();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        mContext = (MainActivity) context;
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private float getStyle_Beer_Number(String style, ArrayList<BeerInfo> datas) {
        int result = 0;
        for (BeerInfo info : datas) {
            if(style.equals(info.getStyle())) {
                result++;
            }
        }

        return (float) result;
    }
}
