package beer.dku.com.beerprototype.asynctasks;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import beer.dku.com.beerprototype.servercommunication.ProxyObject;

/**
 * Created by ChoGunHee on 2016-11-03.
 */

public class SighUpTask extends Thread {
    private Handler handler;
    private String name;
    private String email;
    private String password;

    public SighUpTask(Handler handler, String name, String email, String password) {
        this.handler = handler;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public void run() {
        boolean result = ProxyObject.getInstance().requestsignUp(name, email, password);
        Message msg = new Message();
        Bundle bundle = new Bundle();
        bundle.putBoolean("result", result);
        msg.setData(bundle);

        handler.sendMessage(msg);
    }
}
