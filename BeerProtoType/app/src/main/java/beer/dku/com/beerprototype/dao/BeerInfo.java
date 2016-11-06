package beer.dku.com.beerprototype.dao;

import org.json.JSONObject;

import java.io.Serializable;

import beer.dku.com.beerprototype.database.DatabaseSource;

/**
 * Created by ChoiGunHee on 2016-10-14.
 */

public class BeerInfo
    implements Serializable {
    public static final String BID          = "bid";
    public static final String KRNAME       = "krname";
    public static final String ENNAME       = "enname";
    public static final String STYLE        = "style";
    public static final String ABV          = "abv";
    public static final String PRICE        = "price";
    public static final String DESCRIPTION  = "description";
    public static final String BEERIMG_URL  = "img_url";
    public static final String BEER_RATING  = "beerraing";

    private String bid;
    private String krname;
    private String enname;
    private String style;
    private String abv;
    private String price;
    private String description;
    private String beerimg_url;
    private int beerRating;

    public BeerInfo(String bid, String krname, String enname, String style, String abv, String price, String description, String beerimg_url, int beerRating) {
        this.bid = bid;
        this.krname = krname;
        this.enname = enname;
        this.style = style;
        this.abv = abv;
        this.price = price;
        this.description = description;
        this.beerimg_url = beerimg_url;
        this.beerRating = beerRating;

    }

    public BeerInfo(String bid, String krname, String enname, String style, String abv, String price, String description, String beerimg_url) {
        this.bid = bid;
        this.krname = krname;
        this.enname = enname;
        this.style = style;
        this.abv = abv;
        this.price = price;
        this.description = description;
        this.beerimg_url = beerimg_url;
        this.beerRating = 0;
    }

    public BeerInfo(JSONObject object) {

    }

    public int getBeerRating() {
        return beerRating;
    }

    public String getBID() {
        return bid;
    }

    public String getKrname() {
        return krname;
    }

    public String getEnname() {
        return enname;
    }

    public String getStyle() {
        return style;
    }

    public String getAbv() {
        return abv;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getBeerimg_url() {
        return beerimg_url;
    }

    @Override
    public String toString() {
        return "BeerInfo{" +
                "bid='" + bid + '\'' +
                ", krname='" + krname + '\'' +
                ", enname='" + enname + '\'' +
                ", style='" + style + '\'' +
                ", abv='" + abv + '\'' +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                ", beerimg_url='" + beerimg_url + '\'' +
                '}';
    }
}
