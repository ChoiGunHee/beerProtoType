package beer.dku.com.beerprototype.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import beer.dku.com.beerprototype.R;
import beer.dku.com.beerprototype.customview.BeerInfoListViewAdapter;
import beer.dku.com.beerprototype.dao.BeerInfo;
import cz.msebera.android.httpclient.Header;

public class BeerInfoActivity_Test extends AppCompatActivity {

    ArrayList<BeerInfo> beerInfoArrayList = new ArrayList<BeerInfo>();
    BeerInfoListViewAdapter beerInfoListViewAdapter;
    private ListView beerInfoListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_info_test);

        getBeerInfoData();

        beerInfoListView = (ListView) findViewById(R.id.beerInfoListView);
        beerInfoListViewAdapter = new BeerInfoListViewAdapter(this, beerInfoArrayList);
        beerInfoListView.setAdapter(beerInfoListViewAdapter);
        beerInfoListViewAdapter.notifyDataSetChanged();

        beerInfoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                BeerInfo beerInfo = beerInfoArrayList.get(i);
                Toast.makeText(BeerInfoActivity_Test.this, beerInfo.getBeerName() + beerInfo.getCategory(), Toast.LENGTH_SHORT).show();
            }
        });

        beerInfoArrayList.add(new BeerInfo("fail", "fail", 1.1, "fail", "fail", "fail", "fail"));
        beerInfoArrayList.add(new BeerInfo("fail", "fail", 1.1, "fail", "fail", "fail", "fail"));
    }

    private void getBeerInfoData() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://jejusien.herokuapp.com/beer/get/all", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String responseString = new String(responseBody);

                try {
                    JSONObject responseRoot = new JSONObject(responseString);
                    JSONArray beerInfoJSONArray = responseRoot.getJSONArray("info");

                    for(int i=0; i<beerInfoJSONArray.length(); i++) {
                        JSONObject beerInfoJSON = beerInfoJSONArray.getJSONObject(i);
                        BeerInfo beerInfo = new BeerInfo(beerInfoJSON.getString(BeerInfo.BEERNAME),
                                beerInfoJSON.getString(BeerInfo.COUNTRY),
                                beerInfoJSON.getDouble(BeerInfo.DEGREE),
                                beerInfoJSON.getString(BeerInfo.IMG_URL),
                                beerInfoJSON.getString(BeerInfo.CATEGORY),
                                beerInfoJSON.getString(BeerInfo.EVALUATION),
                                beerInfoJSON.getString(BeerInfo.DESCRIPTIONAL));
                        Log.d("beer", beerInfo.toString());
                        beerInfoArrayList.add(beerInfo);
                        beerInfoListViewAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                beerInfoArrayList.add(new BeerInfo("fail", "fail", 1.1, "fail", "fail", "fail", "fail"));
            }
        });
    }
}
