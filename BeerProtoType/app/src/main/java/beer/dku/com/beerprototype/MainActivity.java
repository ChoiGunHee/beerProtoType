package beer.dku.com.beerprototype;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.loopj.android.http.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textview);


    }

    public void send(View view) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://jejusien.herokuapp.com/dbcrud/get?beername=test1", new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String responsString = new String(responseBody);

                try {
                    JSONObject jsonObject = new JSONObject(responsString);
                    JSONArray jsonArray = jsonObject.getJSONArray("info");

                    for(int i=0; i<jsonArray.length(); i++) {
                        JSONObject tempjsonObject = jsonArray.getJSONObject(i);
                        int id = tempjsonObject.getInt("id");
                        String beername = tempjsonObject.getString("beername");
                        int capacity = tempjsonObject.getInt("capacity");

                        textView.setText(id + "\n" + beername + "\n" + capacity);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                textView.setText(error.toString());
            }
        });
    }
}
