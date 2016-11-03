package beer.dku.com.beerprototype.asynctasks;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import beer.dku.com.beerprototype.servercommunication.ProxyObject;

/**
 * Created by ChoGunHee on 2016-11-04.
 */

public class ImageSearchTask extends Thread {
    private Handler handler;
    private String path;
    private String imgName;

    public ImageSearchTask(Handler handler, String path, String imgName) {
        this.handler = handler;
        this.path = path;
        this.imgName = imgName;
    }

    @Override
    public void run() {
        String result = ProxyObject.getInstance().requestSearchWithImage(path, imgName);
        Message msg = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("result", result);
        msg.setData(bundle);

        handler.sendMessage(msg);
    }
}
