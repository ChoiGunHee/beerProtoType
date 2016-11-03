package beer.dku.com.beerprototype.asynctasks;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import beer.dku.com.beerprototype.servercommunication.ProxyObject;

/**
 * Created by ChoGunHee on 2016-11-03.
 */

public class LoginTask extends Thread {

    private Handler handler;
    private String email;
    private String password;

    public LoginTask(Handler handler, String email, String password) {
        this.handler = handler;
        this.email = email;
        this.password = password;
    }

    @Override
    public void run() {
        boolean result = ProxyObject.getInstance().requestLogin(email, password);
        Message msg = new Message();
        Bundle bundle = new Bundle();
        bundle.putBoolean("result", result);
        msg.setData(bundle);

        handler.sendMessage(msg);
    }
}
