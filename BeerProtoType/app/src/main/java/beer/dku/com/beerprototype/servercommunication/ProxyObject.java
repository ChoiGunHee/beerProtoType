package beer.dku.com.beerprototype.servercommunication;

import android.util.Log;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import beer.dku.com.beerprototype.dao.BeerInfo;
import cz.msebera.android.httpclient.Header;

/**
 * Created by ChoGunHee on 2016-11-03.
 */

public class ProxyObject {


    public static final String LOGIN_URL = "http://jejusien.herokuapp.com/users/login";
    public static final String SIGHUPURL = "http://jejusien.herokuapp.com/users/regist";
    public static final String IMGSEARCHURL = "http://jejusien.herokuapp.com/fileupload/put";
    public static final String BeerALLURL = "http://jejusien.herokuapp.com/beers/get/all";

    /*
    public static final String LOGIN_URL = "http://172.25.235.166:5000/users/login";
    public static final String SIGHUPURL = "http://172.25.235.166:5000/users/regist";
    public static final String IMGSEARCHURL = "http://172.25.235.166:5000/fileupload/put";
    */

    private String jauth;
    private String userName;

    static ProxyObject instance = new ProxyObject();

    private ProxyObject() {

    }

    public static ProxyObject getInstance() {
        return instance;
    }

    public ArrayList<BeerInfo> requestDatabaseInfo() {
        SyncHttpClient client = new SyncHttpClient();

        JsonHttpResponseHandler handler = new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    JSONArray infos = response.getJSONArray("info");
                    ArrayList<BeerInfo> resultList = new ArrayList<BeerInfo>();

                    for(int i = 0; i < infos.length(); i++) {
                        JSONObject info = infos.getJSONObject(i);

                        String bid = info.getString(BeerInfo.BID);
                        String krName = info.getString(BeerInfo.KRNAME);
                        String enName = info.getString(BeerInfo.ENNAME);
                        String style = info.getString(BeerInfo.STYLE);
                        String abv = info.getString(BeerInfo.ABV);
                        String price = info.getString(BeerInfo.PRICE);
                        String description = info.getString(BeerInfo.DESCRIPTION);
                        String img_uri = info.getString(BeerInfo.BEERIMG_URL);

                        BeerInfo beerInfo = new BeerInfo(bid, krName, enName, style, abv, price, description, img_uri);
                        resultList.add(beerInfo);
                    }

                    setTag(resultList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                setTag(null);
            }
        };

        client.get(BeerALLURL, handler);
        ArrayList<BeerInfo> resultList = (ArrayList<BeerInfo>) handler.getTag();

        return resultList;
    }

    public boolean requestLogin(String email, String password) {
        RequestParams params = new RequestParams();
        params.put("email", email);
        params.put("password", password);

        SyncHttpClient client = new SyncHttpClient();

        JsonHttpResponseHandler handler = new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONObject responseRoot = response;
                    jauth = responseRoot.getString("jauth");
                    JSONObject infoJsonObject = responseRoot.getJSONObject("jinfo");
                    userName = infoJsonObject.getString("username");
                    setTag(true);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                setTag(false);
            }
        };
        client.post(LOGIN_URL, params, handler);

        return (boolean) handler.getTag();
    }

    public boolean requestsignUp(String name, String email, String password) {
        RequestParams params = new RequestParams();
        params.put("username", name);
        params.put("email", email);
        params.put("password", password);
        SyncHttpClient client = new SyncHttpClient();

        JsonHttpResponseHandler handler = new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONObject responseRoot = response;
                String resultResponse = responseRoot.toString();
                this.setTag(true);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                this.setTag(false);
            }
        };

        client.post(SIGHUPURL, params, handler);

        return (boolean) handler.getTag();
    }

    public String requestSearchWithImage(String imagePath, String imgName) {
        File file = new File(imagePath);
        RequestParams params = new RequestParams();
        SyncHttpClient client = new SyncHttpClient();

        JsonHttpResponseHandler handler = new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    Log.d("beer1111", response.toString());
                    int result = response.getInt("resultCode");
                    String beername = response.getString("data");

                    if(result == 0) {
                        setTag(beername);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
            }
        };

        try {
            params.put("file", file);
            params.put("fname", imgName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        client.post(IMGSEARCHURL, params, handler);

        return handler.getTag().toString();
    }

    public String getJauth() {
        return jauth;
    }

    public String getUserName() {
        return userName;
    }
}
