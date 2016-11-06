package beer.dku.com.beerprototype.dao;

import java.io.Serializable;

/**
 * Created by ChoiGunHee on 2016-11-06.
 */

public class BeerInfo_Rating
    implements Serializable {
    private BeerInfo info;
    private int rating;

    public BeerInfo_Rating(BeerInfo info, int rating) {
        this.info = info;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "BeerInfo_Rating{" +
                "info=" + info +
                ", rating=" + rating +
                '}';
    }

    public BeerInfo getInfo() {
        return info;
    }

    public int getRating() {
        return rating;
    }
}
