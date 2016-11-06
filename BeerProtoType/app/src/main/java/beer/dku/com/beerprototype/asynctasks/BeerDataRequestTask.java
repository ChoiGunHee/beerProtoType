package beer.dku.com.beerprototype.asynctasks;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;

import beer.dku.com.beerprototype.servercommunication.ProxyObject;

/**
 * Created by ChoiGunHee on 2016-11-05.
 */

public class BeerDataRequestTask extends Thread {

    public static final int BEER_DATA_REQUEST = 2;

    private Handler handler;

    public BeerDataRequestTask(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        ArrayList result = ProxyObject.getInstance().requestDatabaseInfo();
        Message msg = new Message();
        msg.what = BEER_DATA_REQUEST;
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("beerInfos", result);
        msg.setData(bundle);

        handler.sendMessage(msg);
    }
}
