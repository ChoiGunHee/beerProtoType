package beer.dku.com.beerprototype.fragment;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.models.BarModel;

import beer.dku.com.beerprototype.R;
import beer.dku.com.beerprototype.activity.MainActivity;

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

        mBarChart.addBar(new BarModel("라거", 10.0f, 0xFFF7FE2E));
        mBarChart.addBar(new BarModel("에일", 8.0f, 0xFFDBA901));
        mBarChart.addBar(new BarModel("IPA", 3.0f,  0xFFB43104));
        mBarChart.addBar(new BarModel("흑맥주", 1.0f, 0xFF3B0B0B));
        mBarChart.addBar(new BarModel("기타", 5.0f, 0xFFD8D8D8));

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
}
